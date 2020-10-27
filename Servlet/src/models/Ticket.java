package models;

import java.util.Objects;
import dtos.TicketFormData;

public class Ticket {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id) &&
                departurePlace.equals(ticket.departurePlace) &&
                arrivalPlace.equals(ticket.arrivalPlace) &&
                company.equals(ticket.company) &&
                departureTime.equals(ticket.departureTime) &&
                arrivalTime.equals(ticket.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departurePlace, arrivalPlace, company, departureTime, arrivalTime);
    }

    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private String company;
    private String departureTime;
    private String arrivalTime;

    public Ticket(Long id, String departurePlace, String arrivalPlace, String company, String departureTime, String arrivalTime) {
        this.id = id;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.company = company;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static Ticket from(TicketFormData ticketFormData) {
        return new Ticket(ticketFormData.getId(),ticketFormData.getDeparturePlace(),ticketFormData.getArrivalPlace(),
                ticketFormData.getCompany(),ticketFormData.getDepartureTime(),ticketFormData.getArrivalTime());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", departurePlace='" + departurePlace + '\'' +
                ", arrivalPlace='" + arrivalPlace + '\'' +
                ", company='" + company + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }

    public Ticket() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}