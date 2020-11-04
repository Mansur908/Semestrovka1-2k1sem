package repositories;

import models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message> {

    @Override
    List<Message> findAll();
    Message insertMessage(String username,String message,String date);
}
