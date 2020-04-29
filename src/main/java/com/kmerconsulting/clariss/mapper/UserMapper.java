package com.kmerconsulting.clariss.mapper;

import com.kmerconsulting.clariss.model.User;
import com.kmerconsulting.clariss.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    @Override
    public User mapToBasisEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddressId(userDTO.getAddressId());
        user.setDeviceId(userDTO.getDeviceId());
        user.setDeviceToken(userDTO.getDeviceToken());
        user.setStatus(userDTO.getStatus());
        user.setRole(userDTO.getRole());
        user.setRating(userDTO.getRating());

        return user;
    }

    @Override
    public UserDTO mapToBasisDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddressId(user.getAddressId());
        userDTO.setDeviceId(user.getDeviceId());
        userDTO.setDeviceToken(user.getDeviceToken());
        userDTO.setStatus(user.getStatus());
        userDTO.setRole(user.getRole());
        userDTO.setRating(user.getRating());

        return userDTO;
    }
}
