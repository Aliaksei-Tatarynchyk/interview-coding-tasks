package com.interview.coding.tasks.codereview.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Need to perform a code review of this cache solution.
 * Then need to fix all found issues.
 *
 * @author Aliaksei Tatarynchyk
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Cacheable(cacheNames = "users", key = "#id")
    public User getUser(String id) {
        return userDAO.find(id);
    }

}
