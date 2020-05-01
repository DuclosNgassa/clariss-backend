package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.User;
import com.kmerconsulting.clariss.repository.UserRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity User with id: " + id + "not found"));
    }

    public List<User> findByStatus(GlobalStatus status) {
        return userRepository.findByStatus(status).orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isUserActive(User user) {
        return user.getStatus() == GlobalStatus.active;
    }

    public boolean isUserPending(User user) {
        return user.getStatus() == GlobalStatus.pending;
    }

    public boolean isUserBlocked(User user) {
        return user.getStatus() == GlobalStatus.blocked;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
