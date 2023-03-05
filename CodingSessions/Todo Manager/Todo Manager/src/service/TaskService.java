package service;

import dao.TaskDaoImpl;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
 private TaskDaoImpl taskDao;

 public TaskService(TaskDaoImpl taskDao) {
  this.taskDao = taskDao;
 }

 public List<String> getAllTasksAssigned(String email) throws Exception {
    if(email==null || email.isBlank())
     throw new Exception("User email cannot be a null or blank value");
    List<String> task = this.taskDao.getAllTasksAssigned(email);
    if(task==null)
     throw new Exception("User with email "+email+" does not exist");
    return task;
   }
    public List<String> getAllTasks() throws Exception {

        List<String> task = this.taskDao.getAllTasks();
        if(task==null)
            throw new Exception("No Tasks available");
        return task;
    }
    public boolean insertTask(Task task) throws Exception {
        if(task.getTaskText()==null || task.getTaskText().isBlank())
            throw new Exception("Task Text cannot be a null or blank value");
        if(task.getTaskTitle()==null || task.getTaskTitle().isBlank())
            throw new Exception("Task Title cannot be a null or blank value");
        if(task.getAssignedTo()==null || task.getAssignedTo().isBlank())
            throw new Exception("Task Assignee cannot be a null or blank value");

        try {
            taskDao.insertTask(task);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateTask(Task task) throws Exception {
        if(task.getTaskText()==null || task.getTaskText().isBlank())
            throw new Exception("Task Text cannot be a null or blank value");
        if(task.getTaskTitle()==null || task.getTaskTitle().isBlank())
            throw new Exception("Task Title cannot be a null or blank value");
        if(task.getAssignedTo()==null || task.getAssignedTo().isBlank())
            throw new Exception("Task Assignee cannot be a null or blank value");

        try {
            taskDao.updateTask(task);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteTask(String taskTitle) throws Exception {

        if(taskTitle==null || taskTitle.isBlank())
            throw new Exception("Task Title cannot be a null or blank value");
        try {
            taskDao.deleteTask(taskTitle);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<String> getTaskDetail(String taskTitle) throws Exception {
        List<String> taskList=new ArrayList<>();
        if(taskTitle==null || taskTitle.isBlank())
            throw new Exception("Task Title cannot be a null or blank value");
        try {
            taskList=taskDao.searchTask(taskTitle);
            if(taskList==null)
                throw new Exception("No Tasks available for "+taskTitle+" Title");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return taskList;
    }
    public List<String> getAllTasksBasedOnStatus(boolean status) throws Exception {
        List<String> taskList=new ArrayList<>();

        try {
            taskList=taskDao.getAllTasksBasedOnStatus(status);
            if(taskList==null)
                throw new Exception("No Tasks available for "+status+" Status");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return taskList;
    }
}
