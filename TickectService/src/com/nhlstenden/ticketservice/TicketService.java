package com.nhlstenden.ticketservice;

import java.time.LocalDate;

public class TicketService
{
    private TourManager tourManager;

    public TicketService(TourManager tourManager)
    {
        this.setTourManager(tourManager);
    }

    public TourManager getTourManager()
    {
        return this.tourManager;
    }

    public void setTourManager(TourManager tourManager)
    {
        if (tourManager == null)
        {
            throw new IllegalArgumentException("TourManager cannot be null.");
        }

        this.tourManager = tourManager;
    }

    public void sellTickets(String artistName, String venueName, LocalDate date, int amountOfTickets)
    {
        Concert searchedConcert = this.getTourManager().getConcert(artistName, venueName, date);
        LocalDate today = LocalDate.now();

        if (searchedConcert == null)
        {
            throw new IllegalArgumentException("This concert does not exist.");
        }

        if (searchedConcert.getDate().isBefore(today))
        {
            throw new IllegalArgumentException("This concert has already happened.");
        }

        if (searchedConcert.isSoldOut())
        {
            throw new IllegalArgumentException("This concert has already sold out.");
        }

        if (isSoldOut(amountOfTickets, searchedConcert))
        {
            throw new IllegalArgumentException("This concert is too full for the amount of tickets requested.");
        }

        for (int i = 1; i < amountOfTickets; i++)
        {
            searchedConcert.addTicket(new Ticket(searchedConcert));
        }
    }

    public int getTotalRevenueInEuro(String artistName)
    {
        if (artistName == null || artistName.isBlank())
        {
            throw new IllegalArgumentException("ArtistName cannot be null or blank.");
        }

        return this.getTourManager().getTotalRevenueInEuros(artistName);
    }

    public boolean isSoldOut(int amountOfTickets, Concert concert)
    {
        if (amountOfTickets <= 0)
        {
            throw new IllegalArgumentException("AmountOfTickets cannot be inferior or equal to 0.");
        }

        if (concert == null)
        {
            throw new IllegalArgumentException("Concert cannot be null.");
        }

        int amountOfTicketsAfter = concert.getAmountOfTicketsSold() + amountOfTickets;

        if (amountOfTicketsAfter > concert.getVenue().getMaxCapacity())
        {
            return true;
        }

        return false;
    }
}