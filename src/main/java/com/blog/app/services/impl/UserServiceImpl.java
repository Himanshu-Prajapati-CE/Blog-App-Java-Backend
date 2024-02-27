package com.blog.app.services.impl;

import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFondException;
import com.blog.app.modelmapper.UserMapper;
import com.blog.app.payloads.UserDTO;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = userMapper.mapToEntity(userDTO);
        User savedUser = userRepository.save(user);

        return userMapper.mapToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFondException("User", "id", userId));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User updatedUser = userRepository.save(user);

        UserDTO updatedUserDTO = userMapper.mapToDTO(updatedUser);

        return updatedUserDTO;
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFondException("User", "id", userId));

        return userMapper.mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> allUsers = userRepository.findAll();

        List<UserDTO> listOfUserDTOs =
                allUsers.stream().map(user -> userMapper.mapToDTO(user)).collect(Collectors.toList());

        return listOfUserDTOs;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFondException("User", "id", userId));
        userRepository.deleteById(userId);
    }
}
