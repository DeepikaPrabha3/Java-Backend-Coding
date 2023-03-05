package model;
//create a bean or pojo or model class
//FYI -Limbok or loggers
public class Customer {
    private String email;
    private String custname;
    private String city;
    private String phone;
    private String password;
    public Customer() {
    }

    public Customer(String email, String custname, String city, String phone, String password) {
        this.email = email;
        this.custname = custname;
        this.city = city;
        this.phone = phone;
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
