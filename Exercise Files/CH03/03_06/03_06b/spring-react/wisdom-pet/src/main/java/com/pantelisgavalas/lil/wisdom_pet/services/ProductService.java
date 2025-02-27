package com.pantelisgavalas.lil.wisdom_pet.services;

import com.pantelisgavalas.lil.wisdom_pet.data.entities.ProductEntity;
import com.pantelisgavalas.lil.wisdom_pet.data.repositories.ProductRepository;
import com.pantelisgavalas.lil.wisdom_pet.web.errors.NotFoundException;
import com.pantelisgavalas.lil.wisdom_pet.web.models.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET all Operation
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Iterable<ProductEntity> entities = this.productRepository.findAll();
        entities.forEach(entity -> {
            products.add(this.translateDbToWeb(entity));
        });
        return products;
    }

    // GET Operation
    public Product getProduct(long id) {
        Optional<ProductEntity> optional = this.productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product not found with id: " + id);
        }
        else {
            return this.translateDbToWeb(optional.get());
        }
    }

    // POST - PUT Operations
    public Product createOrUpdateProduct(Product product) {
        ProductEntity entity = this.translateWebToDb(product);
        entity = this.productRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    // DELETE Operation
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    // Helper methods for Web <--> DB data exchange
    private ProductEntity translateWebToDb(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getProductId()==null?0: product.getProductId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setVendorId(product.getVendorId());
        return entity;
    }

    private Product translateDbToWeb(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice(), entity.getVendorId());
    }
}
