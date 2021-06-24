package co.com.foodbank.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.com.foodbank.product.v1.model.Product;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.repository
 *         23/06/2021
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
