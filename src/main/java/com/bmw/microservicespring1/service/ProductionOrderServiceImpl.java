/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmw.microservicespring1.service;

import com.bmw.microservicespring1.data.ProductionOrder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author UNGERW
 */
@Service
public class ProductionOrderServiceImpl extends BaseService implements ProductionOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductionOrderServiceImpl.class);

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    @Override
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public ResponseEntity<ProductionOrder> findById(Long id) {
        ProductionOrder order = productionOrderRepository.findOne(id);
        return order == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(order, HttpStatus.OK);
    }

    public ResponseEntity<ProductionOrder> findByIdFallback(Long id, Throwable e) {
        LOGGER.error("Error in finding Sales Order with id {}", id);
        return new ResponseEntity<>(getHttpStatusCode(e));
    }

    @Override
    public ResponseEntity<Long> saveProductionOrder(ProductionOrder productionOrder, UriComponentsBuilder ucBuilder) {
        productionOrder.setId(null);
        saveOrUpdate(productionOrder);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/prodcutionorder/{id}").buildAndExpand(productionOrder.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateProductionOrder(ProductionOrder productionOrder) {
        if (productionOrderRepository.exists(productionOrder.getId())) {
            saveOrUpdate(productionOrder);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteProductionOrderById(Long id) {
        productionOrderRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @HystrixCommand(fallbackMethod = "findAllProductionOrdersFallback")
    public ResponseEntity<List<ProductionOrder>> findAllProductionOrders() {
        List<ProductionOrder> productionOrders = new ArrayList<>();
        productionOrderRepository.findAll().forEach(productionOrders::add);
        return productionOrders.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(productionOrders, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductionOrder>> findAllProductionOrdersFallback(Throwable e) {
        LOGGER.error("Error in finding all Sales Orders");
        return new ResponseEntity<>(getHttpStatusCode(e));
    }

    @Override
    public ResponseEntity<Void> deleteAllProductionOrders() {
        productionOrderRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void saveOrUpdate(ProductionOrder productionOrder) {
        productionOrderRepository.save(productionOrder);
        //productionOrder.getSalesOrderDetail().forEach(orderDetail -> orderDetail.setSalesOrderId(salesOrder.getSalesOrderId()));
        //salesOrderDetailRepository.save(salesOrder.getSalesOrderDetail());
    }

}
