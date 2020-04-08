package com.interview.coding.tasks.codereview.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Need to perform a code review of this cache solution.
 * Then need to fix all found issues.
 *
 * @author Aliaksei Tatarynchyk
 */
@Service
public class UserService {
    static Map cache = new HashMap<>();

    @Autowired
    private UserDAO userDAO;

    public User getUser(String id) {
        if (cache.containsKey(id)) {
            return (User) cache.get(id);
        }

        return userDAO.find(id);
    }
}
