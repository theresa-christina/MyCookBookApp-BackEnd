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

    public User getUser(Long id) {
        return this.repo.getOne(id);
    }

    public void deleteUser(Long id) {
        this.repo.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User userToUpdate = this.repo.getOne(id);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setFavoriteRecipes(user.getFavoriteRecipes());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setUsersRecipes(user.getUsersRecipes());
        return this.repo.save(userToUpdate);
    }
}
