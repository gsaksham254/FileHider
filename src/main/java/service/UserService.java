package service;

import dao.UserDao;
import model.User;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(User user){
        try{
            if(UserDao.isExists(user.getEmail())){
                return 0;
            }
            else{
                return UserDao.saveUser(user);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
