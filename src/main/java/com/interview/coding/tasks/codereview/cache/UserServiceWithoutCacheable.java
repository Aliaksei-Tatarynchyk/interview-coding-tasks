package com.interview.coding.tasks.codereview.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceWithoutCacheable { // should not be final because spring beans are proxies
    /*
     static - for being able to use it with "prototype" beans.
     final - to prohibit modifications
     ConcurrentHashMap - to make it thread-safe
     */
    private static final Map<String, User> cache = new ConcurrentHashMap<>();

    @Autowired // should be applied to constructor or to setter
    private UserDAO userDAO;

    public User getUser(String id) {
        User user = cache.get(id);
        if (user != null) { // cache may not contain an item
            return user; // need to care about cache staleness
        }

        user = userDAO.find(id);
        if (user != null) { // it's not wise to put null to cache
            cache.put(id, user);
        }

        return user;
    }
}
