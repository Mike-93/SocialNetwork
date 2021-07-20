package application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Person {
    private String firstName;
    private String lastName;
    private Date regDate;
    private Date birthDate;
    private String email;
    private String phone;
    private String password;
    private String photo;
    private String about;
    private String town;
    private String confirmationCode;
    private Boolean isApproved;
    private Date lastOnlineTime;
}
