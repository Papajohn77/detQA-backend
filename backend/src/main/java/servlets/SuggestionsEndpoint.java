package servlets;

import com.google.gson.Gson;

import pojo.Suggestion;

import dao.QuestionDAO;

import java.util.List;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


public class SuggestionsEndpoint extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allowCORS(response);

        String searchString = request.getParameter("search");
        List<Suggestion> suggestions = new QuestionDAO().getSuggestions(searchString);
        response.getWriter().write(new Gson().toJson(suggestions));
        }

    private void allowCORS(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }
}
