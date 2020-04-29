package com.kmerconsulting.clariss.mapper;

import com.kmerconsulting.clariss.model.UserRole;
import com.kmerconsulting.clariss.model.UserRoleDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRoleDTO mapUserRoleToDTO(UserRole userRole){
        switch (userRole){
            case admin:
                return buildUserRoleDTO("admin");
            case manager:
                return buildUserRoleDTO("manager");
            case user:
            default:
                return buildUserRoleDTO("user");
        }
    }

    public UserRole mapDTOToUserRole(UserRoleDTO userRoleDTO){
        switch (userRoleDTO.getRole()){
            case "admin":
                return UserRole.admin;
            case "manager":
                return UserRole.manager;
            case "user":
            default:
                return UserRole.user;
        }
    }

    UserRoleDTO buildUserRoleDTO(String role){
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRole(role);
        return userRoleDTO;
    }
}
