package org.example.manager;

import org.example.db.DBConnectionProvider;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Product product) {
        String query = "INSERT INTO product(name, description, price, quantity) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQty());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
            System.out.println("Successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getById(int id) {
        String query = "SELECT * FROM product WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                return new Product(id, name, description, price, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Product product) {
        if (getById(product.getId()) == null) {
            System.out.println("Product with id " + product.getId() + "does not exists!");
            return;
        }
        String query = "UPDATE product SET name=?, description=?, price=?, quantity=? WHERE id = " + product.getId();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQty());
            ps.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        if (getById(id) == null) {
            System.out.println("Product with id " + id + "does not exists!");
            return;
        }
        String query = "DELETE FROM product WHERE id = " + id;
        try (Statement statament = connection.createStatement()) {
            statament.executeUpdate(query);
            System.out.println("Successfully removed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getMaxOfPrice() {
        String query = "SELECT MAX(price) FROM product;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double getMixOfPrice() {
        String query = "SELECT MIN(price) FROM product;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getAllProductsQty() {
        String query = "SELECT SUM(quantity) FROM product;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double getAvgOfPriceProduct() {
        String query = "SELECT AVG(price) FROM product;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
