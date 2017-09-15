import com.mysql.fabric.jdbc.FabricMySQLDriver;
import dataBase.DBControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static dataBase.DBControl.addInfo;
import static dataBase.DBControl.getConection;
import static dataBase.DBControl.scaningLogin;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final String URL_DATA_BASE = "jdbc:mysql://127.0.0.1:3306/test_site";
    private final String LOGIN_DATA_BASE = "root";
    private final String PASSWORD_DATA_BASE = "devel";
    private final String ERROR_LOADING_DBMYSQL = "невозможно загрузить драйвер MySQL";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        boolean flagScanLogin;
        String ERROR_LOGN_REGISTRATION = "Этот LOGIN уже занят ";
        String loginRegistration = request.getParameter("login_registration");
        String passwordRegistration = request.getParameter("password_registration");
        String nameRegistration = request.getParameter("name_registration");
        String surnameRegistration = request.getParameter("surname_registration");
        flagScanLogin = scaningLogin(loginRegistration);


        System.out.println(flagScanLogin);
        System.out.println(loginRegistration + " " + passwordRegistration + " " + nameRegistration + " " + surnameRegistration);
        if (flagScanLogin) {
            request.setAttribute("error_registration", ERROR_LOGN_REGISTRATION);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {

            addInfo(loginRegistration, passwordRegistration, nameRegistration, surnameRegistration);
            ArrayList<String> userInformation = DBControl.sampling(loginRegistration, passwordRegistration);


            request.setAttribute("registration_login", userInformation.get(0));
            request.setAttribute("registration_pasword", userInformation.get(1));
            request.setAttribute("registration_name", userInformation.get(2));
            request.setAttribute("registration_surname", userInformation.get(3));
            request.getRequestDispatcher("Finish.jsp").forward(request, response);
        }
    }


}
