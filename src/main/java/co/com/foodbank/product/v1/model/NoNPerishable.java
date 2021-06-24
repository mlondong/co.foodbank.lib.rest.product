package co.com.foodbank.product.v1.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.v1.model
 *         23/06/2021
 */
@Document(collection = "Product")
@TypeAlias("NoNPerishable")
public class NoNPerishable extends Product {

    /**
     * Default constructor.
     */
    public NoNPerishable() {}
}
