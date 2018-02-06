package pe.edu.utp.bands.models;

import pe.edu.utp.bands.bd.BaseEntity;
import pe.edu.utp.bands.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersEntity extends BaseEntity{
    private static String bd    = "bands";
    private static String table = "users";
    private static String DEFAULT_SQL = "SELECT * FROM " + bd + "." + table;

    private List<User> findByCriteria(String sql) {
        List<User> users;
        if(getConnection() != null) {
            users = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                while(resultSet.next()) {
                    User user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));

                    user.setName(resultSet.getString("name"));
                    user.setLastname(resultSet.getString("lastname"));

                    user.setStatus(resultSet.getInt("status"));
                    user.setType(resultSet.getInt("type"));

                    users.add(user);
                }
                return users;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
