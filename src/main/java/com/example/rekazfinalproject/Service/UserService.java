package com.example.rekazfinalproject.Service;

import com.example.rekazfinalproject.Api.ApiException;
import com.example.rekazfinalproject.Model.User;
import com.example.rekazfinalproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //*** All CRUD Done by Danah ****

    //Get
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Add User
    public User addUser(User user) {
        return userRepository.save(user);
    }

    //Update User
    public void updateUser(Integer id ,User user) {
        User u = userRepository.findUserById(id);
        if(u == null) {
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        userRepository.save(u);
    }

    //Delete User
    public void deleteUser(Integer id) {
        User u = userRepository.findUserById(id);
        if(u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

}
