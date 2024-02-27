package com.blog.app.modelmapper;

import com.blog.app.entities.User;
import com.blog.app.payloads.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings() {
        // Create a TypeMap for User to UserDTO
        TypeMap<User, UserDTO> userToUserDTOMapping = modelMapper.createTypeMap(User.class, UserDTO.class);

        // Define property mappings for User to UserDTO
        userToUserDTOMapping.addMapping(User::getId, UserDTO::setId);
        userToUserDTOMapping.addMapping(User::getName, UserDTO::setName);
        userToUserDTOMapping.addMapping(User::getEmail, UserDTO::setEmail);
        userToUserDTOMapping.addMapping(User::getAbout, UserDTO::setAbout);
        // Add more mappings as needed

        // Create a TypeMap for UserDTO to User
        TypeMap<UserDTO, User> userDTOToUserMapping = modelMapper.createTypeMap(UserDTO.class, User.class);

        // Define property mappings for UserDTO to User
        userDTOToUserMapping.addMapping(UserDTO::getId, User::setId);
        userDTOToUserMapping.addMapping(UserDTO::getName, User::setName);
        userDTOToUserMapping.addMapping(UserDTO::getEmail, User::setEmail);
        userDTOToUserMapping.addMapping(UserDTO::getAbout, User::setAbout);
    }

    public UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
