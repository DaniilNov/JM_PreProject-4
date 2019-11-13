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
import java.io.IOException;

@WebServlet("/editUser")
public class editServlet extends HttpServlet {

    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = userService.getUserById(id);
        req.setAttribute("user",user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/EditUser.jsp");
        dispatcher.forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        userService.editUser(new User(id,name,password));
        resp.sendRedirect("/allUsers");
    }
}
