package co.com.foodbank.product.service;

import java.util.Collection;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.foodbank.product.dto.IProduct;
import co.com.foodbank.product.dto.ProductDTO;
import co.com.foodbank.product.exception.ProductNotFoundException;
import co.com.foodbank.product.repository.ProductRepository;
import co.com.foodbank.product.v1.model.NoNPerishable;
import co.com.foodbank.product.v1.model.Perishable;
import co.com.foodbank.product.v1.model.Product;
import co.com.foodbank.product.v1.model.SemiPerishable;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String NONPERISHABLE = "NONPERISHABLE";

    private static final String SEMIPERISHABLE = "SEMIPERISHABLE";

    private static final String PERISHABLE = "PERISHABLE";


    /**
     * Method to create Product.
     * 
     * @param dto
     * @param type
     * @return {@code IProduct}
     */
    public IProduct create(ProductDTO dto, String type)
            throws ProductNotFoundException {

        return repository.save(checkProduct(type, dto));
    }


    /**
     * Method to check type Product.
     * 
     * @param type
     * @param dto
     * @return {@code Product}
     */
    private Product checkProduct(String type, ProductDTO dto) {

        Product product = null;

        if (type.equals(NONPERISHABLE)) {
            product = modelMapper.map(dto, NoNPerishable.class);
        } else if (type.equals(SEMIPERISHABLE)) {
            product = modelMapper.map(dto, SemiPerishable.class);
        } else if (type.equals(PERISHABLE)) {
            product = modelMapper.map(dto, Perishable.class);
        }

        return product;
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

        Product dataDB = this.findById(_id);
        Product productUpdated = checkInstansOf(dataDB, dto);
        return repository.save(productUpdated);
    }



    /**
     * Check type of object.
     * 
     * @param dataDB
     * @param dto
     * @return {@code Product}
     */
    private Product checkInstansOf(Product dataDB, ProductDTO dto) {

        Product product = null;
        if (dataDB instanceof Perishable) {
            Perishable perishable = fillPerishable(dataDB, dto);
            product = perishable;
        } else if (dataDB instanceof NoNPerishable) {
            NoNPerishable noNPerishable = fillNoNPerisable(dataDB, dto);
            product = noNPerishable;
        } else if (dataDB instanceof SemiPerishable) {
            SemiPerishable semiPerishable = fillSemiPerisable(dataDB, dto);
            product = semiPerishable;
        }
        return product;
    }


    /**
     * Fill SemiPerishable.
     * 
     * @param dataDB
     * @param dto
     * @return {@code SemiPerishable}
     */
    private SemiPerishable fillSemiPerisable(Product dataDB, ProductDTO dto) {
        SemiPerishable semiPerishable =
                (SemiPerishable) fillProduct(dataDB, dto);
        semiPerishable.setRequiredRefrigeration(dto.isRequiredRefrigeration());
        return semiPerishable;
    }


    /**
     * Fill Product.
     * 
     * @param dataDB
     * @param dto
     * @return {@code Product}
     */
    private Product fillProduct(Product dataDB, ProductDTO dto) {
        Product prod = dataDB;
        prod.setName(dto.getName());
        prod.setBrand(dto.getBrand());
        prod.setDateExpiraton(dto.getDateExpiraton());
        prod.setDescription(dto.getDescription());
        prod.setId(dataDB.getId());
        return prod;
    }


    /**
     * Fill NoNPerishable.
     * 
     * @param dataDB
     * @param dto
     * @return {@code NoNPerishable}
     */
    private NoNPerishable fillNoNPerisable(Product dataDB, ProductDTO dto) {
        NoNPerishable noNPerishable = (NoNPerishable) fillProduct(dataDB, dto);
        return noNPerishable;
    }


    /**
     * Fill Perishable.
     * 
     * @param dataDB
     * @param dto
     * @return {@code Perishable}
     */
    private Perishable fillPerishable(Product dataDB, ProductDTO dto) {
        Perishable perishable = (Perishable) fillProduct(dataDB, dto);
        perishable.setExpectedStorageLife(
                Long.valueOf(dto.getExpectedStorageLife()));
        perishable.setStorageTemperature(
                Long.valueOf(dto.getStorageTemperature()));
        return perishable;
    }


    /**
     * Method to find Product by id.
     * 
     * @param id
     * @return {@code IProduct }
     */
    public Product findById(String id) throws ProductNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }



    /**
     * Method to find all Products.
     * 
     * @return {@code Collection<IProduct> }
     */
    public Collection<IProduct> findAll() throws ProductNotFoundException {
        return repository.findAll().stream()
                .map(d -> modelMapper.map(d, IProduct.class))
                .collect(Collectors.toList());
    }


    /**
     * Method to find by name products.
     * 
     * @param _name
     * @return {@code Collection<IProduct>}
     */
    public Collection<IProduct> findByName(String _name)
            throws ProductNotFoundException {
        return repository.findByName(_name);
    }



}
