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

@WebServlet(urlPatterns = "/checkMatch")
public class CheckMatchServlet extends HttpServlet {
    int index = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Login.account;
        String name = account.getUseName();
        ProFileDAO proFileDAO = new ProFileDAO();
        MatchDAO friendsDAO = new MatchDAO();
        FriendsDAO friendsDAO1 = new FriendsDAO();
        String action = req.getParameter("action");
        MatchDAO matchDAO = new MatchDAO();
        if (action == null) {
            action = "";
        }
        String IdProfile;
        switch (action) {
            case "friend":
                IdProfile = req.getParameter("id");
                matchDAO.delete(IdProfile, name);
                friendsDAO1.create(name, IdProfile);
                index--;
                break;
            case "unmatch":
                IdProfile = req.getParameter("id");
                matchDAO.delete(IdProfile, name);
                index--;
                break;
            case "match":
                IdProfile = req.getParameter("id");
                matchDAO.create(name,IdProfile);
                break;

        }

        List<ProFile> proFileFriends;
        List<ProFile> proFileDate;
        RequestDispatcher requestDispatcher;
        ProFile proFile = proFileDAO.findById(name);
        proFileFriends = friendsDAO.getAllMatch(name);
        proFileDate = proFileDAO.getAll();
        if (index == proFileDate.size()) {
            index = 0;
        }
        req.setAttribute("proFileFriends", proFileFriends);
        req.setAttribute("proFile", proFile);
        req.setAttribute("proFileDate", proFileDate.get(index++));

        requestDispatcher = req.getRequestDispatcher("/view/checkMatch.jsp");
        requestDispatcher.forward(req, resp);
    }
}
