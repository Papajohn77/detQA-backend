package dao;

import database.Database;

import pojo.Question;
import pojo.Suggestion;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QuestionDAO {

    public List<Question> getQuestions(int start, int resultsPerPage) {
        List<Question> questions = new ArrayList<>();

        Database db = new Database();
        String query_str = 
            "SELECT *, DATE_FORMAT(date_created, '%d/%m/%Y') as date_formatted " + 
            "FROM question ORDER BY id DESC LIMIT ?, ?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(query_str);
        ) {
            prepStmt.setInt(1, start);
            prepStmt.setInt(2, resultsPerPage);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date_created = rs.getString("date_formatted");
                int category_id = rs.getInt("category_id");

                questions.add(new Question(id, title, body, date_created, category_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public List<Question> getSearchedQuestions(int start, int resultsPerPage, String searchString) {
        List<Question> questions = new ArrayList<>();

        Database db = new Database();
        String query_str =
            "SELECT *, DATE_FORMAT(date_created, '%d/%m/%Y') as date_formatted " + 
            "FROM question WHERE title LIKE ? LIMIT ?, ?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(query_str);
        ) {
            prepStmt.setString(1, "%" + searchString + "%");
            prepStmt.setInt(2, start);
            prepStmt.setInt(3, resultsPerPage);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date_created = rs.getString("date_formatted");
                int category_id = rs.getInt("category_id");

                questions.add(new Question(id, title, body, date_created, category_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public Question getQuestion(int question_id) {
        Question question = null;

        Database db = new Database();
        String query_str = 
            "SELECT *, DATE_FORMAT(date_created, '%d/%m/%Y') as date_formatted " + 
            "FROM question WHERE id = ?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(query_str);
        ) {
            prepStmt.setInt(1, question_id);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String date_created = rs.getString("date_formatted");
                int category_id = rs.getInt("category_id");

                question = new Question(id, title, body, date_created, category_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }

    public List<Suggestion> getSuggestions(String searchString) {
        List<Suggestion> suggestions = new ArrayList<>();

        Database db = new Database();
        String query_str = "SELECT title FROM question WHERE title LIKE ?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(query_str);
        ) {
            prepStmt.setString(1, searchString + "%");
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                suggestions.add(new Suggestion(title));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suggestions;
    }

    public void postQuestion(String title, String body, String category_id) {
        Database db = new Database();
        String query_str =
            "INSERT INTO question (title, body, category_id) VALUES (?, ?, ?)";

        try (
            Connection conn = db.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement(query_str);
        ) {
            prepStmt.setString(1, title);
            prepStmt.setString(2, body);
            prepStmt.setInt(3, Integer.parseInt(category_id));
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
