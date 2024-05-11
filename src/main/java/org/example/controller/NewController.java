package org.example.controller;

import jakarta.annotation.Resource;
import org.example.dto.*;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.repository.CategoryRepository;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/shop")
@CrossOrigin(origins = "http://localhost:3000")
public class NewController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public NewController(ProductService productService, CategoryService categoryService, UserService userService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @PostMapping("/addproduct")
    public String addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("category") Long categoryId,
            @RequestParam("image") MultipartFile imageFile) {
        String result = productService.addProduct(name, categoryId, price, imageFile);
        if (result.equals("ADDED")) {
            return "succes";
        } else {
            return "Error";
        }
    }

    @GetMapping("/category")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping("/addcategory")
    public String addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/user")
    public List<User> users() {
        return userService.getUsers();
    }

    @PostMapping("/adduser")
    public String adduser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<ProductDTO> updateProd(@PathVariable("id") Long id,
                                                 @RequestBody ProductDTO updateprod) throws Exception {
        ProductDTO productDTO = productService.updateProduct(id, updateprod);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id) throws Exception {
        return productService.findById(id);
    }

    @GetMapping("/product-cat/{id}")
    public ProductDTO getProductByIdWithCat(@PathVariable Long id) throws Exception {
        Product byId = productService.findById(id);
        ProductDTO dto = ProductMapper.mapToProductDTO(byId);
        dto.setCategoryStr(categoryService.getCategoryById(dto.getCategory()).getName());
        return dto;
    }
}