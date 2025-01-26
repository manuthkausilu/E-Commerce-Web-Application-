package com.mycompany.ecommerce.helper;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Helper {

    public static String get10Words(String description) {
        String[] strs = description.split(" ");

        if (strs.length > 10) {
            String res = "";
            for (int i = 0; i < 10; i++) {
                res = res + strs[i] + " ";
            }
            return res + "...";

        } else {
            return description + "...";
        }
    }

    public static Map<String, Integer> getCounts(SessionFactory factory) {
         Map<String, Integer> map = new HashMap<>();
         
        try (Session session = factory.openSession()) {
           
            String q1 = "select count(*) from User";
            Query<Long> query1 = session.createQuery(q1, Long.class);
            Long userCountResult = query1.getSingleResult();
            int userCount = userCountResult != null ? userCountResult.intValue() : 0;

            // Query to count Products
            String q2 = "select count(*) from Product";
            Query<Long> query2 = session.createQuery(q2, Long.class);
            Long productCountResult = query2.getSingleResult();
            int productCount = productCountResult != null ? productCountResult.intValue() : 0;

            map.put("userCount", userCount);
            map.put("productCount", productCount);

            return map;
        }
    }

}
