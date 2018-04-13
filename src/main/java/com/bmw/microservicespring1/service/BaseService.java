/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmw.microservicespring1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author UNGERW
 */
public class BaseService {
    	private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

	protected HttpStatus getHttpStatusCode(Throwable error) {
		LOGGER.debug("Error on processiong your request : {}", error);
		LOGGER.info("Error on processiong your request : {} instance of : {}", error.getMessage(), error.getClass());
		if (error instanceof JpaObjectRetrievalFailureException) {
			return HttpStatus.REQUEST_TIMEOUT;
		} else if (error instanceof JpaObjectRetrievalFailureException) {
			return HttpStatus.NOT_FOUND;
		} else {
			if (error instanceof HttpStatusCodeException) {
				HttpStatus status = ((HttpStatusCodeException) error).getStatusCode();
				if (status == HttpStatus.UNAUTHORIZED || status == HttpStatus.NOT_FOUND || status == HttpStatus.NOT_IMPLEMENTED || status == HttpStatus.BAD_REQUEST) {
					return status;
				}
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}
