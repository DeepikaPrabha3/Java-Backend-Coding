package service;

import dao.UserDaoImpl;

public class UserService {
    private UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao=userDao;
    }

    public boolean  validateCredentials(String username, String email, String pwd) throws Exception {
        if(email == null || email.isBlank()){
            throw new Exception("Customer email cannot be a null or blank value");
        }
        return this.userDao.login(username,email,pwd);
    }

}
