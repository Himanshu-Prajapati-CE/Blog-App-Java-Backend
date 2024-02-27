package com.blog.app.services;

import com.blog.app.payloads.UserDTO;

import java.util.List;

public interface UserService {

   UserDTO createUser(UserDTO userDTO);
   UserDTO updateUser(UserDTO userDTO, Integer userId);
   UserDTO getUserById(Integer userId);
   List<UserDTO> getAllUsers();
   void deleteUser(Integer userId);
}