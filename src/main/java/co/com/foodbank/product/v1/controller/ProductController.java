package co.com.foodbank.product.v1.controller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.com.foodbank.product.dto.IProduct;
import co.com.foodbank.product.dto.ProductDTO;
import co.com.foodbank.product.exception.ProductNotFoundException;
import co.com.foodbank.product.service.ProductService;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.v1.controller
 *         23/06/2021
 */
@Controller
public class ProductController {


    @Autowired
    private ProductService service;

    /**
     * Method to create Product.
     * 
     * @param dto
     * @param type
     * @return {@code IProduct}
     */
    public IProduct create(@Valid ProductDTO dto, String type)
            throws ProductNotFoundException {
        return service.create(dto, type);
    }

    /**
     * Method to update a Product.
     * 
     * @param dto
     * @param _id
     * @return {@code IProduct}
     */
    public IProduct update(@Valid ProductDTO dto, String _id)
            throws ProductNotFoundException {
        return service.update(dto, _id);
    }


    /**
     * Find all products.
     * 
     * @return {@code Collection<IProduct> }
     */
    public Collection<IProduct> findAll() throws ProductNotFoundException {
        return service.findAll();
    }



    /**
     * Find a Product.
     * 
     * @param _id
     * @return {@code IProduct}
     */
    public IProduct findById(@Valid String _id)
            throws ProductNotFoundException {
        return service.findById(_id);
    }

    /**
     * Method to find by name of product.
     * 
     * @param _name
     * @return {@code Collection<IProduct>}
     */
    public Collection<IProduct> findByName(@NotNull @NotBlank String _name)
            throws ProductNotFoundException {
        return service.findByName(_name);
    }

}
