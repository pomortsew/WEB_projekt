import dataBase.DBControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/mySimple")
public class SimpleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String login = request.getParameter("loginMypage");
        String password=request.getParameter("passwordMypage");

        if (request.getParameter("Enter") != null) {
            if (DBControl.userVerification(login,password)){



                ArrayList<String> userInformation = DBControl.sampling(login, password);


                request.setAttribute("registration_login", userInformation.get(0));
                request.setAttribute("registration_pasword", userInformation.get(1));
                request.setAttribute("registration_name", userInformation.get(2));
                request.setAttribute("registration_surname", userInformation.get(3));
                request.getRequestDispatcher("Finish.jsp").forward(request, response);



            }else{
                request.setAttribute("error_login_verification","Ошибка входа не правельно введен логин или пароль  ");
                request.setAttribute("loginMypage",login);
            request.setAttribute("passwordMypage",password);
            request.getRequestDispatcher("mypage.jsp").forward(request,response);

            }


        } else if (request.getParameter("Registre") != null) {
            request.setAttribute("password",password);
            request.setAttribute("login", login);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}

