package com.example.shop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

import com.example.shop.entity.CategoryEntity;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CategoryRepository;
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
    @Mock
    CategoryRepository categoryRepository;

    @Test
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
        entity.setQuantity(100);
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        CategoryEntity categoryEntity = new CategoryEntity();
        for (int i = 0; i < categoryEntities.size(); i++) {
            if (categoryEntities.get(i).getName().equals("Do uong")) {
                categoryEntity = categoryEntities.get(i);
            }
        }
        entity.setCategoryEntity(categoryEntity);
        when(productRepository.save(entity)).thenReturn(entity);
        ProductEntity testEntity = mock(ProductEntity.class);
        testEntity = entity;
        assertNotNull(productRepository.save(entity));
        verify(productRepository).save(testEntity);
        assertNotNull(testEntity.getId());
        assertNotNull(testEntity.getName());
    }
}
