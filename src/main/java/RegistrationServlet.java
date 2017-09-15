import dataBase.DBControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static dataBase.DBControl.addInfo;
import static dataBase.DBControl.scaningLogin;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8"); //устанавливает кокодировку сервлета
        request.setCharacterEncoding("UTF-8");               //устанавливает кокодировку сервлета

        String ERROR_LOGN_REGISTRATION = "Этот LOGIN уже занят "; // сообщение
        String loginRegistration = request.getParameter("login_registration"); //получение данных введенных в поле login_registration страница registration.jsp
        String passwordRegistration = request.getParameter("password_registration");//получение данных введенных в поле password_registration страница registration.j
        String nameRegistration = request.getParameter("name_registration");//получение данных введенных в поле name_registration страница registration.j
        String surnameRegistration = request.getParameter("surname_registration");//получение данных введенных в поле surname_registration страница registration.j
         boolean flagScanLogin = scaningLogin(loginRegistration);

//         блок if сработает если логин существует в базе данных и выведет на страницу registration.jsp сообщение об ошибке
        if (flagScanLogin) {
            request.setAttribute("error_registration", ERROR_LOGN_REGISTRATION);// устанавливает сообщение об ошибке на страницу registration.jsp
            request.getRequestDispatcher("registration.jsp").forward(request, response); // направляет на страницу registration.jsp
//            блок else сработает если логин не существует в базе данных. внесет сведения в базу данныхю
        } else {

            addInfo(loginRegistration, passwordRegistration, nameRegistration, surnameRegistration); //метод вносит данные в базу данных

//            получение информации из базы данных о пользователе
            ArrayList<String> userInformation = DBControl.sampling(loginRegistration, passwordRegistration);

//            установка
            request.setAttribute("registration_login", userInformation.get(0));     //установка параметра registration_login
            request.setAttribute("registration_pasword", userInformation.get(1));   //установка параметра registration_pasword
            request.setAttribute("registration_name", userInformation.get(2));      //установка параметра registration_name
            request.setAttribute("registration_surname", userInformation.get(3));   //установка параметра registration_surname
            request.getRequestDispatcher("Finish.jsp").forward(request, response);  //направляет на страницу Finish.jsp
        }
    }


}
