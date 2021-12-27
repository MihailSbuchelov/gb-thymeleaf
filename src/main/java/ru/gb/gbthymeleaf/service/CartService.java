package ru.gb.gbthymeleaf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.dao.CartDao;
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

    @Transactional
    public void addProduct(Product product) {
        cart.addProduct(product);
        if (cart.getStatus().equals(CartStatus.EMPTY.getTitle())) {
            cart.setStatus(CartStatus.NOT_EMPTY.getTitle());
        }
        cartDao.save(cart);
    }

    @Transactional
    public void delProduct(Product product) {
        Set<Product> productSet = cart.getProducts();
        Iterator<Product> itr = productSet.iterator();
        while (itr.hasNext()) {
            Product p = itr.next();
            if (p.getId() == product.getId()) {
                itr.remove();
                return;
            }
        }
        cart.setProducts(productSet);
        if (cart.getProducts().isEmpty()) {
            cart.setStatus(CartStatus.EMPTY.getTitle());
        }
        cartDao.save(cart);
    }

    @Transactional
    public Set<Product> getSetProducts() {
        if (cart.getId() == null) {
            cart.setStatus(CartStatus.EMPTY.getTitle());
            cartDao.save(cart);
        }
        return cart.getProducts();
    }

    public Long getCurCartId() {
        return cart.getId();
    }
}
