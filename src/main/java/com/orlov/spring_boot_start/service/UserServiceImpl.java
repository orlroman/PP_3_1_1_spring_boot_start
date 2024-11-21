package com.orlov.spring_boot_start.service;

import com.orlov.spring_boot_start.dao.UserDao;
import com.orlov.spring_boot_start.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserDao userDao;
    
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }
    
    @Override
    @Transactional
    public void delete(int id) {
        User user = userDao.getUser(id);
        if (user == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userDao.delete(id);
    }
    
    @Override
    @Transactional
    public void update(int id, User user) {
        User existingUser = userDao.getUser(id);
        if (existingUser == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userDao.update(id, user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        User user = userDao.getUser(id);
        if (user == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        return userDao.getUser(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
