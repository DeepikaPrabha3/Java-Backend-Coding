package userInterface;

import dao.TaskDaoImpl;
import dao.UserDaoImpl;
import model.Task;
import service.TaskService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMenu {

    public static void main(String[] args) {
        TaskDaoImpl taskDao=new TaskDaoImpl();
        TaskService taskService =new TaskService(taskDao);
        UserDaoImpl userDao=new UserDaoImpl();
        UserService userService =new UserService(userDao);
        Scanner sc=new Scanner(System.in);
        int initialChoice;
        boolean startFlag=true;
        String usrName,email,pwd;
        System.out.println("Welcome to Todo Manager Application!");

        do {
            System.out.println("1. Login ");

            initialChoice=sc.nextInt();
            sc.nextLine();
            switch (initialChoice) {
                case 1:
                    System.out.println("Enter your username");
                    usrName = sc.next();
                    System.out.println("Enter your email");
                    email = sc.next();
                    System.out.println("Enter your password");
                    pwd = sc.next();
                    try {
                        if (userService.validateCredentials(usrName, email, pwd)) {
                            System.out.println("Login Successful...");
                            taskMenu(sc, taskService, email);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Exiting the app");
                    startFlag=false;
                    break;
                default:
                    System.out.println("Wrong option");
            }
        }while(startFlag);
    }

    public static void taskMenu(Scanner sc, TaskService taskService, String email){
        int taskId;
        String taskTitle,taskText,taskAssignee;
        boolean isTaskCompleted=false;

        boolean flag=true;
        do{
            System.out.println("Please choose an option");
            System.out.println("1. Create a task");
            System.out.println("2. Update a task");
            System.out.println("3. Delete a task");
            System.out.println("4. Search a task");
            System.out.println("5. Get all tasks assigned to me");
            System.out.println("6. Get tasks based on a status");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter the Task ID");
                    taskId=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the Task Title");
                    taskTitle=sc.nextLine();
                    System.out.println("Enter the Task Text");
                    taskText=sc.nextLine();
                    System.out.println("Enter the Task Assignee (Email-ID) ");
                    taskAssignee=sc.nextLine();
                    System.out.println("Is the Task Completed(true/false) ?");
                    isTaskCompleted=sc.nextBoolean();
                    Task t1=new Task(taskId,taskTitle,taskText,taskAssignee,isTaskCompleted);
                    try {
                        if(taskService.insertTask(t1)){
                            System.out.println("Task successfully created");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter the Task Title to be Updated");
                    taskTitle=sc.next();
                    System.out.println("Enter the Task Text");
                    taskText=sc.next();
                    System.out.println("Enter the Task Assignee (Email-ID) ");
                    taskAssignee=sc.next();
                    System.out.println("Is the Task Completed(true/false) ?");
                    isTaskCompleted=sc.nextBoolean();
                    Task t2=new Task();
                    t2.setTaskText(taskText);
                    t2.setTaskTitle(taskTitle);
                    t2.setAssignedTo(taskAssignee);
                    t2.setTaskCompleted(isTaskCompleted);
                    try {
                        if(taskService.updateTask(t2));
                        System.out.println("Task successfully updated");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Enter the Task Title to be Deleted");
                    taskTitle=sc.next();
                    try {
                        if(taskService.deleteTask(taskTitle));
                        System.out.println("Task Successfully Deleted ..");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    System.out.println("Enter the task title to be searched from listed below");
                    List<String> displayTaskList;
                    List<String> displayTaskOpted;
                    try {
                        displayTaskList=taskService.getAllTasks();
                        for (String task: displayTaskList) {
                            System.out.println(task);
                        }
                        String titleToBeSearched =sc.next();
                        displayTaskOpted=taskService.getTaskDetail(titleToBeSearched);

                            System.out.println("Task:"+displayTaskOpted);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 5:
                    System.out.println("Below are the tasks assigned to you ..");
                    List<String> taskList;
                    try {
                        taskList=taskService.getAllTasksAssigned(email);
                        for (String task: taskList) {
                            System.out.println(task);
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 6:
                    System.out.println("To get all the tasks based on status of completion --");
                    System.out.println("Enter status of completion (true/false) to be searched");
                    boolean status=sc.nextBoolean();
                    List<String> taskListBasedOnStatus;
                    try {
                        taskListBasedOnStatus=taskService.getAllTasksBasedOnStatus(status);
                        for (String task: taskListBasedOnStatus) {
                            System.out.println(task);
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 0:
                    System.out.println("Exiting the application ...");
                     flag = false;
                    break;
                default:
                    System.out.println("Wrong Option, Press 0 to exit");

                }
        //taskMenu(sc,taskService,email);
        }while(flag);


    }
}
