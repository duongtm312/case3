package controller;

import dao.AccountDAO;
import model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/Singup")
public class SignUpServlet extends HttpServlet {
    AccountDAO accountDAO = new AccountDAO();
    RequestDispatcher dispatcher = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher = req.getRequestDispatcher("/view/Singup.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String repassword = req.getParameter("confirm password");
        String email = req.getParameter("email");
        if (!password.equals(repassword)){
            dispatcher = req.getRequestDispatcher("/view/Singup.jsp");
            req.setAttribute("message", "Passwords do not match");

        } else{
            Account account = accountDAO.findById(name);
            if (account == null){
                accountDAO.create(new Account(name,password,email));
                resp.sendRedirect("/view/login.jsp");

            }else {
                dispatcher = req.getRequestDispatcher("/view/Singup.jsp");
                req.setAttribute("message", "Account already exists");

            }
        }
        dispatcher.forward(req,resp);

    }
}
