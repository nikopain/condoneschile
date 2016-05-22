package usm.cc.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by niko on 03/05/2016.
 */
public class User implements Parcelable {
    public String name;
    public String lastName;
    public String email;
    public String phone;
    public String address;
    public String city;
    public String postalCode;
    public User (){

    }
    public User(String name, String phone, String lastName, String email){
        this.name = name;
        this.phone = phone;
        this.lastName = lastName;
        this.email = email;
    }
    public User( String city, String address, String postalCode) {
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

    protected User(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        email = in.readString();
        phone = in.readString();
        address = in.readString();
        city = in.readString();
        postalCode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(postalCode);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
