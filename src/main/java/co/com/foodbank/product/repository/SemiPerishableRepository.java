package co.com.foodbank.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.com.foodbank.product.v1.model.SemiPerishable;

@Repository
public interface SemiPerishableRepository
        extends MongoRepository<SemiPerishable, String> {

}
