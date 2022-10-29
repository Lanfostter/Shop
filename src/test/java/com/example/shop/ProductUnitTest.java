package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.impl.ProductServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductUnitTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    void getAll_productReturnList() {
        List<ProductEntity> mockbooks = new ArrayList<ProductEntity>();
        for (int i = 0; i < productRepository.findAll().size(); i++) {
            mockbooks.addAll(productRepository.findAll());
        }
        when(productRepository.findAll()).thenReturn((ArrayList<ProductEntity>) mockbooks);
    }

    @Test
    void whenAddOneProduct() {
        ProductEntity entity = new ProductEntity();
        entity.setName("Coca");
        entity.setImg("278994385_541580917525446_126785513975165332_n.png");
        when(productRepository.save(entity)).thenReturn(entity);
        ProductEntity testEntity = mock(ProductEntity.class);
        testEntity = entity;
        assertNotNull(productRepository.save(entity));
        verify(userRepository).save(testEntity);
        assertNotNull(testEntity.getId());
        assertNotNull(testEntity.getName());
        assertEquals(dateFormat.parse("12/12/2022"), entity.getBirthday());
    }

    @Test
    void findByName() {
        List<UserEntity> mockbooks = new ArrayList<UserEntity>();
        String name = "DucAnh";
        for (int i = 0; i < userRepository.findByName(name).size(); i++) {
            mockbooks.addAll(userRepository.findByName(name));
        }
        when(userRepository.findByName(name)).thenReturn((ArrayList<UserEntity>) mockbooks);
    }
}
