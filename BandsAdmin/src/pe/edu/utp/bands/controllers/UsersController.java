package pe.edu.utp.bands.controllers;

import com.sun.xml.internal.bind.v2.TODO;
import pe.edu.utp.bands.services.UserService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "UsersController")
public class UsersController extends HttpServlet {
//    Variable Connection
    private Connection connection;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("Post", request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("Get", request, response);
    }

    private Connection getConnection() {
        if (connection == null){
            try {
                InitialContext ctx = new InitialContext();
                DataSource dataSource = (DataSource) ctx
                        .lookup("jdbc/MySQLDataSource");
                connection = dataSource.getConnection();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private void processRequest(String method, HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        String url = "index.jsp";
        if(method.equals("Get") && action == null) { action = "index"; }
        if(method.equals("Post") && action.equalsIgnoreCase("index")) { return; }
        if(method.equals("Get") && action.equalsIgnoreCase("create")) { return; }
        if(method.equals("Get") && action.equalsIgnoreCase("update")) { return; }

        UserService service = new UserService();
        service.setConnection(getConnection());
//        TODO: Ocupo los jsp para arreglar el como voy a obtener y programar el request...
    }


}
