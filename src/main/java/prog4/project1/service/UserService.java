package prog4.project1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prog4.project1.repository.UserRepository;
import prog4.project1.repository.entity.User;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByEmailAndPassword (String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void saveUser (User newUser){
        userRepository.save(newUser);
    }

    public User updateUser(User newUser){
        return userRepository.save(newUser);
    }

}
