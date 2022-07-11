package controller;

import dao.FriendsDAO;
import dao.MatchDAO;
import dao.ProFileDAO;
import model.Account;
import model.Login;
import model.ProFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet  extends HttpServlet {
    int index = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Login.account;
        String name = account.getUseName();
        ProFileDAO proFileDAO = new ProFileDAO();
        FriendsDAO friendsDAO = new FriendsDAO();
        List<ProFile> proFileFriends;
        List<ProFile> proFileDate;
        RequestDispatcher requestDispatcher;
        ProFile proFile = proFileDAO.findById(name);
        if (proFile==null){
           resp.sendRedirect("/proFile");
        }else {
           proFileFriends = friendsDAO.getAllFriend(name);
           proFileDate = proFileDAO.getAll();
            if (index==proFileDate.size()){
                index= 0;
            }
            req.setAttribute("proFileFriends",proFileFriends);
            req.setAttribute("proFile",proFile);
            req.setAttribute("proFileDate",proFileDate.get(index++));
            String action = req.getParameter("action");
            MatchDAO matchDAO = new MatchDAO();
            if (action==null){
                action="";
            }
            switch (action){
                case "match":
                    String IdProfile = req.getParameter("id");
                    matchDAO.create(name,IdProfile);
                    break;
                case "logout":
                    Login.account=null;
                    requestDispatcher = req.getRequestDispatcher("/view/login.jsp");
                    requestDispatcher.forward(req, resp);
                    break;

            }
            requestDispatcher = req.getRequestDispatcher("/view/home.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
}
