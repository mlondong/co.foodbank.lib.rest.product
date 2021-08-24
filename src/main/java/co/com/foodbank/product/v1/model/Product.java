package co.com.foodbank.product.v1.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import co.com.foodbank.product.dto.interfaces.IProduct;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.v1.model
 *         23/06/2021
 */
@Document(collection = "Product")
public class Product implements IProduct {

    @Id
    private String id;
    private Date dateExpiraton;
    private String name;
    private String description;
    private String brand;


    /**
     * Default constructor.
     */
    public Product() {}


    public void setId(String id) {
        this.id = id;
    }

    public void setDateExpiraton(Date dateExpiraton) {
        this.dateExpiraton = dateExpiraton;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getDateExpiraton() {
        return dateExpiraton;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getBrand() {
        return brand;
    }



}
