package com.interview.coding.tasks.codereview.cache;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class UserDAO {
    private static final Random random = new Random();

    public User find(String id) {
        return new User(id, generateRandomString());
    }

    private String generateRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        return random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    }

}
