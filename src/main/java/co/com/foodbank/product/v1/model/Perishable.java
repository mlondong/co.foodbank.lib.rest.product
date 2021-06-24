package co.com.foodbank.product.v1.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.v1.model
 *         23/06/2021
 */
@Document(collection = "Product")
@TypeAlias("Perishable")
public class Perishable extends Product {


    private Long expectedStorageLife;
    private Long storageTemperature;

    /**
     * Default constructor.
     */
    public Perishable() {}



    public Perishable(Long expectedStorageLife, Long storageTemperature) {
        super();
        this.expectedStorageLife = expectedStorageLife;
        this.storageTemperature = storageTemperature;

    }



    public Long getExpectedStorageLife() {
        return expectedStorageLife;
    }

    public void setExpectedStorageLife(Long expectedStorageLife) {
        this.expectedStorageLife = expectedStorageLife;
    }

    public Long getStorageTemperature() {
        return storageTemperature;
    }

    public void setStorageTemperature(Long storageTemperature) {
        this.storageTemperature = storageTemperature;
    }



}
