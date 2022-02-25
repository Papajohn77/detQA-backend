package servlets;

import com.google.gson.Gson;

import pojo.Answer;
import pojo.SubmittedAnswer;

import dao.AnswerDAO;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;


public class AnswerEndpoint extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allowCORS(response);
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allowCORS(response);

        SubmittedAnswer submittedAnswer = new Gson().fromJson(request.getReader(), SubmittedAnswer.class);
        new AnswerDAO().postAnswer(submittedAnswer.getBody(), submittedAnswer.getQuestionId());
	}

    private void allowCORS(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }
}
