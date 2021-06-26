package co.com.foodbank.product.repository;

import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import co.com.foodbank.product.dto.IProduct;
import co.com.foodbank.product.exception.ProductNotFoundException;
import co.com.foodbank.product.v1.model.Product;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.repository
 *         23/06/2021
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{'name': {'$regex':'?0','$options':'i'}}")
    Collection<IProduct> findByName(String _name)
            throws ProductNotFoundException;
}
