package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Recipe;
import dao.Step;

public class FormStepServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Step step = new Step();
        ArrayList<Recipe> recipes;

        try {
            recipes = Recipe.all();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (action != null && action.equals("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            step.setId(id);
            try {
                step.find();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            action = "create";
        }

        req.setAttribute("action", action);
        req.setAttribute("step", step);
        req.setAttribute("recipes", recipes);

        RequestDispatcher dispatcher = req.getRequestDispatcher("form-step.jsp");
        dispatcher.forward(req, resp);
    }

}