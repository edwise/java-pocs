package com.edwise.pocs.springdata.service.impl;

import com.edwise.pocs.springdata.entity.User;
import com.edwise.pocs.springdata.repository.UserRepository;
import com.edwise.pocs.springdata.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final long USER_ID_12 = 12l;
    private static final String NAME_GANDALF = "Gandalf";
    private static final int TYPE_1 = 1;
    private static final String PHONE_661534411 = "661534411";
    private static final String STRING_DATE_19110102 = "1911-01-02";
    private static final long USER_ID_23 = 23l;
    private static final String NAME_ARAGORN = "Aragorn";
    private static final int TYPE_2 = 2;
    private static final String PHONE_661534410 = "661534410";
    private static final String STRING_DATE_19511212 = "1951-12-12";
    private static final long USER_ID_44 = 44l;
    private static final String NAME_FRODO = "Frodo";
    private static final int TYPE_3 = 3;
    private static final String PHONE_661121212 = "661121212";
    private static final String STRING_DATE_19600311 = "1960-03-11";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    // TODO improve the name of the tests
    // TODO more tests for error cases, etc

    @Test
    public void findById() {
        when(userRepository.findOne(USER_ID_12)).thenReturn(
                createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102));

        User user = userService.findById(USER_ID_12);

        assertNotNull(user);
        assertThat(user.getId(), is(USER_ID_12));
        verify(userRepository).findOne(USER_ID_12);
    }

    @Test
    public void findAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102),
                createUser(USER_ID_23, NAME_ARAGORN, TYPE_2, PHONE_661534410, STRING_DATE_19511212),
                createUser(USER_ID_44, NAME_FRODO, TYPE_3, PHONE_661121212, STRING_DATE_19600311)
        ));

        List<User> users = userService.findAll();

        assertNotNull(users);
        assertThat(users, hasSize(3));
        verify(userRepository).findAll();
    }

    @Test
    public void save() {
        // TODO fix this test, with when and verify
        User user = userService.save(
                createUser(null, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102)
        );

        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void update() {
        User user = createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        when(userRepository.save(user)).then(returnsFirstArg());

        User userUpdated = userService.update(
                createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102)
        );

        assertNotNull(userUpdated);
        assertThat(userUpdated.getId(), is(USER_ID_12));
        verify(userRepository).save(user);
    }

    @Test
    public void delete() {
        userService.delete(USER_ID_12);
    }

    private User createUser(Long id, String name, int type, String phone, String stringDate) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setType(type);
        user.setPhone(phone);
        user.setBirthDate(LocalDate.parse(stringDate));

        return user;
    }
}