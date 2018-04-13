/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmw.microservicespring1.service;

import com.bmw.microservicespring1.data.ProductionOrder;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author UNGERW
 */
public interface ProductionOrderService {
    
    	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	ResponseEntity<ProductionOrder> findById(Long id);

	ResponseEntity<Long> saveProductionOrder(ProductionOrder productionOrder, UriComponentsBuilder ucBuilder);

	ResponseEntity<Void> updateProductionOrder(ProductionOrder productionOrder);

	ResponseEntity<Void> deleteProductionOrderById(Long id);

	ResponseEntity<List<ProductionOrder>> findAllProductionOrders();

	ResponseEntity<Void> deleteAllProductionOrders();
    
}
