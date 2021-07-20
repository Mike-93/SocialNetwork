package application.dao;

import application.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person user = new Person();
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setRegDate(rs.getDate("reg_date"));
        user.setBirthDate(rs.getDate("birth_date"));
        user.setEmail(rs.getString("e_mail"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setPhoto(rs.getString("photo"));
        user.setAbout(rs.getString("about"));
        user.setTown(rs.getString("town"));
        user.setConfirmationCode(rs.getString("confirmation_code"));
        user.setIsApproved(rs.getBoolean("is_approved"));
        user.setLastOnlineTime(rs.getDate("last_online_time"));
        return user;
    }
}
