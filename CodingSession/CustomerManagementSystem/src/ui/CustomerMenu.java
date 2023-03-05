package ui;

import database.CustomerDatabase;
import model.Customer;
import service.CustomerService;

import java.util.Scanner;

public class CustomerMenu {
    public static void main(String[] args) {
        CustomerDatabase customerDatabase =new CustomerDatabase();
        CustomerService customerService = new CustomerService(customerDatabase);
        String email, pwd, phone, city, name;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Operation");
        System.out.println("1. Login");
        System.out.println("2. Register Customer");
        System.out.println("3. Exit");
        int input=sc.nextInt();
        sc.nextLine();

            switch(input){
                case 1:
                    System.out.println("Please enter your email id..");
                    email =sc.nextLine();
                    System.out.println("Please enter the password..");
                    pwd = sc.nextLine();
                    if(email.equals("admin@a.c") && pwd.equals("admin"))
                    {do {
                        System.out.println("******** Welcome to Admin's Dashboard ********");
                        adminMenu();
                        int adminChoice = sc.nextInt();
                        switch (adminChoice) {
                            case 1:
                                try {
                                    for (Customer cust : customerService.getAllCustomers())
                                    {
                                        System.out.print(cust.getCustname()+",");
                                        System.out.print(cust.getCity()+",");
                                        System.out.print(cust.getPhone()+",");
                                        System.out.print(cust.getEmail()+",");
                                        System.out.println(" ");
                                    }

                                    break;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                email = sc.next();
                                try {
                                    Customer customer = customerService.getCustomerByEmail(email);
                                    System.out.println("Name : " + customer.getCustname());
                                    System.out.println("City : " + customer.getCity());
                                    System.out.println("Phone : " + customer.getPhone());
                                    System.out.println("Email : " + customer.getEmail());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                System.out.println("Logging Off....");
                                System.out.println("Thank you for using the customer management");
                                flag=false;
                                break;
                            default:
                                System.out.println("Wrong Option");
                        }
                    }while(flag);
                    }
                    else
                    {
                        try{
                            if(customerService.validateCredentials(email,pwd));
                            {
                                System.out.println("Welcome to Customer's Dashboard");
                                customerMenu();
                                int custChoice=sc.nextInt();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case 2:
                    System.out.println("Please enter details to register");
                    System.out.println("enter email");
                    email = sc.next();
                    System.out.println("Enter your name");
                    name= sc.next();
                    System.out.println("Enter your city");
                    city = sc.next();
                    System.out.println("enter your phone");
                    phone = sc.next();
                    System.out.println("Enter your password");
                    pwd = sc.next();
                    Customer c1 = new Customer(email, name, city, phone, pwd);
                    try {
                        customerService.registerCustomer(c1);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                       e.printStackTrace();
                    }
                    break;
                case 3: System.out.println("Thanks exiting the application");
                    flag = false;
                    break;
                default : System.out.println("Wrong option");
            }

    }

    public static void adminMenu(){
        System.out.println("Select an Option");
        System.out.println("1. Select all customers");
        System.out.println("2. Fetch  Customer By Email");
        System.out.println("3. Log Off");
    }

    public static void customerMenu(){
        System.out.println("Select an Option");
        System.out.println("1. Select all customers");
        System.out.println("2. Fetch  Customer By Email");
        System.out.println("3. Edit the profile ");
        System.out.println("4. Log Off");
    }
}
