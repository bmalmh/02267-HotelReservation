/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelObjects;

import java.util.ArrayList;

/**
 *
 * @author benjamin
 */
public class HotelDataCreator {
    
    public static ArrayList<HotelData> getHotelData() {

        ArrayList<HotelData> hotelData = new ArrayList<HotelData>();
        
        Address hotelCali = new Address("Eagles Street 445", "Sacramento", "94273", "United States of America");
        hotelData.add(new HotelData("Hotel California", hotelCali, true, 1500, 1));
        
        Address marriotAddr = new Address("ABC-Straße 52", "Hamburg", "20354", "Deutschland");
        hotelData.add(new HotelData("Marriot", marriotAddr, true, 350, 2));
        
        Address holidayAddr = new Address("275 Old St.", "London", "EC1V 9LN", "United Kingdom");
        hotelData.add(new HotelData("Holiday Inn", holidayAddr, false, 475, 3 ));
        
        Address fortuneAddr = new Address("765 Qixin Rd Minhang", "Shanghai", "201100", "China");
        hotelData.add(new HotelData("Fortune Forever Hotel", fortuneAddr, false, 750, 4));
        
        return hotelData;

    }
    
}
