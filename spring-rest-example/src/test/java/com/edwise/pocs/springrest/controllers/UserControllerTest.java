package com.edwise.pocs.springrest.controllers;

import com.edwise.pocs.springrest.entities.User;
import com.edwise.pocs.springrest.services.UserService;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final long USER_ID_12 = 12l;
    private static final long USER_ID_140 = 140l;
    private static final long USER_ID_453321 = 45332l;
    private static final String NAME_GANDALF = "Gandalf";
    private static final String NAME_ARAGORN = "Aragorn";
    private static final String NAME_FRODO = "Frodo";
    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;
    private static final String PHONE_666554433 = "666554433";
    private static final String PHONE_661534411 = "661534411";
    private static final String PHONE_666222211 = "666222211";
    private static final String STRING_DATE_19110102 = "1911-01-02";
    private static final String STRING_DATE_19230716 = "1923-07-16";
    private static final String STRING_DATE_19511124 = "1951-11-24";

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test
    public void getAllUsers_withUsers_returnSeveralUsers() {
        when(userService.findAll()).thenReturn(Arrays.asList(
                createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_666554433, STRING_DATE_19110102),
                createUser(USER_ID_140, NAME_ARAGORN, TYPE_1, PHONE_661534411, STRING_DATE_19230716),
                createUser(USER_ID_453321, NAME_FRODO, TYPE_2, PHONE_666222211, STRING_DATE_19511124)
        ));

        List<User> users = userController.getAllUsers();

        verify(userService).findAll();
        assertNotNull(users);
        assertThat(users, hasSize(3));
    }

    @Test
    public void getAllUsers_withoutUsers_returnEmptyList() {
        when(userService.findAll()).thenReturn(new ArrayList<>(0));

        List<User> users = userController.getAllUsers();

        verify(userService).findAll();
        assertNotNull(users);
        assertThat(users, hasSize(0));
    }

    @Test
    public void getUser_existUser_returnUser() {
        when(userService.findById(USER_ID_12)).thenReturn(
                createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102));

        ResponseEntity<User> response = userController.getUser(USER_ID_12);

        verify(userService).findById(USER_ID_12);
        assertNotNull(response);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), isA(User.class));
        assertThat(response.getBody().getId(), is(USER_ID_12));
    }

    @Test
    public void getUser_notExistUser_returnNotFound() {
        when(userService.findById(USER_ID_12)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(USER_ID_12);


        verify(userService).findById(USER_ID_12);
        assertNotNull(response);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertNull(response.getBody());
    }

    @Test
    public void insertUser() {
        User user = createUser(null, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        User userSaved = createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        when(userService.save(any(User.class))).thenReturn(userSaved);

        ResponseEntity<User> response = userController.insertUser(user);

        verify(userService).save(user);
        assertNotNull(response);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertNull(response.getBody());
        assertThat(response.getHeaders().getLocation().toString(), endsWith("/" + USER_ID_12));
    }

    @Test
    public void updateUser_existsUser_updateTheUser() {
        User userOld = createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        User user = createUser(USER_ID_12, null, null, PHONE_666222211, null);
        when(userService.findById(USER_ID_12)).thenReturn(userOld);
        when(userService.update(any(User.class))).then(returnsFirstArg());

        userController.updateUser(USER_ID_12, user);

        verify(userService).findById(USER_ID_12);
        verify(userService).update(userOld.setPhone(PHONE_666222211));
    }

    @Test
    public void updateUser_notExistUser_noUpdateTheUser() {
        when(userService.findById(USER_ID_12)).thenReturn(null);
        User user = createUser(USER_ID_12, null, null, PHONE_666222211, null);

        userController.updateUser(USER_ID_12, user);

        verify(userService).findById(USER_ID_12);
        verify(userService, never()).update(any(User.class));
    }

    @Test
    public void deleteUser() {
        doNothing().when(userService).delete(anyLong());

        userController.deleteUser(USER_ID_12);

        verify(userService).delete(USER_ID_12);
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