package com.interview.coding.tasks.codereview.cache;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class UserServiceTestConfig {

    @Bean
    @Primary
    public UserDAO userDAO() {
        return Mockito.mock(UserDAO.class);
    }
}
