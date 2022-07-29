package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "whiskey",2000),
            new Product(1L,"coca cola",120));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "coca cola", 120);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "pepsi", 150);
    final Product EXPECTED_SAVED_PRODUCT = new Product(2L, "juice", 200);
    final List<Product> EXPECTED_DELETE_PRODUCT = Arrays.asList(
            new Product(1L,"coca cola",120));
    private ProductsRepositoryJdbcImpl repository;

    @BeforeEach
    void init() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    void findByIdTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(1L).get());
    }

    @Test
    void updateTest() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(1L).get());
    }

    @Test
    void saveTest() {
        repository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(2L).get());
    }

    @Test
    void deleteTest() {
        repository.delete(0L);
        Assertions.assertEquals(EXPECTED_DELETE_PRODUCT, repository.findAll());
    }
}
