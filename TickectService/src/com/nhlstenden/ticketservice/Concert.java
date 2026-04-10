package com.nhlstenden.ticketservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concert
{
    private static final int DEFAULT_TICKET_PRICE_IN_EUROS = 50;
    private static final int MINIMUM_PRICE = 1;

    private Artist artist;
    private Venue venue;
    private LocalDate date;
    private List<Ticket> soldTickets;
    private int priceInEuro;

    public Concert(Artist artist, Venue venue, LocalDate date)
    {
        this.setArtist(artist);
        this.setVenue(venue);
        this.setDate(date);
        this.setSoldTickets(new ArrayList<>());
        this.setPriceInEuro(this.DEFAULT_TICKET_PRICE_IN_EUROS);
    }

    public Artist getArtist()
    {
        return this.artist;
    }

    public void setArtist(Artist artist)
    {
        if (artist == null)
        {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        this.artist = artist;
    }

    public Venue getVenue()
    {
        return this.venue;
    }

    public void setVenue(Venue venue)
    {
        if (venue == null)
        {
            throw new IllegalArgumentException("Venue cannot be null.");
        }

        this.venue = venue;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public void setDate(LocalDate date)
    {
        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        /*if (date.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("Date cannot be set in the past.");
        }*/

        this.date = date;
    }

    public List<Ticket> getSoldTickets()
    {
        return this.soldTickets;
    }

    public void setSoldTickets(List<Ticket> soldTickets)
    {
        if (soldTickets == null)
        {
            throw new IllegalArgumentException("SoldTickets cannot be null.");
        }

        if (soldTickets.contains(null))
        {
            throw new IllegalArgumentException("SoldTickets cannot contain null elements.");
        }

        this.soldTickets = soldTickets;
    }

    public void addTicket(Ticket ticket)
    {
        if (ticket == null)
        {
            throw new IllegalArgumentException("Ticket cannot be null");
        }

        if (this.getSoldTickets().contains(ticket))
        {
            throw new IllegalArgumentException("This ticket already exists in the system.");
        }

        this.getSoldTickets().add(ticket);
    }

    public void removeTicket(Ticket ticket)
    {
        if (ticket == null)
        {
            throw new IllegalArgumentException("Ticket cannot be null");
        }

        if (!this.getSoldTickets().contains(ticket))
        {
            throw new IllegalArgumentException("This ticket does not exist in the system.");
        }

        this.getSoldTickets().remove(ticket);
    }

    public int getPriceInEuro()
    {
        return this.priceInEuro;
    }

    public void setPriceInEuro(int priceInEuro)
    {
        if (priceInEuro <= this.MINIMUM_PRICE)
        {
            throw new IllegalArgumentException("PriceInEuro cannot be inferior or equal to 0 euro.");
        }

        this.priceInEuro = priceInEuro;
    }

    public boolean hasOccured()
    {
        return this.getDate().isAfter(LocalDate.now());
    }

    public int getAmountOfTicketsSold()
    {
        return this.getSoldTickets().size();
    }

    public int getRevenueInEuros()
    {
        return this.getPriceInEuro() * this.getSoldTickets().size();
    }

    public boolean isSoldOut()
    {
         return this.getVenue().getMaxCapacity() >= this.getSoldTickets().size();
    }
}
