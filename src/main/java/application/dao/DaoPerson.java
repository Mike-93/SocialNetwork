package application.dao;

import application.models.FriendshipStatus;
import application.models.PermissionMessagesType;
import application.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DaoPerson implements Dao<Person> {

    private final JdbcTemplate jdbcTemplate;

    public Person getByEmail(String email) {
        String selectPersonByEmail = "SELECT * FROM person WHERE e_mail = ?";
        return jdbcTemplate.query(selectPersonByEmail, new Object[]{email}, new PersonMapper()).stream().findAny()
                .orElse(null);
    }

    @Override
    public Person get(int id) {
        String selectPersonForId = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.query(selectPersonForId, new Object[]{id}, new PersonMapper()).stream()
                .findAny().orElse(null);
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    public List<Person> getRecommendations(int id) {
        String selectRecommendations = "SELECT * FROM person WHERE id IN (SELECT src_person_id from friendship " +
                "JOIN friendship_status fs on fs.id = friendship.status_id WHERE code = ? AND dst_person_id " +
                "IN (SELECT src_person_id FROM friendship WHERE dst_person_id = ?)) AND person.id != ? " +
                "UNION SELECT * FROM person WHERE id IN (SELECT dst_person_id from friendship " +
                "JOIN friendship_status f on f.id = friendship.status_id WHERE code = ? AND src_person_id " +
                "IN (SELECT dst_person_id FROM friendship WHERE src_person_id = ?)) AND id != ?";
        return jdbcTemplate.query(selectRecommendations, new Object[]{FriendshipStatus.FRIEND.toString(), id, id,
                        FriendshipStatus.FRIEND.toString(), id, id},
                new PersonMapper());
    }

    public void save(Person person) {
        String sqlInsertPerson = "INSERT INTO person (first_name, last_name, password," +
                " e_mail, reg_date, messages_permission) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsertPerson,
                person.getFirstName(),
                person.getLastName(),
                person.getPassword(),
                person.getEmail(),
                System.currentTimeMillis(),
                PermissionMessagesType.ALL.toString());
    }

    @Override
    public void update(Person person, String... params) {

    }

    @Override
    public void delete(Person person) {

    }

    public List<Person> getFriends(int id) {

        String select = "SELECT * FROM person WHERE id IN (SELECT src_person_id FROM friendship" +
                " JOIN friendship_status fs on fs.id = friendship.status_id " +
                "WHERE code = '" + FriendshipStatus.FRIEND + "' AND dst_person_id = " + id + " union SELECT dst_person_id" +
                " FROM friendship JOIN friendship_status fs " +
                "on fs.id = friendship.status_id WHERE code = '" + FriendshipStatus.FRIEND + "' AND src_person_id = " + id + ")";

        return jdbcTemplate.query(select, new PersonMapper());
    }

    public void addFriendForId(int srcId, int dtsId) {

        String insertIntoFriendshipStatus = "INSERT INTO friendship_status (time, name, code) VALUES (?, ?, ?)";
        String insetIntoFriendship = "INSERT INTO friendship (status_id, src_person_id, dst_person_id) " +
                "VALUES ((SELECT max(friendship_status.id) from friendship_status), ?, ?)";

        jdbcTemplate.update(insertIntoFriendshipStatus, System.currentTimeMillis(),
                "Запрос на добавление в друзья",
                FriendshipStatus.REQUEST.toString());

        jdbcTemplate.update(insetIntoFriendship, srcId, dtsId);
    }

    public void addFriendRequest(int srcId, int dtsId) {

        String update = "UPDATE friendship_status SET code = ? WHERE id = (SELECT status_id FROM friendship" +
                " WHERE src_person_id = ? AND dst_person_id = ?)";

        jdbcTemplate.update(update, FriendshipStatus.FRIEND.toString(), srcId, dtsId);
    }

    public List<Person> getFriendsRequest(int id) {

        String selectRequests = "SELECT * FROM  person WHERE id IN (SELECT src_person_id FROM friendship " +
                "JOIN friendship_status fs on fs.id = friendship.status_id WHERE code = ? AND dst_person_id = ?)";

        return jdbcTemplate.query(selectRequests, new Object[]{FriendshipStatus.REQUEST.toString(),
                id}, new PersonMapper());
    }

    public String getFriendStatus(int srcId, int dtsId) {

        try {
            String select = "select code from friendship f join friendship_status fs2 on f.status_id = fs2.id " +
                    "where src_person_id IN (?, ?)" +
                    " and dst_person_id IN (?, ?)";

            return jdbcTemplate.queryForObject(select, new Object[]{srcId, dtsId, dtsId, srcId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteFriendForID(int srcId, int dtcId) {
        String deleteFriendshipStatus = "DELETE from friendship_status WHERE id = (SELECT status_id FROM friendship" +
                " WHERE src_person_id IN (?, ?) AND dst_person_id IN (?, ?))";

        String deleteFriendship = "DELETE FROM friendship WHERE src_person_id IN (?, ?) AND dst_person_id IN (?, ?)";

        jdbcTemplate.update(deleteFriendshipStatus, srcId, dtcId, dtcId, srcId);
        jdbcTemplate.update(deleteFriendship, srcId, dtcId, dtcId, srcId);
    }

    public Person getAuthPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getByEmail(authentication.getName());
    }

    public void unAcceptRequest(int dtsId, int srcId) {
        String updateFriendshipStatus = "UPDATE friendship_status SET code = ? WHERE id = (SELECT status_id " +
                "FROM friendship WHERE dst_person_id = ? AND src_person_id = ?)";
        jdbcTemplate.update(updateFriendshipStatus, FriendshipStatus.DECLINED.toString(),
                dtsId, srcId);
    }

    public void updateDeclined(int srcId, int dtcId) {
        String updateFriendship = "UPDATE friendship SET src_person_id = ?, dst_person_id = ? " +
                "WHERE src_person_id IN (?, ?) AND dst_person_id IN (?, ?)";

        String updateFriendshipStatus = "UPDATE friendship_status SET code = ? WHERE id = (SELECT status_id " +
                "FROM friendship WHERE src_person_id = ? AND dst_person_id = ?)";
        jdbcTemplate.update(updateFriendship, srcId, dtcId, srcId, dtcId, dtcId, srcId);
        jdbcTemplate.update(updateFriendshipStatus, FriendshipStatus.REQUEST, srcId, dtcId);
    }

    public List<Person> getRecommendationsOnRegDate() {
        String selectRecommendations = "SELECT * FROM person WHERE reg_date > ?";
        long twoDays = 172800000;
        return jdbcTemplate.query(selectRecommendations, new Object[]{System.currentTimeMillis() - twoDays},
                new PersonMapper());
    }
}
