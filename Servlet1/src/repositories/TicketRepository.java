package repositories;

import models.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket>{

    List<Ticket> findByPlaces(String departurePlace, String arrivalPlace,String day);
    Ticket insertTicket (String departurePlace,String arrivalPlace,String company,String departureTime,String arrivalTime,String day,String price,String link);
    Ticket findTicket (String departurePlace,String arrivalPlace,String company,String departureTime,String arrivalTime,String day,String price);
}