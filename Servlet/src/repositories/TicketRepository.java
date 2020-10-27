package repositories;

import models.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket>{

    List<Ticket> findByPlaces(String departurePlace, String arrivalPlace);
}
