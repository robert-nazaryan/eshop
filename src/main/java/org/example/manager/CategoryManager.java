package org.example.manager;

import org.example.db.DBConnectionProvider;
import org.example.model.Category;
import org.example.model.Product;

import java.sql.*;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Category category) {
        String query = "INSERT INTO category(name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
            System.out.println("Successfully added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Category getById(int id) {
        String query = "SELECT * FROM category WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(Category category) {
        if (getById(category.getId()) == null) {
            System.out.println("Category with id " + category.getId() + "does not exists!");
            return;
        }
        String query = "UPDATE category SET name=? WHERE id = " + category.getId();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, category.getName());
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
        String query = "DELETE FROM category WHERE id = " + id;
        try (Statement statament = connection.createStatement()) {
            statament.executeUpdate(query);
            System.out.println("Successfully removed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
