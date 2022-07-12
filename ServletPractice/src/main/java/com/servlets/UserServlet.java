package com.servlets;

import com.Persistance.UserDAO;
import com.Utils.CurrentUser;
import com.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet
{
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 //       super.doGet(req, resp);

//        Integer userID = Integer.parseInt(req.getParameter("user-id-input"));
//        User myUser = userDAO.read(userID);
//        resp.getWriter().println(myUser.getUser_id());
//        resp.getWriter().println(myUser.getFirst_name());
//        resp.getWriter().println(myUser.getLast_name());
//        resp.getWriter().println(myUser.getEmail());

    String email = String.valueOf(req.getParameter("email-sign-in"));
    String password = String.valueOf(req.getParameter("password-sign-in"));

        CurrentUser.currentUser = userDAO.loginUser(email,password);


        //this can be  where we figure between employee or manager
//        if (CurrentUser.employeeType == "employee")
//        {
//            //do some logic
//        }
//        else
//        {
//            //do some other logic
//        }

//        if(CurrentUser.currentUser == null)
//        {
//            req.getRequestDispatcher("error-page.html");
//        }
//        else
//        {
//          req.getRequestedDispatcher("tickets.html")
//        }


    }
}
