package com.casestudy.ecart.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Min;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Component
@Entity
@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    // private fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @NotBlank(message = "Please enter the product name!")
    private String name;
    @NotBlank(message = "Please enter the brand name!")
    private String brand;
    @NotBlank(message = "Please enter the URL of image!")
    private String imgsrc;
    @NotBlank(message = "Please enter the description!")
    private String description;
    @Column(name = "unit_price")
    @Min(value = 1, message="Please select at least one value!")
    private double unitPrice;
    private int quantity;
    @Column(name = "Category")
    private String category;
    @Column(name = "supplier_id")
    private int supplierId;


    // default constructor
    public Items() {

        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();

    }


    // setters and getters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    public String getImgsrc() {return imgsrc;}
    public void setImgsrc(String imgsrc) {this.imgsrc = imgsrc;}

    // toString for debugging
    @Override
    public String toString() {
        return "Product [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + ", description="
                + description + ", imgsrc=" + imgsrc + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", category=" + category + ", supplierId=" + supplierId + ", views="
                + "]";
    }
}

