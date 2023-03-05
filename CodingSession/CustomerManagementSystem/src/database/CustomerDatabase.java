package database;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDatabase {
    List<Customer> customers =  new ArrayList<>();

    public CustomerDatabase() {
        customers.add(new Customer("a@a.c","aa","Pune","11111111111","aa"));
        customers.add(new Customer("b@a.c","bb","Kochi","555555555555","bb"));
        customers.add(new Customer("c@a.c","cc","Chennai","888888888888","cc"));
        customers.add(new Customer("d@a.c","dd","Pune","33333333333333","dd"));
    }
    public List<Customer> getAllCustomers(){
        return this.customers;
    }
    public boolean insertCustomer(Customer customer) throws Exception {
        if(customer.getEmail()==null || customer.getEmail()=="")
            throw new Exception("Customer email cannot be a null or blank value");
        customers.add(customer);
        return true;
    }
    public Customer getCustomerByEmail(String email){
        Customer customer=null;
        for (Customer cust:customers) {
            if(cust.getEmail().equals(email))
            {
                customer = cust;
                break;
            }
        }
        return customer;
    }
    public boolean login(String email, String pwd){
        for (Customer c:customers) {
            if(c.getEmail().equals(email)){
                if(c.getPassword().equals(pwd)) {
                    return true;
                }
            }
        }
        return false;
    }
}
