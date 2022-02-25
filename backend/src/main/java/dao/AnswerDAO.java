package dao;

import database.Database;

import pojo.Answer;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AnswerDAO {

    public List<Answer> getAnswers(int question_id) {
        List<Answer> answers = new ArrayList<>();

        Database db = new Database();
        String query_str =
            "SELECT *, DATE_FORMAT(date_created, '%d/%m/%Y') as date_formatted " +
            "FROM answer WHERE question_id = ?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement myStmt = conn.prepareStatement(query_str);
        ) {
            myStmt.setInt(1, question_id);
            ResultSet myRs = myStmt.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String body = myRs.getString("body");
                String date_created = myRs.getString("date_formatted");

                answers.add(new Answer(id, body, date_created));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answers;
    }
    

    public void postAnswer(String body, int question_id) {
        Database db = new Database();
        String query_str = "INSERT INTO answer (body, question_id) VALUES (?, ?)";

        try (
            Connection conn = db.getConnection();
            PreparedStatement myStmt = conn.prepareStatement(query_str);
        ) {
            myStmt.setString(1, body);
            myStmt.setInt(2, question_id);
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
