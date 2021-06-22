package computerplus.com.pl.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import computerplus.com.pl.exceptions.NotFoundException;
import computerplus.com.pl.models.User;
import computerplus.com.pl.repositiries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity saveUser(User user) {
            userRepository.save(user);
            return ResponseEntity.ok().build();
    }

    public void deleteUserFromDb(UUID id) throws NotFoundException {
        userRepository.findById(id).
                map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new NotFoundException("User with id " + id + "  not found"));
    }

    public List<User> findUserByLastName(User user) {
        String userLastName = user.getLastname();
        List<User> users = userRepository.findAll();
        List<User> resultUsers = new ArrayList<User>();
        users.forEach(foundUser -> {
            if (userLastName.equals(foundUser.getLastname())) {
                userRepository.findById(foundUser.getId())
                        .map(resultUser -> {
                            resultUsers.add(resultUser);
                            return resultUsers;
                        });
            }
        });
        return resultUsers;
    }

    public List<User> findUserByName(User user) {
        String userFirstName = user.getFirstname();
        List<User> users = userRepository.findAll();
        List<User> resultUsers = new ArrayList<User>();
        users.forEach(findUser -> {
            if (userFirstName.equals(findUser.getFirstname())) {
                userRepository.findById(findUser.getId())
                        .map(resultUser -> {
                            resultUsers.add(resultUser);
                            return resultUsers;
                        });
            }
        });
        return resultUsers;
    }

    public Optional<User> findUserById(UUID userId) throws NotFoundException {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
