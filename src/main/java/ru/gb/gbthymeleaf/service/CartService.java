package ru.gb.gbthymeleaf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.gbthymeleaf.dao.CartDao;
import ru.gb.gbthymeleaf.dao.ProductDao;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.Product;
import ru.gb.gbthymeleaf.entity.enums.CartStatus;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    @Autowired
    private CartDao cartDao;
    private Cart cart = new Cart();


    public void addProduct(Product product) {
        cart.addProduct(product);
        cartDao.save(cart);
    }

    public void delProduct(Product product, Long cartId) {
        Cart cart = cartDao.findById(cartId).get();
        cart.getProducts().remove(product);
        if(cart.getProducts().isEmpty()){
            cart.setStatus(CartStatus.EMPTY.getTitle());
        }
        cartDao.save(cart);
    }

    public Set<Product> getSetProducts() {
        return cartDao.findById(cart.getId()).get().getProducts();
    }

    public Long getCurCartId() {
        return cart.getId();
    }
}
