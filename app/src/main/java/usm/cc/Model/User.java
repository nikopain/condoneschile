package usm.cc.Model;

/**
 * Created by niko on 03/05/2016.
 */
public class User {
    public String name;
    public String lastName;
    public String email;
    public String phone;
    public String address;
    public String city;
    public String postalCode;

    public User(String name, String phone, String lastName, String email, String city, String address, String postalCode) {
        this.name = name;
        this.phone = phone;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
