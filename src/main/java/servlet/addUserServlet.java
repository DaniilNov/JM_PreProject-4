package servlet;

import service.UserService;
import service.UserServiceImpl;
import user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addUser")

public class addUserServlet extends HttpServlet {

   private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/AddUserJSP.jsp");
        resp.setCharacterEncoding("UTF-8");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if(name.isEmpty()||password.isEmpty()){
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/AddUserJSP.jsp");
            dispatcher.forward(req,resp);
            return;
        }
        User user = new User(name,password,role);
        userService.addUser(user);
//        HttpSession session = req.getSession();
//        session.setAttribute("user",user);
        resp.sendRedirect("/login");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("html/ThankYou.html");
//        dispatcher.forward(req,resp);

    }
}
