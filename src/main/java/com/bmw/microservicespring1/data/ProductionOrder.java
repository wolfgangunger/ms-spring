package com.bmw.microservicespring1.data;

import com.bmw.microservicespring1.data.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UNGERW
 */
@Entity
@Table(name = "T_ProductionOrder")
public class ProductionOrder extends BaseEntity{
    
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    
    
}
