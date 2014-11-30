/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelObjects;

/**
 *
 * @author benjamin
 */
public class Address {
    private String address;
    private String city;
    private String zipCode;
    private String country;
    
    public Address(String address, String city, String zipCode, String country){
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
    
    
}
