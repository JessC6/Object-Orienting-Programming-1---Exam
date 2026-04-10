package com.nhlstenden.ticketservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TourManager
{
    private String name;
    private int maxAmountOfArtists;
    private List<Artist> artists;
    private List<Venue> venues;
    private List<Concert> concerts;

    public TourManager(String name, int maxAmountOfArtists)
    {
        this.setName(name);
        this.setMaxAmountOfArtists(maxAmountOfArtists);
        this.setArtists(new ArrayList<>());
        this.setVenues(new ArrayList<>());
        this.setConcerts(new ArrayList<>());
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        this.name = name;
    }

    public int getMaxAmountOfArtists()
    {
        return this.maxAmountOfArtists;
    }

    public void setMaxAmountOfArtists(int maxAmountOfArtists)
    {
        if (maxAmountOfArtists < 0)
        {
            throw new IllegalArgumentException("MaxAmountOfArtists cannot be negative.");
        }

        this.maxAmountOfArtists = maxAmountOfArtists;
    }

    public List<Artist> getArtists()
    {
        return this.artists;
    }

    public void setArtists(List<Artist> artists)
    {
        if (artists == null)
        {
            throw new IllegalArgumentException("Artists cannot be null.");
        }

        if (artists.contains(null))
        {
            throw new IllegalArgumentException("Artists list cannot contain any null elements.");
        }

        this.artists = artists;
    }

    public void addArtist(Artist artist)
    {
        if (artist == null)
        {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        if (this.getArtists().contains(artist))
        {
            throw new IllegalArgumentException("This artist already exists in the system.");
        }

        if (this.getArtists().size() >= this.getMaxAmountOfArtists())
        {
            throw new IllegalArgumentException("The maximum amount of artists has been reached.");
        }

        this.getArtists().add(artist);
    }

    public void removeArtist(Artist artist)
    {
        if (artist == null)
        {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        if (!this.getArtists().contains(artist))
        {
            throw new IllegalArgumentException("This artist does not exist in the system.");
        }

        this.getArtists().remove(artist);
    }

    public List<Venue> getVenues()
    {
        return this.venues;
    }

    public void setVenues(List<Venue> venues)
    {
        if (venues == null)
        {
            throw new IllegalArgumentException("Venues cannot be null.");
        }

        if (venues.contains(null))
        {
            throw new IllegalArgumentException("Venues list cannot contain null elements.");
        }

        this.venues = venues;
    }

    public void addVenue(Venue venue)
    {
        if (venue == null)
        {
            throw new IllegalArgumentException("Venue cannot be null.");
        }

        if (this.getVenues().contains(venue))
        {
            throw new IllegalArgumentException("This venue already exists in the system.");
        }

        this.getVenues().add(venue);
    }

    public void removeVenue(Venue venue)
    {
        if (venue == null)
        {
            throw new IllegalArgumentException("Venue cannot be null.");
        }

        if (!this.getVenues().contains(venue))
        {
            throw new IllegalArgumentException("This venue does not exist in the system.");
        }

        this.getVenues().remove(venue);
    }

    public List<Concert> getConcerts()
    {
        return this.concerts;
    }

    public void setConcerts(List<Concert> concerts)
    {
        if (concerts == null)
        {
            throw new IllegalArgumentException("Concerts cannot be null.");
        }

        if (concerts.contains(null))
        {
            throw new IllegalArgumentException("Concerts list cannot contain null elements.");
        }

        this.concerts = concerts;
    }

    public void addConcert(Concert concert)
    {
        if (concert == null)
        {
            throw new IllegalArgumentException("Concert cannot be null.");
        }

        if (this.getConcerts().contains(concert))
        {
            throw new IllegalArgumentException("This concert already exists in the system.");
        }

        this.getConcerts().add(concert);
    }

    public void removeConcert(Concert concert)
    {
        if (concert == null)
        {
            throw new IllegalArgumentException("Concert cannot be null.");
        }

        if (!this.getConcerts().contains(concert))
        {
            throw new IllegalArgumentException("This concert does not exist in the system.");
        }

        this.getConcerts().remove(concert);
    }

    public Concert getConcert(Artist artist, Venue venue, LocalDate date)
    {
        if (artist == null || venue == null || date == null)
        {
            throw new IllegalArgumentException("Artist, venue and date cannot be null.");
        }

        List<Concert> concertsByArtists = this.getConcertByArtist(this.getConcerts(), artist);
        List<Concert> concertsByDate = this.getConcertByDate(this.getConcerts(), date);
        List<Concert> concertsByVenue = this.getConcertByVenue(this.getConcerts(), venue);

        if (concertsByArtists.isEmpty() || concertsByDate.isEmpty() || concertsByVenue.isEmpty())
        {
            return null;
        }

        for (Concert concert : concertsByArtists)
        {
            if (concertsByDate.contains(concert) && concertsByVenue.contains(concert))
            {
                return concert;
            }
        }

        return null;
    }

    public Concert getConcert(String artistName, String venueName, LocalDate date)
    {
        if (artistName == null || artistName.isBlank() || venueName == null || venueName.isBlank())
        {
            throw new IllegalArgumentException("ArtistName and venueName cannot be null or blank.");
        }

        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        for (Concert concert : this.getConcerts())
        {
            String currentArtist = concert.getArtist().getName();
            String currentVenue = concert.getVenue().getName();
            LocalDate dateByCurrentConcert = concert.getDate();

            if (currentArtist.equalsIgnoreCase(artistName) && currentVenue.equalsIgnoreCase(venueName) && dateByCurrentConcert == date)
            {
                return concert;
            }
        }

        return null;
    }

    public List<Concert> getConcertByArtist(List<Concert> concerts, Artist artist)
    {
        if (concerts == null)
        {
            throw new IllegalArgumentException("Concerts list cannot be null.");
        }

        if (concerts.contains(null))
        {
            throw new IllegalArgumentException("Concerts list cannot contain null elements.");
        }

        if (artist == null)
        {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        List<Concert> concertsWithTheRequiredArtist = new ArrayList<>();

        // Since a concert list is passed here I'll be looping over that list rather than the ones from the class Concert stored here
        for (Concert concert : concerts)
        {
            if (concert.getArtist() == artist)
            {
                concertsWithTheRequiredArtist.add(concert);
            }
        }

        return concertsWithTheRequiredArtist;
    }

    private List<Concert> getConcertByDate(List<Concert> concerts, LocalDate date)
    {
        if (concerts == null)
        {
            throw new IllegalArgumentException("Concerts list cannot be null.");
        }

        if (concerts.contains(null))
        {
            throw new IllegalArgumentException("Concerts list cannot contain null elements.");
        }

        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        List<Concert> concertsWithTheSameDate = new ArrayList<>();

        for (Concert concert : concerts)
        {
            if (concert.getDate() == date)
            {
                concertsWithTheSameDate.add(concert);
            }
        }

        return concertsWithTheSameDate;
    }

    private List<Concert> getConcertByVenue(List<Concert> concerts, Venue venue)
    {
        if (concerts == null)
        {
            throw new IllegalArgumentException("Concerts list cannot be null.");
        }

        if (concerts.contains(null))
        {
            throw new IllegalArgumentException("Concerts list cannot contain null elements.");
        }

        if (venue == null)
        {
            throw new IllegalArgumentException("Venue cannot be null.");
        }

        List<Concert> concertsInTheSameVenue = new ArrayList<>();

        for (Concert concert : concerts)
        {
            if (concert.getVenue() == venue)
            {
                concertsInTheSameVenue.add(concert);
            }
        }

        return concertsInTheSameVenue;
    }

    public Artist getArtistByName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        for (Artist artist : this.getArtists())
        {
            if (artist.getName().equalsIgnoreCase(name))
            {
                return artist;
            }
        }

        return null;
    }

    public Venue getVenueByName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        for (Venue venue : this.getVenues())
        {
            if (venue.getName().equalsIgnoreCase(name))
            {
                return venue;
            }
        }

        return null;
    }

    public void scheduleConcert(String artistName, String venueName, LocalDate date)
    {
        if (artistName == null || artistName.isBlank() || venueName == null || venueName.isBlank())
        {
            throw new IllegalArgumentException("ArtistName and venueName cannot be null or blank.");
        }

        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        if (!this.getVenues().contains(getVenueByName(venueName)))
        {
            throw new IllegalArgumentException("This venue couldn't be found in the system.");
        }

        for (Artist artist : this.getArtists())
        {
            if (artist.getName().equalsIgnoreCase(artistName) && !hasShowOnDate(artist, date))
            {
                this.addConcert(new Concert(artist, getVenueByName(venueName), date));
            }
        }

        throw new IllegalArgumentException("The artist couldn't be found in the system or has already a show for the required date.");
    }

    private boolean hasShowOnDate(Artist artist, LocalDate date)
    {
        if (artist == null || date == null)
        {
            throw new IllegalArgumentException("Artist and date cannot be null.");
        }

        for (Concert concert : this.getConcertByArtist(getConcerts(), artist))
        {
            if (concert.getDate() == date)
            {
                return true;
            }
        }

        return false;
    }

    public void removeCancelledConcerts()
    {
        Iterator<Concert> iterator = this.getConcerts().iterator();

        while (iterator.hasNext())
        {
            Concert concert = iterator.next();

            if (concert.getAmountOfTicketsSold() == 0)
            {
                iterator.remove();
            }
        }
    }

    private List<Concert> getConcertsInFuture()
    {
        LocalDate today = LocalDate.now();
        List<Concert> futureConcerts = new ArrayList<>();

        for (Concert concert : this.getConcerts())
        {
            if (concert.getDate().isAfter(today))
            {
                futureConcerts.add(concert);
            }
        }

        return futureConcerts;
    }

    private List<Concert> getConcertsWithZeroTicketsSold(List<Concert> concerts)
    {
        List<Concert> concertsWithZeroTicketsSold = new ArrayList<>();

        for (Concert concert : this.getConcerts())
        {
            if (concert.getAmountOfTicketsSold() == 0)
            {
                concertsWithZeroTicketsSold.add(concert);
            }
        }

        return concertsWithZeroTicketsSold;
    }

    public int getTotalRevenueInEuros(String artistName)
    {
        if (artistName == null || artistName.isBlank())
        {
            throw new IllegalArgumentException("ArtistName cannot be null or blank.");
        }

        int totalRevenueByArtist = 0;

        for (Concert concert : this.getConcerts())
        {
            if (concert.getArtist().getName().equalsIgnoreCase(artistName))
            {
                totalRevenueByArtist += concert.getRevenueInEuros();
            }
        }

        return totalRevenueByArtist;
    }
}