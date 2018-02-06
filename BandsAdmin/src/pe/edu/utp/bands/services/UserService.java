package pe.edu.utp.bands.services;

import pe.edu.utp.bands.dto.User;
import pe.edu.utp.bands.models.UsersEntity;

import java.sql.Connection;
import java.util.List;

public class UserService {
    private Connection connection;
    private UsersEntity usersEntity;

    //    Patron FACADE
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    //    Patron SINGLETON
    public UsersEntity getUsersEntity() {
        if (getConnection() != null){
            if (usersEntity == null){
                usersEntity = new UsersEntity();
                usersEntity.setConnection(getConnection());
            }
        }
        return usersEntity;
    }

    public List<User> findAllRegions(){
        return getUsersEntity() != null ?
                getUsersEntity().findAll() : null;
    }

    public User findRegionById(int id){
        return getUsersEntity() != null ?
                getUsersEntity().findById(id) : null;
    }

    public User createRegion(String username, String password, String email, String name, String lastname){
        return getUsersEntity() != null ?
                getUsersEntity().create(username, password, email, name, lastname) : null;
    }

    public boolean deleteRegion(int id){
        return getUsersEntity() != null ?
                getUsersEntity().delete(id) : false;
    }

    public boolean updateRegion(User user){
        return getUsersEntity() != null ?
                getUsersEntity().update(user) : false;
    }
}
