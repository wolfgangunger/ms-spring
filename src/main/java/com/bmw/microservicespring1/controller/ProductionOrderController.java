package com.bmw.microservicespring1.controller;

import com.bmw.microservicespring1.data.ProductionOrder;
import com.bmw.microservicespring1.service.ProductionOrderService;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * @author  UNGERW
 *
 */
@RestController
@RequestMapping("/productionorder")
public class ProductionOrderController {

	@Autowired
	private ProductionOrderService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductionOrder> findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> saveProductionOrder(@RequestBody ProductionOrder productionOrder, UriComponentsBuilder ucBuilder) {
		return service.saveProductionOrder(productionOrder, ucBuilder);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateProductionOrder(@RequestBody ProductionOrder ProductionOrder) {
		return service.updateProductionOrder(ProductionOrder);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteProductionOrderId(@PathVariable("id") Long id) {
		return service.deleteProductionOrderById(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductionOrder>> findAllProductionOrders() {
		return service.findAllProductionOrders();
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteAllSalesOrders() {
		return service.deleteAllProductionOrders();
	}

}
