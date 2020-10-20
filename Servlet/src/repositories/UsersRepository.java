package repositories;

import models.User;

public interface UsersRepository extends CrudRepository<User>{

    User findByUsername(String username);
    User insertUser(String username,String password);
    User changePassword(String newPassord,String oldPassword,String username) throws IllegalStateException;


}
