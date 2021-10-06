package application.service;

import application.dao.DaoNotification;
import application.dao.DaoPerson;
import application.models.FriendshipStatus;
import application.models.NotificationType;
import application.models.Person;
import application.models.dto.MessageResponseDto;
import application.models.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendsService {

    private final DaoPerson daoPerson;
    private final DaoNotification daoNotification;

    public List<PersonDto> getUserFriends() {

        return daoPerson.getFriends(daoPerson.getAuthPerson().getId()).stream()
                .map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> getUserFriendsRequest() {

        return daoPerson.getFriendsRequest(daoPerson.getAuthPerson().getId())
                .stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> getUserFriendsRecommendations() {

        Person currentPerson = daoPerson.getAuthPerson();

        List<Integer> listBlockPerson;
        val personList = daoPerson.getRecommendations(currentPerson.getId());

        try {
            listBlockPerson = daoPerson.getBlockedIds(currentPerson.getId());
        } catch (EmptyResultDataAccessException e) {
            listBlockPerson = Collections.emptyList();
        }

        List<Integer> finalListBlockPerson = listBlockPerson;

        if (personList.size() > 0 && personList.size() < 20) {
            val recommendOnRegDate = daoPerson.getRecommendationsOnRegDate(currentPerson.getId());
            for (int i = 0; i < 20 - personList.size(); i++) {
                personList.add(recommendOnRegDate.get(i));
            }

            return personList.stream().filter(person -> !finalListBlockPerson.contains(person.getId()))
                    .collect(Collectors.toSet()).stream()
                    .map(PersonDto::fromPerson).collect(Collectors.toList());
        }

        return personList.size() == 0 ? daoPerson.getRecommendationsOnRegDate(currentPerson.getId()).stream()
                .filter(person -> !finalListBlockPerson.contains(person.getId()))
                .map(PersonDto::fromPerson).collect(Collectors.toList()) :
                personList.stream().filter(person -> !finalListBlockPerson.contains(person.getId()))
                        .map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public MessageResponseDto addFriendForId(int id) {

        Person currentPerson = daoPerson.getAuthPerson();
        if (currentPerson.getId() == id) {
            throw new IllegalArgumentException("You can't add yourself as friend");
        }
        String friendStatus;
        try {
            friendStatus = daoPerson.getFriendStatus(currentPerson.getId(), id);
        } catch (EmptyResultDataAccessException ex) {
            friendStatus = null;
        }
        if (friendStatus == null) {
            int entityId = daoPerson.addFriendByIdAndReturnEntityId(currentPerson.getId(), id);
            daoNotification.addNotification(id, currentPerson.getId(), System.currentTimeMillis(), entityId,
                    daoPerson.getById(id).getEmail(), NotificationType.FRIEND_REQUEST.toString());
        } else if (friendStatus.equals(FriendshipStatus.REQUEST.toString())) {
            daoPerson.addFriendRequest(id, currentPerson.getId());
        } else if (friendStatus.equals(FriendshipStatus.DECLINED.toString())) {
            daoPerson.updateDeclined(currentPerson.getId(), id);
        }
        return new MessageResponseDto();
    }

    public MessageResponseDto deleteFriendForId(int id) {

        Person currentPerson = daoPerson.getAuthPerson();
        String friendStatus = daoPerson.getFriendStatus(currentPerson.getId(), id);
        if (friendStatus.equals(FriendshipStatus.FRIEND.toString())) {
            daoPerson.deleteFriendForID(currentPerson.getId(), id);
        } else if (friendStatus.equals(FriendshipStatus.REQUEST.toString())) {
            daoPerson.declineRequest(currentPerson.getId(), id);
        }
        return new MessageResponseDto();
    }
}
