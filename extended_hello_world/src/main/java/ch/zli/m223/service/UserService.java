package ch.zli.m223.service;

import java.util.List;

import ch.zli.m223.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

        public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    // create user
    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    // user update
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existingUser = entityManager.find(User.class, id);
        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            return entityManager.merge(existingUser);
        }
        return null;
    }


    // delete user
    @Transactional
    public boolean deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;
    }

}