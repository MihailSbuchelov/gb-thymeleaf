package ru.gb.gbthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.Product;

import java.util.Set;

public interface CartDao extends JpaRepository<Cart, Long> {
    Set<Product> findAllByProducts(Cart cart);

}
