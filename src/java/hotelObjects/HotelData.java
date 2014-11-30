/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelObjects;

/**
 *
 * @author benjamin
 */
public class HotelData {
    private int         hotelID;
    private String      hotelName;
    private Address     address;
    private boolean     creditCardGuarentee;
    private int         price;
    
    public HotelData(String hotelName, Address address, boolean creditCardGuarentee, int price, int hotelID){
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.address = address;
        this.creditCardGuarentee = creditCardGuarentee;
        this.price = price;
    }
    
    public int getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isCreditCardGuarentee() {
        return creditCardGuarentee;
    }

    public int getPrice() {
        return price;
    }
    
    public int calculatePrice(int days){
        return days*price;
    }
}
