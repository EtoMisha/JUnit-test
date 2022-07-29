package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductRepository {

    private final DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM products;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> optionalProduct;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {

            String query = String.format("SELECT * FROM products WHERE id = %d;", id);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            optionalProduct = Optional.of(new Product(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)));
            return optionalProduct;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        String query = String.format("UPDATE products SET name = '%s', price = %d WHERE id = %d;",
                product.getName(), product.getPrice(), product.getId());

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        String query = String.format("INSERT into products (name, price) VALUES ('%s', %d);",
                product.getName(), product.getPrice());

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = String.format("DELETE FROM products WHERE id = %d;", id);

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
