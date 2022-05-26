package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void addUser(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteUser(int id) {
        try {
            User user = getEntityManager().find(User.class, id);
            if (user != null) {
                getEntityManager().remove(user);
            }
        } catch (NullPointerException e) {
            System.out.println("User с указанным вами id не существует!");
        }
    }

    @Override
    public void editUser(User user) {
        getEntityManager().merge(user);
    }

    @Override
    public User getUserById(int id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return getEntityManager()
                .createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        return getEntityManager()
                .createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();

    }
}
