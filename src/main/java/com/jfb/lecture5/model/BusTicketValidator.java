package com.jfb.lecture5.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BusTicketValidator {
    private BusTicketValidator() {
    }

    public static boolean isValidByTypeAndStartDate(BusTicket busTicket) {
        String ticketType = busTicket.getTicketType();
        String startDate = busTicket.getStartDate();
        return (ticketType.equals("DAY") || ticketType.equals("WEEK") || ticketType.equals("YEAR"))
                && (startDate != null && !startDate.isEmpty());
    }

    public static boolean isPriceNotZero(BusTicket busTicket) {
        String ticketPrice = busTicket.getPrice();
        return ticketPrice !=null && !ticketPrice.equals("0");
    }

    public static boolean isValidByStartDate(BusTicket busTicket) {
        String startDate = busTicket.getStartDate();
        if (startDate == null || startDate.isEmpty()) {
            return false;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(startDate, formatter).isAfter(LocalDate.now());
        }
    }

    public static boolean isValidByType(BusTicket busTicket) {
        String ticketType = busTicket.getTicketType();
        return ticketType.equals("DAY") || ticketType.equals("WEEK") || ticketType.equals("MONTH") || ticketType.equals("YEAR");
    }

    public static boolean isPriceEven(BusTicket busTicket) {
        String price = busTicket.getPrice();
        if (price == null || price.isEmpty()) {
            return false;
        } else {
            return Integer.parseInt(price) % 2 == 0;
        }
    }
}
