package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Step;

public class StepServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if (action != null && action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Step step = new Step(id);
                step.delete();
            }

            ArrayList<Step> steps = Step.all();
            req.setAttribute("steps", steps);
            RequestDispatcher dispatcher = req.getRequestDispatcher("step.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("idStep"));
        int idRecipe = Integer.parseInt(req.getParameter("stepIdRecipe"));
        int number = Integer.parseInt(req.getParameter("stepNumber"));
        String instruction = req.getParameter("stepInstruction");
        Step step = new Step(id, idRecipe, number, instruction);

        try {
            if (action != null && action.equals("update")) {
                step.update();
            } else {
                step.create();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("step");
    }

}