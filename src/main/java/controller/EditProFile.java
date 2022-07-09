package controller;

import dao.FriendsDAO;
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

@WebServlet(urlPatterns = "/proFile")
public class EditProFile extends HttpServlet {
    Account account = Login.account;
    String name = account.getUseName();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProFileDAO proFileDAO = new ProFileDAO();
        ProFile proFile = proFileDAO.findById(name);
        RequestDispatcher requestDispatcher;
        if (proFile!=null){
            req.setAttribute("Profile",proFile);
        }
        requestDispatcher = req.getRequestDispatcher("/view/editProfile.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ProFileDAO proFileDAO = new ProFileDAO();
       String name2 = req.getParameter("inputName");
       String gender = req.getParameter("inputGender");
        int age =0;
       try {
            age = Integer.parseInt(req.getParameter("inputAge"));
       }catch (Exception e){
           resp.sendRedirect("/proFile");
       }
       String address = req.getParameter("inputAddress");
       String imgSrc = req.getParameter("LinkImage");
    if ( proFileDAO.create(new ProFile(name,name2,gender,age,address,imgSrc))){
           resp.sendRedirect("/home");
       }else {
           resp.sendRedirect("/proFile");
       }

    }
}
