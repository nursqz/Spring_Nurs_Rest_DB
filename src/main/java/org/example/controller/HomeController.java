package org.example.controller;


import org.example.entity.Pharmacist;
import org.example.repository.PharmacistRepository;
import org.example.service.LogeedUserService;
import org.example.service.PharmacistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private final LogeedUserService logeedUserService;
    private final PharmacistRepository pharmacistRepository;
    private final PharmacistService pharmacistService;
    public HomeController(LogeedUserService logeedUserService, LogeedUserService logeedUserService1, PharmacistRepository pharmacistRepository, PharmacistService pharmacistService){
        this.logeedUserService = logeedUserService1;
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacistService = pharmacistService;
    }
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String Home(@RequestParam(required = false) String logout, Model model) {
        if(logout != null) {
            logeedUserService.setUserEmail(null);
        }
        String userEmail = logeedUserService.getUserEmail();
        if (userEmail == null) {
            return "redirect:/login";
        }

        model.addAttribute("pharmacist",pharmacistRepository.findAll());
        model.addAttribute("userEmail", userEmail);
        return "index";
    }
//
//    @RequestMapping(value = "/product",method = RequestMethod.GET)
//    public String getAllProduct(Model model) {
//        List<Product> productsList = productService.getAllProduct();
//        model.addAttribute("products", productsList);
//        return "index";
//    }
//    @RequestMapping(value = "/productadd",method = RequestMethod.GET)
//    public String ShowProduct(){
//        return "product";
//    }
//    @RequestMapping(value = "/productadd",method = RequestMethod.POST)
//    public String addProduct(@RequestParam("productName") String name,@RequestParam("catid") Long id,@RequestParam("price") Double price, Model model) {
//        String result = productService.addProduct(name,id,price);
//        if (result.equals("ADDED")) {
//            return "redirect:/index";
//        } else {
//            model.addAttribute("error", "error");
//            return "product";
//        }
//    }
//    @RequestMapping(value = "/categoryadd",method = RequestMethod.GET)
//    public String show(){
//        return "category";
//    }
//    @RequestMapping(value = "/categoryadd",method = RequestMethod.POST)
//    public String addCategory(@RequestParam("id") Long id,@RequestParam("categoryName") String CatName,Model model) {
//        String result= categoryService.addCategory(id,CatName);
//        if (result.equals("ADDED")) {
//            return "redirect:/index";
//        } else {
//            model.addAttribute("error", "error");
//            return "category";
//        }
//    }
//
//    @RequestMapping(value = "/category",method = RequestMethod.GET)
//    public String getAllCategory(Model model) {
//        List<Category> categoryList = categoryService.getAllCategory();
//        model.addAttribute("category", categoryList);
//        return "index";
//    }
}