package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;
import com.jfb.lecture5.model.BusTicketValidator;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws JsonProcessingException {
    int x = 0;
    int totalTickets = 0;
    int validTickets = 0;
    int startDateViolations = 0;
    int priceViolations = 0;

    do {
      String input = getInput();
      try {
        BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);
        if (!BusTicketValidator.isValidByTypeAndStartDate(busTicket)) {
          startDateViolations++;
        } else if (!BusTicketValidator.isPriceNotZero(busTicket)) {
          priceViolations++;
        } else {
          validTickets++;
        }
        totalTickets++;
        System.out.println(busTicket.toString());
        x++;
      } catch (JsonParseException e) {
        System.out.println("Invalid input, ticket can't be created");
      }
    } while (x < 5);
    
    System.out.println("Total = " + totalTickets);
    System.out.println("Valid = " + validTickets);
    if (startDateViolations > priceViolations) {
      System.out.println("Most popular violation = startDate");
    } else {
      System.out.println("Most popular violation = price");
    }
  }

  private static String getInput() {
    return new Scanner(System.in).nextLine();
  }
}
