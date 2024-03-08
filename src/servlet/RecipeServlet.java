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

import dao.Category;
import dao.Recipe;

public class RecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            
            if (action != null && action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Recipe recipe = new Recipe(id);
                recipe.delete();
            }

            ArrayList<Category> categories = Category.all();
            
            String title = req.getParameter("searchTitle") == null ? "" : req.getParameter("searchTitle");
            String description = req.getParameter("searchDescription") == null ? "" : req.getParameter("searchDescription");
            int idCategory = req.getParameter("searchIdCategory") == null ? 0 : Integer.parseInt(req.getParameter("searchIdCategory"));
            String minCookTimeStr = req.getParameter("searchMinCookTime");
            String maxCookTimeStr = req.getParameter("searchMaxCookTime");
            LocalTime minCookTime = null;
            LocalTime maxCookTime = null;
            String creator = req.getParameter("searchCreator") == null ? "" : req.getParameter("searchCreator");
            String minCreationDateStr = req.getParameter("searchMinCreationDate");
            String maxCreationDateStr = req.getParameter("searchMaxCreationDate");
            LocalDate minCreationDate = null;
            LocalDate maxCreationDate = null;
            
            if (minCookTimeStr != null && !minCookTimeStr.equals("")) {
                minCookTime = LocalTime.parse(minCookTimeStr);
            }
            
            if (maxCookTimeStr != null && !maxCookTimeStr.equals("")) {
                maxCookTime = LocalTime.parse(maxCookTimeStr);
            }
            
            if (minCreationDateStr != null && !minCreationDateStr.equals("")) {
                minCreationDate = LocalDate.parse(minCreationDateStr);
            }
            
            if (maxCreationDateStr != null && !maxCreationDateStr.equals("")) {
                maxCreationDate = LocalDate.parse(maxCreationDateStr);
            }

            ArrayList<Recipe> recipes = Recipe.search(title, description, idCategory, minCookTime, maxCookTime, creator, minCreationDate, maxCreationDate);
            req.setAttribute("recipes", recipes);
            req.setAttribute("categories", categories);
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