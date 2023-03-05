package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
    Connection conn = DBConnection.getConnection();

    //Function Validates the login by checking the crdentials are true from DB
    public boolean login(String usrname, String email, String password) throws Exception
    {
        String sql = "select password from user where username=? and email=?";

        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, usrname);
            statement.setString(2, email);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                if(password.equals(rs.getString(1)))
                    return true;
            }
            throw new Exception("Invalid credentials");
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }
    //
}
