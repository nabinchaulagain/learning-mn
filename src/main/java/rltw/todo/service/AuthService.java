package rltw.todo.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import rltw.todo.dto.SignupPayload;
import rltw.todo.error.AuthException;
import rltw.todo.error.NotFoundException;
import rltw.todo.model.User;
import rltw.todo.repository.UserRepository;
import rltw.todo.util.PasswordEncoder;

import java.util.Optional;

@Singleton
public class AuthService {
    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    public User createUser(SignupPayload payload) throws AuthException{
        if(doesUsernameExist(payload.getUsername())){
            throw new AuthException("Username is already taken");
        }

        String hashedPassword = passwordEncoder.hash(payload.getPassword());

        return userRepository.save(new User(payload.getUsername(),hashedPassword));
    }

    public boolean doesUsernameExist(String username){
        return userRepository.existsByUsername(username);
    }

    public User getUserByUsername(String username) throws NotFoundException{
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isEmpty()){
            throw new NotFoundException("User");
        }

        return optionalUser.get();
    }
}
