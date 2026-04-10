package com.nhlstenden.ticketservice;

public class Ticket
{
    private String code;

    public Ticket(Concert concert)
    {
        this.setCode();
        this.setConcert(concert);
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode()
    {
        this.code = code;
    }

    public void setConcert(Concert concert)
    {
        if (concert == null)
        {
            throw new IllegalArgumentException("Concert cannot be null.");
        }

        this.setConcert(concert);
    }
}
