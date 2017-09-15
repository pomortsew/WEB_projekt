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

        String  PASSWORD_OR_LOGIN_FAILED="Ошибка входа не правельно введен логин или пароль  ";
        String login = request.getParameter("loginMypage");
        String password = request.getParameter("passwordMypage");
//            блок if сработает при нажатии кнопки enter (login)
        if (request.getParameter("Enter") != null) {
//            блок if сработает если введены правельные логин и пароль, устанавливает данные полученные из БД,
//            и напрвляет на страницу Finish.jsp
            if (DBControl.userVerification(login, password)) {
                ArrayList<String> userInformation = DBControl.sampling(login, password);        // получение данных из БД
                request.setAttribute("registration_login", userInformation.get(0));         // устанавливает параметр registration_login
                request.setAttribute("registration_pasword", userInformation.get(1));       // устанавливает параметр registration_pasword
                request.setAttribute("registration_name", userInformation.get(2));          // устанавливает параметр registration_name
                request.setAttribute("registration_surname", userInformation.get(3));       // устанавливает параметр registration_surname
                request.getRequestDispatcher("Finish.jsp").forward(request, response);      // напрвляет на страницу Finish.jsp
//            блок else сработает если логин или пароль введены не правельно и выведет сообщение об ошибке на страницу mypage.jsp
            } else {
                request.setAttribute("error_login_verification", PASSWORD_OR_LOGIN_FAILED); // установка параметра error_login_verification сообщения об ошибке
                request.setAttribute("loginMypage", login);                                 // установка параметра loginMypage
                request.setAttribute("passwordMypage", password);                           // установка параметра passwordMypage
                request.getRequestDispatcher("mypage.jsp").forward(request, response);      // напрвляет на страницу mypage.jsp

            }

//            блок else if сработает при нажатии кнопки registration устанавливает параметры и направляет на страницу registration.
        } else if (request.getParameter("Registre") != null) {
            request.setAttribute("password", password);                                     //установка параметра password
            request.setAttribute("login", login);                                           //установка параметра password
            request.getRequestDispatcher("registration.jsp").forward(request, response);    //направление на страницу registration,jsp
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}

