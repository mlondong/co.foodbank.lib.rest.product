package co.com.foodbank.product.v1.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.v1.model
 *         23/06/2021
 */
@Document(collection = "Product")
@TypeAlias("SemiPerishable")
public class SemiPerishable extends Product {

    private boolean requiredRefrigeration;

    /**
     * Default constructor.
     */
    public SemiPerishable() {}



    public SemiPerishable(boolean requiredRefrigeration) {
        super();
        this.requiredRefrigeration = requiredRefrigeration;
    }



    public boolean isRequiredRefrigeration() {
        return requiredRefrigeration;
    }

    public void setRequiredRefrigeration(boolean requiredRefrigeration) {
        this.requiredRefrigeration = requiredRefrigeration;
    }

}
