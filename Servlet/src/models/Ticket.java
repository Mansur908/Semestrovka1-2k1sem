package models;

import java.util.Objects;
import dtos.TicketFormData;

public class Ticket {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(departurePlace, ticket.departurePlace) &&
                Objects.equals(arrivalPlace, ticket.arrivalPlace) &&
                Objects.equals(company, ticket.company) &&
                Objects.equals(departureTime, ticket.departureTime) &&
                Objects.equals(arrivalTime, ticket.arrivalTime) &&
                Objects.equals(day, ticket.day) &&
                Objects.equals(price, ticket.price) &&
                Objects.equals(link, ticket.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departurePlace, arrivalPlace, company, departureTime, arrivalTime, day, price, link);
    }

    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private String company;
    private String departureTime;
    private String arrivalTime;
    private String day;
    private String price;
    private String link;

    public Ticket(Long id, String departurePlace, String arrivalPlace, String company, String departureTime, String arrivalTime, String day, String price, String link) {
        this.id = id;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.company = company;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.day = day;
        this.price = price;
        this.link = link;
    }

    public static Ticket from(TicketFormData ticketFormData) {
        return new Ticket(ticketFormData.getId(),ticketFormData.getDeparturePlace(),ticketFormData.getArrivalPlace(),
                ticketFormData.getCompany(),ticketFormData.getDepartureTime(),ticketFormData.getArrivalTime(),ticketFormData.getDay(),ticketFormData.getPrice(),ticketFormData.getLink());
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
                ", day='" + day + '\'' +
                ", price='" + price + '\'' +
                ", link='" + link + '\'' +
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}