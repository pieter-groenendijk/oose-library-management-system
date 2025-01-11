package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productRepository = new ProductRepository(sessionFactory);
    }

    @Test
    void testRetrieveProductCopyById() {
            long productCopyId = 1;

            ProductCopy mockProductCopy = new ProductCopy();
            mockProductCopy.setProductCopyId(productCopyId);

            when(sessionFactory.openSession()).thenReturn(session);
            when(session.get(ProductCopy.class, productCopyId)).thenReturn(mockProductCopy);

            Optional<ProductCopy> result = productRepository.retrieveProductCopyById(productCopyId);

            assertTrue(result.isPresent());
            assertEquals(productCopyId, result.get().getProductCopyId());

            verify(sessionFactory).openSession();
            verify(session).get(ProductCopy.class, productCopyId);
            verify(session).close();
        }
}
