package com.jfb.lecture5.model;

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
}
