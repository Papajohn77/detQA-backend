package dao;

import database.Database;

import pojo.Category;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryDAO {

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        Database db = new Database();
        String query_str = "SELECT * FROM category";

        try (
            Connection conn = db.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(query_str);
            ResultSet rs = prepStmt.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                categories.add(new Category(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public Category findCategory(int category_id) {
        Category category = null;

        Database db = new Database();
        String query_str = "SELECT * FROM category WHERE id = ?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(query_str);
        ) {
            prepStmt.setInt(1, category_id);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                category = new Category(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }
}
