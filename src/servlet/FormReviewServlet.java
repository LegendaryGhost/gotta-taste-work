package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Recipe;
import dao.Review;
import util.SessionUtils;

public class FormReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SessionUtils.isUserConnected(req)) {
            resp.sendRedirect("login");
            return;
        }

        String action = req.getParameter("action");
        Review review = new Review();
        ArrayList<Recipe> recipes;

        try {
            recipes = Recipe.all();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (action != null && action.equals("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            review.setId(id);
            try {
                review.find();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            action = "create";
            review.setIdUser(SessionUtils.getConnectedUser(req).getId());
        }

        req.setAttribute("action", action);
        req.setAttribute("review", review);
        req.setAttribute("recipes", recipes);

        RequestDispatcher dispatcher = req.getRequestDispatcher("form-review.jsp");
        dispatcher.forward(req, resp);
    }

}