package com.blog.app.controllers;

import com.blog.app.payloads.APIResponse;
import com.blog.app.payloads.UserDTO;
import com.blog.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create users
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){

        UserDTO createdUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    //update users
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,
                                              @PathVariable("id") Integer userId){
        UserDTO userDTOUser = userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(userDTOUser);
    }

    //delete users
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<APIResponse>(new APIResponse("User Deleted!!", true), HttpStatus.OK);
    }

    //get All user
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //get single user
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable("id") Integer id){
        UserDTO userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

}
