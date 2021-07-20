package application.api;

import application.dao.CheckLogin;
import application.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthApiGeneral {
    private final CheckLogin checkLogin;

    @Autowired
    public AuthApiGeneral(CheckLogin checkLogin) {
        this.checkLogin = checkLogin;
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<Person> login(@PathVariable String login, @PathVariable String pass) {
        Optional<Person> optional = Optional.ofNullable(checkLogin.checkLogin(login, pass));
        return optional.isPresent() ? new ResponseEntity(optional.get(), HttpStatus.OK)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
