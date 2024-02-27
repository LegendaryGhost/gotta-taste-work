package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Recipe;

public class RecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            String title = req.getParameter("searchTitle");
            String minTimeStr = req.getParameter("searchMinCookTime");
            String maxTimeStr = req.getParameter("searchMaxCookTime");
            LocalTime minTime = null;
            LocalTime maxTime = null;

            if (title == null) {
                title = "";
            }

            if (minTimeStr != null && !minTimeStr.equals("")) {
                minTime = LocalTime.parse(minTimeStr);
            }

            if (maxTimeStr != null && !maxTimeStr.equals("")) {
                maxTime = LocalTime.parse(maxTimeStr);
            }

            if (action != null && action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Recipe recipe = new Recipe(id);
                recipe.delete();
            }

            ArrayList<Recipe> recipes = Recipe.search(title, minTime, maxTime);
            req.setAttribute("recipes", recipes);
            RequestDispatcher dispatcher = req.getRequestDispatcher("recipe.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("idRecipe"));
        String title = req.getParameter("recipeTitle");
        String description = req.getParameter("recipeDescription");
        int idCategory = Integer.parseInt(req.getParameter("recipeIdCategory"));
        LocalTime cookTime = LocalTime.parse(req.getParameter("recipeCookTime"));
        String createdBy = req.getParameter("recipeCreator");
        LocalDate createdDate = LocalDate.parse(req.getParameter("recipeCreationDate"));
        Recipe recipe = new Recipe(id, title, description, idCategory, cookTime, createdBy, createdDate);

        try {
            if (action != null && action.equals("update")) {
                recipe.update();
            } else {
                recipe.create();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("recipe");
    }

}