package org.vaadin.example.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.vaadin.example.entity.User;

import java.util.Optional;

@ApplicationScoped
public class UserService {

    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    @Transactional
    public void register(User user) {
        entityManager.persist(user);
    }

    public Optional<User> findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    public Optional<User> login(String email, String password) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultStream()
                .findFirst();
    }
}
