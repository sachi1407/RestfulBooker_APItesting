package Restfulbooker.modules;

import Restfulbooker.payload.Booking;
import Restfulbooker.payload.Bookingdates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadModule {

    ObjectMapper objectMapper;


    public String createBooking() throws JsonProcessingException {
        objectMapper=new ObjectMapper();
        Booking booking=new Booking();
        booking.setFirstname("Sachin");
        booking.setLastname("chauhan");
        booking.setTotalprice(2390);
        booking.setAdditionalneeds("breakfast");
        booking.setDepositpaid(true);
        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2023-07-01");
        bookingdates.setCheckout("2023-07-04");
        booking.setBookingdates(bookingdates);

        String payload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;

    }

}
