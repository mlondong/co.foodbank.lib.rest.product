package co.com.foodbank.product.exception;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.exception
 *         23/06/2021
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param str
     */
    public ProductNotFoundException(String str) {
        super(str);
    }
}
