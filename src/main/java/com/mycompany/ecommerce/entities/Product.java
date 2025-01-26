package com.mycompany.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pId;
    private String pName;
    @Column(length = 3000)
    private String pDescription;
    private String productPic;
    private int pPrice;
    private int pDiscount;
    private int pQuantity;
    private Date date;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String pName, String pDescription, String productPic, int pPrice, int pDiscount, int pQuantity, Date date, Category category) {
        this.pName = pName;
        this.pDescription = pDescription;
        this.productPic = productPic;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pQuantity = pQuantity;
        this.date = date;
        this.category = category;
    }

    public Product(int pId, String pName, String pDescription, String productPic, int pPrice, int pDiscount, int pQuantity, Date date, Category category) {
        this.pId = pId;
        this.pName = pName;
        this.pDescription = pDescription;
        this.productPic = productPic;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pQuantity = pQuantity;
        this.date = date;
        this.category = category;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public int getpDiscount() {
        return pDiscount;
    }

    public void setpDiscount(int pDiscount) {
        this.pDiscount = pDiscount;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "pId=" + pId + ", pName=" + pName + ", pDescription=" + pDescription + ", productPic=" + productPic + ", pPrice=" + pPrice + ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + ", date=" + date + ", category=" + category + '}';
    }

    public int getPriceAfterDiscount(){
        int d = (int)((this.getpDiscount()/100.0)*this.getpPrice());
        return this.getpPrice()-d;
    }

    
}
