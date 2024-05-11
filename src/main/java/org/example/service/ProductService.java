package org.example.service;

import org.example.dto.ProductDTO;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final FileStorageService fileStorageService;

    public ProductService(ProductRepository repository, CategoryService categoryService, FileStorageService fileStorageService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.fileStorageService = fileStorageService;
    }
    public String addProduct(String name, Long id, Double price, MultipartFile imageFile) {
        try {
            Product product = new Product();
            product.setName(name);

            Category category = categoryService.getCategoryById(id);
            product.setCategory(category);
            product.setPrice(price);

            String imagePath = fileStorageService.storeFile(imageFile);
            product.setImage(imagePath);

            repository.save(product);

            return "ADDED";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String add(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
//        try {
//            product.setImage(Base64.getEncoder().encodeToString(dto.getImage().getBytes()));
//        } catch (Exception e) {
//            return String.valueOf(e);
//        }
        product.setCategory(categoryService.getCategoryById(dto.getCategory()));
        repository.save(product);
        return "ADDED";
    }

    public List<Product> getAllProduct() {
        return repository.findAll();
    }
    public Product findById(Long id) throws Exception{
        Product product = (Product) repository.findById(id).orElseThrow(
                () -> new Exception("Product is not exist with given id:" + id)
        );
        return product;
    }
    public ProductDTO updateProduct(long id, ProductDTO productDTO) throws Exception {
        Product product = repository.findById((int) id).orElseThrow(
                () -> new Exception("Product is not exist with given id:" + id));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategory()));

        Product updatedProduct = repository.save(product);
        return org.example.dto.ProductMapper.mapToProductDTO(updatedProduct);
    }
}