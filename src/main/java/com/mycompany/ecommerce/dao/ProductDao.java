package com.mycompany.ecommerce.dao;

import com.mycompany.ecommerce.entities.Product;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class ProductDao {

    private final SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }

    public void saveProduct(Product product) throws HibernateException, Exception {

        Transaction tx = null;

        try (Session session = this.factory.openSession()) {

            tx = session.beginTransaction();

            session.persist(product);

            tx.commit();

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

    //get all products
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        try (Session session = this.factory.openSession()) {

            TypedQuery<Product> query = session.createQuery("from Product", Product.class);
            products = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    
    //get all products by category-id
    public List<Product> getProductsByCategoryId(int categoryId) {

        List<Product> products = new ArrayList<>();

        try (Session session = this.factory.openSession()) {

            TypedQuery<Product> query = session.createQuery("from Product where category.categoryId=: id", Product.class);
            query.setParameter("id", categoryId);
            products = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

}
