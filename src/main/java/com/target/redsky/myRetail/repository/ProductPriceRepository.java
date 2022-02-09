package com.target.redsky.myRetail.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.target.redsky.myRetail.entity.*;

/**
 * @author Prashant Vaikole
 *
 */



public interface ProductPriceRepository extends MongoRepository<ProductPrice, Long> {

	@SuppressWarnings("unchecked")
	public ProductPrice save(ProductPrice product);
	
	//public ProductPrice findById(long id);
	
	Optional<ProductPrice>  findById(long id);
	
	
	
		
}
