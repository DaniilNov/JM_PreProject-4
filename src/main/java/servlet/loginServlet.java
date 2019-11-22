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
import java.sql.SQLException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {

    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/Index.jsp");
     dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (name.isEmpty() || password.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/Index.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        try {
            User user = userService.getUserByName(name);
            if (user == null) {
         resp.sendRedirect("login");
                return;
            }
            if (user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

//                if ("admin".equals(user.getRole())) {
//                    resp.sendRedirect("/admin");
//                    return;
//                } else if ("user".equals(user.getRole())) {
//                    resp.sendRedirect("/admin");
//                    return;
//                }
                resp.sendRedirect("/admin");
            }
            else {
                resp.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
