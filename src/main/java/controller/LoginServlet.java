package controller;

import dao.AccountDAO;
import model.Account;
import model.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    RequestDispatcher dispatcher = null;
    AccountDAO accountDAO = new AccountDAO();
    List<Account> accounts = accountDAO.getAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                dispatcher = req.getRequestDispatcher("/view/login.jsp");
                dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int index = checkAccount(req);
        if (index != -1) {
            req.setAttribute("account", accounts.get(index));
            Login.account = accounts.get(index);
            resp.sendRedirect("/home");
        } else {
            resp.sendRedirect("/login");
        }


    }

    public int checkAccount(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUseName().equals(username)) {
                if (accounts.get(i).getPassWord().equals(password)) {
                    return i;
                }
            }

        }
        return -1;
    }
}

