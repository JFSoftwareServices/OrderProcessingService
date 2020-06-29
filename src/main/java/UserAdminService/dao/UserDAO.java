package UserAdminService.dao;

import UserAdminService.dto.User;
import UserAdminService.util.IDGenerator;

public class UserDAO {

    public int create(User user) {
        int id = IDGenerator.generateID();
        //Save the user object to db
        return id;
    }
}