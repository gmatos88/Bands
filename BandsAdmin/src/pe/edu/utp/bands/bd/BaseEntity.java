package pe.edu.utp.bands.bd;

import java.sql.Connection;
//Base Entity
public class BaseEntity {

    private Connection connection;


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
