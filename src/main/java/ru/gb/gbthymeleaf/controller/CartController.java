package ru.gb.gbthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbthymeleaf.service.CartService;
import ru.gb.gbthymeleaf.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/all")
    public String findAll(Model model) {
        model.addAttribute("products", cartService.getSetProducts());
        return "cart";
    }

    @GetMapping
    public String addProduct(@RequestParam(name = "id", required = false) Long id) {
        cartService.addProduct(productService.findById(id));
        return "redirect:/cart/all";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        cartService.delProduct(productService.findById(id), cartService.getCurCartId());
        return "redirect:/cart/all";
    }
}
