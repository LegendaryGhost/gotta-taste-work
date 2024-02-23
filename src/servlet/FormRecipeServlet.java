package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Category;
import dao.Recipe;

public class FormRecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Recipe recipe = new Recipe();
        ArrayList<Category> categories;

        try {
            categories = Category.all();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (action != null && action.equals("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            recipe.setId(id);
            try {
                recipe.find();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            action = "create";
        }

        req.setAttribute("action", action);
        req.setAttribute("recipe", recipe);
        req.setAttribute("categories", categories);

        RequestDispatcher dispatcher = req.getRequestDispatcher("form-recipe.jsp");
        dispatcher.forward(req, resp);
    }

}