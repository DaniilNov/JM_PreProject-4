package filter;

import user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter (urlPatterns = "/admin/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user =(User) session.getAttribute("user");

        if (user!=null&&user.getRole().equals("admin")){
            request.getRequestDispatcher("/allUsers").forward(request,response);

        }else if (user!=null&&user.getRole().equals("user")){

            ((HttpServletResponse) response).sendRedirect("/html/ThankYou.html");
//            request.getRequestDispatcher("/html/ThankYou.html");
        }
        else {
            request.getRequestDispatcher("/logout");
        }
    }

    @Override
    public void destroy() {

    }
}
