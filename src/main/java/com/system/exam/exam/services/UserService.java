package com.system.exam.exam.services;

import com.system.exam.exam.entities.User;
import com.system.exam.exam.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user){
        return this.userRepo.save(user);
    }

    public User getUserByUsername(String username){
        return this.userRepo.findByUsername(username);
    }
}
