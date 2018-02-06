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
    //    General Method to executeQuery
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
    //    General Method to executeUpdate
    private int updateByCriteria(String sql){
        if (getConnection() != null){
            try {
                return getConnection()
                        .createStatement()
                        .executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    //    Find All Method
    public List<User> findAll(){
        return findByCriteria(DEFAULT_SQL);
    }
    //    Find by Id Method
    public User findById(int id){
        List<User> users = findByCriteria(
                DEFAULT_SQL +
                        "WHERE id = " +
                        String.valueOf(id)
        );
        return (users != null ? users.get(0) : null);
    }
    //    Find by Name Method
    public User findByName(String name){
        List<User> users = findByCriteria(
                DEFAULT_SQL +
                        " WHERE name = '" + name + "'"
        );
        return  (users.isEmpty()) ? null : users.get(0);
    }
    //    Find MaxId
    private int getMaxId(){
        String sql = "SELECT MAX(id) AS max_id FROM "+table;
        if (getConnection() != null){
            try {
                ResultSet resultSet = getConnection()
                        .createStatement()
                        .executeQuery(sql);
                return resultSet.next() ?
                        resultSet.getInt(1) : 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    //    Create user
    public User create(String username, String password, String email, String name, String lastname){
        if (findByName(username) == null){
            if (getConnection() != null){
                String sql = "INSERT INTO "+table+"(id, username, password, email, name, lastname, status, type) " +
                        "VALUES(" + String.valueOf(getMaxId() + 1) + ", '" +
                        username + "' , '"+password+"','"+email+"','"+name+"','"+lastname+"', '1', '1'";
                int results = updateByCriteria(sql);
                if (results > 0){
                    User user = new User();
                    user.setId(getMaxId());
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setName(name);
                    user.setLastname(lastname);
                    user.setStatus(1);
                    user.setStatus(1);
                    return user;
                }
            }
        }
        return null;
    }
    //    Delete by Id
    public boolean delete(int id){
        return updateByCriteria("DELETE FROM "+table+" WHERE id = " +
                String.valueOf(id)) > 0;
    }
    //    Delete by Name
    public boolean delete(String username){
        return updateByCriteria("DELETE FROM "+table+" WHERE username = '" +
                username + "'") > 0;
    }
    //    Update by User Object
    public boolean update(User user){
        return updateByCriteria("UPDATE "+table +
                " SET name = '" + user.getUsername() + "' " +
                " WHERE id= "+ String.valueOf(user.getId())) > 0;
    }

}
