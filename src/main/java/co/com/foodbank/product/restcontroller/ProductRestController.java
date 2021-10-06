package co.com.foodbank.product.restcontroller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.foodbank.product.dto.ProductDTO;
import co.com.foodbank.product.dto.interfaces.IProduct;
import co.com.foodbank.product.exception.ProductNotFoundException;
import co.com.foodbank.product.v1.controller.ProductController;
import co.com.foodbank.product.v1.model.Product;
import co.com.foodbank.validaton.ValidTypeProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.product.restcontroller
 *         23/06/2021
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/product")
@Tag(name = "Product", description = "the product API")
@Validated
public class ProductRestController {


    @Autowired
    private ProductController controller;



    /**
     * Create a Product.
     * 
     * @param dto
     * @return {@code ResponseEntity<IProduct>}
     */
    @Operation(
            summary = "Add a new Product options:  NonPerishable, SemiPerishable, Perishable",
            description = "  NonPerishable, SemiPerishable, Perishable",
            tags = {"product"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Product created",
                            content = @Content(schema = @Schema(
                                    implementation = Product.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Product already exists")})

    @PostMapping(value = "/create/{type}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IProduct> create(@RequestBody @Valid ProductDTO dto,
            @PathVariable("type") @ValidTypeProduct String type)
            throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(controller.create(dto, type));
    }



    /**
     * Update a Product.
     * 
     * @param dto
     * @return {@code ResponseEntity<IProduct>}
     */
    @Operation(summary = "Update a Product", description = "",
            tags = {"product"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Product updated",
                            content = @Content(schema = @Schema(
                                    implementation = Product.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Product already exists")})

    @PutMapping(value = "/update/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IProduct> update(@RequestBody @Valid ProductDTO dto,
            @PathVariable("id") @NotNull @NotBlank String _id)
            throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.update(dto, _id));
    }



    /**
     * Method to find products by name.
     * 
     * @return {@code ResponseEntity<Collection<IProduct>>}
     */
    @Operation(summary = "Search products by Name.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the product",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/searchByName/{name}")
    public ResponseEntity<Collection<IProduct>> searchByName(
            @PathVariable("name") @NotNull @NotBlank String _name)
            throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.findByName(_name));
    }



    /**
     * Method to find all products.
     * 
     * @return {@code ResponseEntity<Collection<IProduct>>}
     */
    @Operation(summary = "Find all products.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the product",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/findAll")
    public ResponseEntity<Collection<IProduct>> findByAll()
            throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(controller.findAll());
    }


    /**
     * Method to find a products.
     * 
     * @return {@code ResponseEntity<Collection<IProduct>>}
     */
    @Operation(summary = "Find a product.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Found the product",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/findBy/{id}")
    public ResponseEntity<IProduct> findById(
            @Valid @PathVariable("id") String _id)
            throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.findById(_id));
    }



}
