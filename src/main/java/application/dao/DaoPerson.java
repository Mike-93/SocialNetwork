package application.dao;

import application.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DaoPerson implements Dao<Person>{

    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_INSERT_PERSON = "INSERT INTO person (first_name, last_name, password, e_mail, reg_date) " +
            "VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_FIND_PERSON_BY_EMAIL = "SELECT * FROM person WHERE e_mail = ?";

    public Person getByEmail(String email) {
        return jdbcTemplate.query(SQL_FIND_PERSON_BY_EMAIL, new Object[]{email}, new PersonMapper()).stream().findAny()
                .orElse(null);
    }

    @Override
    public Optional<Person> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    public void save(Person person) {
        jdbcTemplate.update(SQL_INSERT_PERSON,
                person.getFirstName(),
                person.getLastName(),
                person.getPassword(),
                person.getEmail(),
                System.currentTimeMillis());
    }

    @Override
    public void update(Person person, String... params) {

    }

    @Override
    public void delete(Person person) {

    }
}
