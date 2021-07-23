package application.service;

import application.models.City;
import application.models.Country;
import application.models.Person;
import application.responses.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    public GeneralResponse<Person> getPerson(){
        GeneralResponse<Person> response = new GeneralResponse<>();
        response.setError("");
        response.setTimestamp(System.currentTimeMillis());
        Person person = new Person();
        person.setId(2);
        person.setFirstName("Борис");
        person.setLastName("Булкин");
        person.setRegDate(System.currentTimeMillis() - 567);
        person.setBirthDate(System.currentTimeMillis() - 1997);
        person.setEmail("gsdfhgsh@skdjfhskdj.ru");
        person.setPhone("9163332211");
        person.setPhoto("");
        person.setAbout("Немного обо мне");

        person.setCity("Москва");
        person.setCountry("Россия");
        person.setMessagesPermission("All");
        person.setLastOnlineTime(System.currentTimeMillis() - 40);
        person.setBlocked(false);
        response.setData(person);
        return response;
    }
}