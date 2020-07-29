package myexam.service;

import myexam.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel addUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

}
