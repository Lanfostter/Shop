package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shop.entity.Cart;
import com.example.shop.entity.CartItem;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CartItemRepository;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CartItemUnitTest {
    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    CartRepository cartRepository;
    @Mock
    UserRepository userRepository;

    @Test
    void add_cartItem() {
        Cart cart = mock(Cart.class);
        cart.setUserEntity(userRepository.findByUsername("DucAnh"));
        cart.setBuyDate(new Date());
        cart.setPayup(false);
        cart.setTotalprice(0);
        cartRepository.save(cart);
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        String productname = "Rau sạch";
        ProductEntity entity = new ProductEntity();
        for (ProductEntity productEntity : productRepository.findAll()) {
            if (productEntity.getName().equals(productname)) {
                entity = productEntity;
            }
        }
        cartItem.setProductEntity(entity);
        cartItem.setCart(cart);
        CartItem cartItemTest = mock(CartItem.class);
        cartItemTest = cartItem;

        assertNull(cartItemRepository.save(cartItemTest));
        assertNotNull(cartItemTest.getQuantity());
        verify(cartItemRepository).save(cartItemTest);
    }
}
