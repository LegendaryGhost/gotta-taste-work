package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Review;

public class ReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if (action != null && action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Review review = new Review(id);
                review.delete();
            }

            ArrayList<Review> reviews = Review.all();
            req.setAttribute("reviews", reviews);
            RequestDispatcher dispatcher = req.getRequestDispatcher("review.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("idReview"));
        int idUser = Integer.parseInt(req.getParameter("reviewIdUser"));
        int idRecipe = Integer.parseInt(req.getParameter("reviewIdRecipe"));
        int rating = Integer.parseInt(req.getParameter("reviewRating"));
        String comment = req.getParameter("reviewComment");
        Review review = new Review(id, idUser, idRecipe, rating, comment);

        try {
            if (action != null && action.equals("update")) {
                review.update();
            } else {
                review.create();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("review");
    }

}