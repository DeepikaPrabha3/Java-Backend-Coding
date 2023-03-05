package dao;

import model.Task;

import java.beans.Customizer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl {
    Connection conn = DBConnection.getConnection();
    public boolean insertTask(Task task) throws Exception {
        String sql = "insert into task values(?,?,?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getTaskId());
            statement.setString(2, task.getTaskTitle());
            statement.setString(3, task.getTaskText());
            statement.setString(4, task.getAssignedTo());
            statement.setBoolean(5, task.isTaskCompleted());

            statement.execute();
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return true;
    }
    public boolean updateTask(Task task) throws Exception {
        String sql = "update task set text=?,assignee=?, completion_status=? where title=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, task.getTaskText());
            statement.setString(2, task.getAssignedTo());
            statement.setBoolean(3, task.isTaskCompleted());
            statement.setString(4, task.getTaskTitle());

            statement.execute();
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return true;
    }
    public boolean deleteTask(String title) throws Exception {
        String sql = "delete from task where title=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.execute();
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return true;
    }
    public List<String> getAllTasksAssigned(String email) throws Exception {
        String sql = "select title from task where assignee=?";
        List<String> tasks=new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);

            ResultSet rs =statement.executeQuery();
            while(rs.next()) {
             tasks.add(rs.getString(1));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return tasks;
    }
    public List<String> searchTask(String taskTitle) throws Exception {
        String sql = "select * from task where title=?";
        List tasks=new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, taskTitle);

            ResultSet rs =statement.executeQuery();
            while(rs.next()) {
                tasks.add(rs.getInt(1));
                tasks.add(rs.getString(2));
                tasks.add(rs.getString(3));
                tasks.add(rs.getString(4));
                tasks.add(rs.getBoolean(5));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return tasks;
    }
    public List<String> getAllTasks() throws Exception {
        String sql = "select distinct title from task";
        List<String> titleList=new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs =statement.executeQuery();
            while(rs.next()) {
                titleList.add(rs.getString(1));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return titleList;
    }

    public List<String> getAllTasksBasedOnStatus(boolean status) throws Exception {
        String sql = "select distinct title from task where completion_status=?";
        List<String> titleList=new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, status);
            ResultSet rs =statement.executeQuery();
            while(rs.next()) {
                titleList.add(rs.getString(1));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return titleList;
    }
}
