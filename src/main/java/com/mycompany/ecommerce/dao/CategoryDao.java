package com.mycompany.ecommerce.dao;

import com.mycompany.ecommerce.entities.Category;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
public class CategoryDao {

    private SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }

    //save category to database
    public void saveCategory(Category category) throws ConstraintViolationException, HibernateException, Exception {

        Transaction tx = null;

        try (Session session = this.factory.openSession()) {

            tx = session.beginTransaction();

            session.persist(category);

            tx.commit();

        } catch (ConstraintViolationException e) {

            if (tx != null) {
                tx.rollback();
            }
            throw e; // Re-throwing to handle at a higher level
            
        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            throw e; // Re-throwing to handle at a higher level

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            throw e; // Re-throwing to handle at a higher level

        }
    }

    public List<Category> getCategories() {

        List<Category> categories = new ArrayList<>();

        try (Session session = this.factory.openSession()) {

            TypedQuery<Category> query = session.createQuery("from Category", Category.class);
            categories = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public Category getCategoryByCatId(int catId) {

        Category category = null;

        try (Session session = this.factory.openSession()) {

            category = session.get(Category.class, catId);

        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

}
