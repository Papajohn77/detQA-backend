package servlets;

import com.google.gson.Gson;

import pojo.Question;

import dao.QuestionDAO;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


public class QuestionsEndpoint extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int QUESTIONS_PER_PAGE = 3;

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allowCORS(response);

        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questions = new ArrayList<>();

        int page = Integer.parseInt(request.getParameter("page"));
        int start = (page - 1) * QUESTIONS_PER_PAGE;

        String searchString = request.getParameter("search");

        if (searchString != null) {
            questions = questionDAO.getSearchedQuestions(start, QUESTIONS_PER_PAGE + 1, searchString);
        } else {
            questions = questionDAO.getQuestions(start, QUESTIONS_PER_PAGE + 1);
        }

        response.getWriter().write(new Gson().toJson(questions));
        }

    private void allowCORS(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }
}
