package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Ingredient;

public class IngredientServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if (action != null && action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Ingredient ingredient = new Ingredient(id);
                ingredient.delete();
            }

            ArrayList<Ingredient> ingredients = Ingredient.all();
            req.setAttribute("ingredients", ingredients);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ingredient.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("idIngredient"));
        String name = req.getParameter("ingredientName");
        String unit = req.getParameter("ingredientUnit");
        Ingredient ingredient = new Ingredient(id, name, unit);
        
        try {
            if (action != null && action.equals("update")) {
                ingredient.update();
            } else {
                ingredient.create();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("ingredient");
    }

}
