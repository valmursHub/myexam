package myexam.service.impl;

import myexam.model.entity.User;
import myexam.model.service.UserServiceModel;
import myexam.repository.UserRepository;
import myexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel addUser(UserServiceModel userServiceModel) {
        return this.modelMapper
                .map(this.userRepository
                        .saveAndFlush(this.modelMapper
                                .map(userServiceModel, User.class)), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }

}
