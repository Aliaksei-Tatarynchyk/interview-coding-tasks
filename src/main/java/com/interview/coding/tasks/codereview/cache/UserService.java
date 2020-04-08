package com.interview.coding.tasks.codereview.cache;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Need to perform a code review of this cache solution.
 * Then need to fix all found issues.
 *
 * @author Aliaksei Tatarynchyk
 */
@Service
public class UserService {
    private final Map<String, User> cache = new ConcurrentHashMap<>();
    private volatile LocalDateTime lastCacheRefreshTime = LocalDateTime.now();

    @Value("${user.cache.time.to.live.seconds}")
    private Integer cacheTimeToLiveSeconds = 300;

    @Autowired
    private UserDAO userDAO;

    public User getUser(String id) {
        if (isStaleCache()) {
            refreshCache();
        } else if (cache.containsKey(id)) {
            return cache.get(id);
        }

        User user = userDAO.find(id);
        if (user != null) {
            cache.put(id, user);
        }

        return user;
    }

    private boolean isStaleCache() {
        return LocalDateTime.now().minusSeconds(cacheTimeToLiveSeconds).isAfter(lastCacheRefreshTime);
    }

    private void refreshCache() {
        cache.clear();
        lastCacheRefreshTime = LocalDateTime.now();
    }
}
