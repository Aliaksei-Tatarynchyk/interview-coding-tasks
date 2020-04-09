package com.interview.coding.tasks.codereview.cache;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atMostOnce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.interview.coding.tasks.DemoApplication;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @Test
    public void userRequests_shouldBeCached() {
        given(userDAO.find("u1234")).willReturn(new User("u1234", "Vasia Pupkin"));

        userService.getUser("u1234");
        userService.getUser("u1234");

        then(userDAO).should(atMostOnce()).find("u1234");
    }
}
