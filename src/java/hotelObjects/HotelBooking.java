/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelObjects;

/**
 *
 * @author benjamin
 */
public class HotelBooking {
    private String   hotelName;
    private Address  address;
    private String   bookingNumber;
    private boolean  creditCardGuarentee;
    private int      priceForStay;
    private String   bookingService;
    
    public HotelBooking(String hotelName, Address address, int days, boolean creditCardGuarentee, int priceForStay, String bookingService, int hotelId){
        this.hotelName = hotelName;
        this.address = address;
        this.creditCardGuarentee = creditCardGuarentee;
        this.priceForStay = priceForStay;
        this.bookingService = bookingService;
        this.bookingNumber = createBookingNumber(days, hotelId);
    }

    public String getHotelName() {
        return hotelName;
    }

    public Address getAddress() {
        return address;
    }

    public String createBookingNumber(int days, int hotelId) {
        int cardGuarenteeInt = (creditCardGuarentee) ? 1 : 0;
        
        return priceForStay + "-" + cardGuarenteeInt + hotelId;
    }

    public boolean isCreditCardGuarentee() {
        return creditCardGuarentee;
    }

    public int getPriceForStay() {
        return priceForStay;
    }

    public String getBookingService() {
        return bookingService;
    }
    
}
