package com.passionproject.cookbook.services;

import com.passionproject.cookbook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.passionproject.cookbook.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User addUser(User user) {
        return this.repo.save(user);
    }

    // https://www.javacodemonk.com/difference-between-getone-and-findbyid-in-spring-data-jpa-3a96c3ff
    public User getUser(Long id) {
        return this.repo.findById(id).orElse(null);
    }

    public Iterable<User> getAllUsers() {
        return this.repo.findAll();
    }

    public void deleteUser(Long id) {
        this.repo.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User userToUpdate = this.repo.findById(id).orElse(null);
        userToUpdate.setUsername(user.getUsername());
        //userToUpdate.setFavoriteRecipes(user.getFavoriteRecipes());
        userToUpdate.setPassword(user.getPassword());
        return this.repo.save(userToUpdate);
    }
}
