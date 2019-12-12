package servlet;

import service.UserService;
import service.UserServiceImpl;
import user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class getAllUserServlet extends HttpServlet {


   private UserService userService = UserServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            List<User>users = userService.getAllUsers();
//            req.setCharacterEncoding("UTF-8");
            req.setAttribute("usersFromServlet",users);

            req.getServletContext().getRequestDispatcher("/jsp/admin/AllUsers.jsp").forward(req,resp);


        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
