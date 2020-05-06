package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.mapper.UserMapper;
import com.kmerconsulting.clariss.mapper.UserRoleMapper;
import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.User;
import com.kmerconsulting.clariss.model.UserDTO;
import com.kmerconsulting.clariss.model.UserRole;
import com.kmerconsulting.clariss.model.UserRoleDTO;
import com.kmerconsulting.clariss.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody User user) throws Exception {
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(UserRole.user);
        user.setPassword("TEMP_PASSWORD");
        user.setStatus(GlobalStatus.active);
        User createdUser = userService.save(user);

        if (createdUser == null) {
            throw new Exception("Error while saving user");
        }

        UserDTO createdUserDTO = userMapper.mapToBasisDTO(createdUser);

        return ResponseEntity.ok(createdUserDTO);
    }

    @PutMapping("/role/update/{id}")
    public ResponseEntity<UserDTO> changeRole(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRoleDTO userRoleDTO) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserRole userRole = userRoleMapper.mapDTOToUserRole(userRoleDTO);
        user.setRole(userRole != null ? userRole : user.getRole());

        User updatedUser = userService.update(user);

        UserDTO userDTO = userMapper.mapToBasisDTO(updatedUser);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();

        if (users == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOS = users.stream().map(userMapper::mapToBasisDTO).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = userMapper.mapToBasisDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/email/{email}/")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable(value = "email") String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = userMapper.mapToBasisDTO(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserDTO>> findAllActive() {
        List<User> users = userService.findByStatus(GlobalStatus.active);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOS = users.stream().map(userMapper::mapToBasisDTO).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    /**
     * get all user with the status blocked
     *
     * @return
     */
    @GetMapping("/blocked")
    public ResponseEntity<List<UserDTO>> findAllBlocked() {
        List<User> users = userService.findByStatus(GlobalStatus.blocked);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOS = users.stream().map(userMapper::mapToBasisDTO).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    /**
     * This method updates a user based on his id and his details
     *
     * @param userDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO userDetail) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setName(userDetail.getName() != null ? userDetail.getName() : user.getName());
        user.setEmail(userDetail.getEmail() != null ? userDetail.getEmail() : user.getEmail());
        user.setPhone(userDetail.getPhone() != null ? userDetail.getPhone() : user.getPhone());
        user.setAddressId(userDetail.getAddressId() != null ? userDetail.getAddressId() : user.getAddressId());
        user.setDeviceToken(userDetail.getDeviceToken() != null ? userDetail.getDeviceToken() : user.getDeviceToken());
        user.setDeviceId(userDetail.getDeviceId() != null ? userDetail.getDeviceId() : user.getDeviceId());
        user.setStatus(userDetail.getStatus() != null ? userDetail.getStatus() : user.getStatus());

        User updatedUser = userService.update(user);

        UserDTO userDTO = userMapper.mapToBasisDTO(updatedUser);

        return ResponseEntity.ok(userDTO);
    }

    /**
     * This method deletes a user based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(id);

        UserDTO userDTO = userMapper.mapToBasisDTO(user);

        return ResponseEntity.ok(userDTO);
    }
}
