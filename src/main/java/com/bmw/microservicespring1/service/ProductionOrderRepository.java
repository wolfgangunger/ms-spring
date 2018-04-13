/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmw.microservicespring1.service;

import com.bmw.microservicespring1.data.ProductionOrder;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author UNGERW
 */
public interface ProductionOrderRepository extends CrudRepository<ProductionOrder, Long>{
    
}
