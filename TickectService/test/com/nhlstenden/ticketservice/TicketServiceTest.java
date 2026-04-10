package com.nhlstenden.ticketservice;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest
{

    @Test
    void getTotalRevenueInEuro()
    {
        // Arrange
        TourManager tourManager = new TourManager("Something 1", 5);
        TicketService ticketService = new TicketService(tourManager);
        Artist artist1 = new Artist("John", "R&B");
        Venue venue1 = new Venue("Something", "Something", 10);
        Concert concert1 = new Concert(artist1, venue1, LocalDate.parse("2030-01-01"));

        tourManager.addConcert(concert1);

        // Couldn't finish the exam...



        // Action

        // Assert
    }
}