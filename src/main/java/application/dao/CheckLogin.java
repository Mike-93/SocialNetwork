package application.dao;

import application.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CheckLogin {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CheckLogin(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person checkLogin(String log, String pass) {
        return jdbcTemplate.query("SELECT * FROM users WHERE login = " + log + "and password = " + pass
                , new PersonMapper()).stream().findAny().orElse(null);
    }
}
