package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;
import com.jfb.lecture5.model.BusTicketValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    } else if (startDateViolations < priceViolations) {
      System.out.println("Most popular violation = price");
    } else {
      System.out.println("Most popular violation = none");
    }

    //Reading tickets from file and validating
    validTickets = 0;
    startDateViolations = 0;
    priceViolations = 0;
    int typeViolations = 0;
    List<BusTicket> busTickets = readTicketsFromFile("src/main/resources/ticketData.txt");
    totalTickets = busTickets.size();
    for (BusTicket busTicket : busTickets) {
      if (!BusTicketValidator.isValidByStartDate(busTicket)) {
        startDateViolations++;
      } else if (!BusTicketValidator.isValidByType(busTicket)) {
        typeViolations++;
      } else if (!BusTicketValidator.isPriceEven(busTicket)) {
        priceViolations++;
      } else {
        validTickets++;
      }
    }
    System.out.println("Total = " + totalTickets);
    System.out.println("Valid = " + validTickets);
    int maxNumberOfViolations = Math.max(startDateViolations, Math.max(priceViolations, typeViolations));
    if (startDateViolations == maxNumberOfViolations) {
      System.out.println("Most popular violation = startDate");
    } else if (priceViolations == maxNumberOfViolations){
      System.out.println("Most popular violation = price");
    } else {
      System.out.println("Most popular violation = type");
    }
  }

  private static String getInput() {
    return new Scanner(System.in).nextLine();
  }

  private static List<BusTicket> readTicketsFromFile(String file) {
    List<BusTicket> tickets = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String input = reader.readLine();
      while (input != null) {
        try {
          tickets.add(new ObjectMapper().readValue(input, BusTicket.class));
          input = reader.readLine();
        } catch (JsonParseException e) {
          System.out.println("Invalid input, ticket can't be created");
          input = reader.readLine();
        }
      }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return tickets;
  }
}
