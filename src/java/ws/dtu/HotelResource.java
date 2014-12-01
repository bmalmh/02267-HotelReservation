package ws.dtu;

import com.thoughtworks.xstream.XStream;
import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import hotelObjects.HotelBooking;
import hotelObjects.HotelData;
import hotelObjects.HotelDataCreator;
import java.util.ArrayList;
import javax.jws.WebService;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author benjamin
 */
@WebService(serviceName = "HotelResource")
public class HotelResource {
    BankService bank = new BankService();
    static ArrayList<HotelData> mockHotelData = HotelDataCreator.getHotelData();
    final int group = 1;
    
    public String getHotels(String city, String arrivalDate, String departureDate ) throws Exception{
        ArrayList<HotelBooking> result = new ArrayList<HotelBooking>();
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        DateTime start;
        DateTime end;
        try{
            start = format.parseDateTime(arrivalDate);
            end = format.parseDateTime(departureDate);
        }catch(Exception e){
            throw new Exception("Not a valid date", e);
        }
        
        int days = Days.daysBetween(start, end).getDays();
        
        for (HotelData hd : mockHotelData) {
            if (hd.getAddress().getCity().equals(city)) {
                result.add(new HotelBooking(hd.getHotelName(), hd.getAddress(), days, hd.isCreditCardGuarentee(), hd.calculatePrice(days), "NiceView", hd.getHotelID()));
            }
        }

        XStream xstream = new XStream();

        String xml = xstream.toXML(result);
                
        return xml;
    }
    
    public boolean bookHotel
            (String bookingNumber, 
             dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard) 
             throws Exception {
        String bookingRegex = "^[\\d]+[-]{1}[\\d]+$";
        boolean bookingMatch = bookingNumber.matches(bookingRegex);
        if(!bookingMatch){
            throw new Exception("Booking Number is not valid");
        }
        String[] bookingParts = bookingNumber.split("-");
        int days = Integer.parseInt(bookingParts[0]);
        boolean cardGuarentee = false;
        int hotelID = Integer.parseInt(bookingParts[1]);
        int pricePerDay = 0;
        boolean hotelExists = false;
        
        
        for (HotelData hd : mockHotelData) {
            if (hd.getHotelID() == hotelID) {
                hotelExists = true;
                cardGuarentee = hd.isCreditCardGuarentee();
                pricePerDay = hd.getPrice();
                break;
            }
        }
        if(!hotelExists)
            throw new Exception("Booking number is not valid, no hotel exists with that ID");
        
        int price = pricePerDay*days;

        if (cardGuarentee) {
         try{
             validateCreditCard(group, creditCard, price);
             return true;
         } catch(CreditCardFaultMessage e){
             throw new Exception("Credit card could not be validated for book hotel", e);
         }
        }
        return true;
    }

    public boolean cancelHotel(String bookingNumber)
                                throws Exception{
        ArrayList<HotelData> result = new ArrayList<HotelData>();
        String bookingRegex = "^[\\d]+[-]{1}[\\d]+$";
        boolean bookingMatch = bookingNumber.matches(bookingRegex);
        
        if(!bookingMatch){
            throw new Exception("Booking Number is not valid");
        }
        
        String[] bookingParts = bookingNumber.split("-");
        boolean hotelExists = false;
        int hotelID = Integer.parseInt(bookingParts[1]);
        
        
        for (HotelData hd : mockHotelData) {
            if (hd.getHotelID() == hotelID) {
                hotelExists = true;
                break;
            }
        }
        if(!hotelExists)
            throw new Exception("Booking number is not valid, no hotel exists with that ID");
        
        return true;
    }

    private boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankPortType port = bank.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}