package com.edwise.pocs.springdata;

import com.edwise.pocs.springdata.entity.User;
import com.edwise.pocs.springdata.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTest {
    private static final String NAME_GANDALF = "Gandalf";
    private static final int TYPE_1 = 1;
    private static final String PHONE_631534411 = "631534411";
    private static final String STRING_DATE_19110102 = "1911-01-02";
    private static final String NAME_ARAGORN = "Aragorn";
    private static final int TYPE_2 = 2;
    private static final String PHONE_661534410 = "661534410";
    private static final String STRING_DATE_19511212 = "1951-12-12";
    private static final String NAME_FRODO = "Frodo";
    private static final String PHONE_661121212 = "661121212";
    private static final String STRING_DATE_19600311 = "1960-03-11";

    @Autowired
    private UserRepository userRepository;

    private User userGandalf;
    private User userAragorn;
    private User userFrodo;

    @Before
    public void setUp() {
        userGandalf = userRepository.save(createUser(null, NAME_GANDALF, TYPE_1, PHONE_631534411, STRING_DATE_19110102));
        userAragorn = userRepository.save(createUser(null, NAME_ARAGORN, TYPE_1, PHONE_661534410, STRING_DATE_19511212));
        userFrodo = userRepository.save(createUser(null, NAME_FRODO, TYPE_2, PHONE_661121212, STRING_DATE_19600311));
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testUserRepositoryNamedMethod() {
        List<User> usersWithType2 = userRepository.findByType(TYPE_1);

        assertNotNull(usersWithType2);
        assertThat(usersWithType2, hasSize(2));
        assertThat(usersWithType2, containsInAnyOrder(userGandalf, userAragorn));
    }

    @Test
    public void testUserRepositoryNamedMethodQuery() {
        List<User> usersWithNameFrodo = userRepository.findByNameIgnoreCase(NAME_FRODO);

        assertNotNull(usersWithNameFrodo);
        assertThat(usersWithNameFrodo, hasSize(1));
        assertThat(usersWithNameFrodo, contains(userFrodo));
    }

    @Test
    public void testUserRepositoryQueryAnnotation() {
        List<User> usersWithPrefixPhone = userRepository.findByPhonePrefix("661");

        assertNotNull(usersWithPrefixPhone);
        assertThat(usersWithPrefixPhone, hasSize(2));
        assertThat(usersWithPrefixPhone, containsInAnyOrder(userFrodo, userAragorn));
    }

    private User createUser(Long id, String name, Integer type, String phone, String stringDate) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setType(type);
        user.setPhone(phone);
        if (stringDate != null) {
            user.setBirthDate(LocalDate.parse(stringDate));
        }

        return user;
    }
}