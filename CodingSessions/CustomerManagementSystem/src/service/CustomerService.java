package service;

import database.CustomerDBConnection;
import database.CustomerDatabase;
import model.Customer;

import java.util.List;

public class CustomerService {
    //private CustomerDatabase customerDatabase ;
    private CustomerDBConnection customerDatabase ;

    public CustomerService(CustomerDBConnection customerDatabase) {
        this.customerDatabase = customerDatabase;
    }
    public List<Customer> getAllCustomers() throws Exception {
        if(customerDatabase.getAllCustomers().size() == 0)
            throw new Exception("No Customer registered yet");
        return this.customerDatabase.getAllCustomers();
    }
    public boolean registerCustomer(Customer customer) throws Exception {
        if(customer.getEmail()==null || customer.getEmail().isBlank())
            throw new Exception("Customer email cannot be a null or blank value");
        try {
            customerDatabase.insertCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Customer getCustomerByEmail(String email) throws Exception {
        if(email==null || email.isBlank())
            throw new Exception("Customer email cannot be a null or blank value");
        Customer cust = this.customerDatabase.getCustomerByEmail(email);
        if(cust==null)
            throw new Exception("Customer with emmail "+email+" does not exist");
        return cust;
    }
    public boolean  validateCredentials(String email, String pwd) throws Exception {
        if(email == null || email.isBlank()){
            throw new Exception("Customer email cannot be a null or blank value");
        }
        return this.customerDatabase.login(email,pwd);
    }
}
