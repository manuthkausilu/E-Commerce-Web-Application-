package com.mycompany.ecommerce.dao;

import com.mycompany.ecommerce.entities.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDao {

    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    public User getUserByEmailAndPassword(String email, String password) {

        User user = null;

        try (Session session = this.factory.openSession()) {

            String query = "from User where userEmail =: e and userPassword =: p";

            TypedQuery q = session.createQuery(query, User.class);
            q.setParameter("e", email);
            q.setParameter("p", password);

            user = (User) q.getSingleResult();

        } catch (HibernateException e) {
            e.printStackTrace();
            user=null;
        } catch (Exception e) {
            e.printStackTrace();
            user=null;
        }

        return user;
    }
}
