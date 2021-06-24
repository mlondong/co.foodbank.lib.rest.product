package co.com.foodbank.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.com.foodbank.product.v1.model.NoNPerishable;

@Repository
public interface NoNPerishableRepository
        extends MongoRepository<NoNPerishable, String> {


}
