package servlets;

import com.google.gson.Gson;

import pojo.Question;
import pojo.SubmittedQuestion;

import dao.QuestionDAO;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


public class QuestionEndpoint extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allowCORS(response);
    }

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allowCORS(response);

        int question_id = Integer.parseInt(request.getParameter("id"));
        Question question = new QuestionDAO().getQuestion(question_id);
        response.getWriter().write(new Gson().toJson(question));
        }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allowCORS(response);

        SubmittedQuestion submittedQuestion = new Gson().fromJson(request.getReader(), SubmittedQuestion.class);
        new QuestionDAO().postQuestion(submittedQuestion.getTitle(), submittedQuestion.getBody(), submittedQuestion.getCategory());
        }

    private void allowCORS(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }
}
