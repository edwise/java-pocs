package com.edwise.pocs.jerseyrest.resource;

import com.edwise.pocs.jerseyrest.entity.User;
import com.edwise.pocs.jerseyrest.service.UserService;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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

    @Mock
    private UriInfo uriInfo;

    @InjectMocks
    private UserController userController;

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
        when(userService.findAll()).thenReturn(new ArrayList<User>(0));

        List<User> users = userController.getAllUsers();

        verify(userService).findAll();
        assertNotNull(users);
        assertThat(users, hasSize(0));
    }

    @Test
    public void getUser_existUser_returnUser() {
        when(userService.findById(USER_ID_12)).thenReturn(
                createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102));

        Response response = userController.getUserById(USER_ID_12);

        verify(userService).findById(USER_ID_12);
        assertNotNull(response);
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertNotNull(response.getEntity());
        assertThat(((User)response.getEntity()).getId(), is(USER_ID_12));
    }

    @Test
    public void getUser_notExistUser_returnNotFound() {
        when(userService.findById(USER_ID_12)).thenReturn(null);

        Response response = userController.getUserById(USER_ID_12);

        verify(userService).findById(USER_ID_12);
        assertNotNull(response);
        assertThat(response.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
    }

    @Test
    public void insertUser() {
        User user = createUser(null, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        User userSaved = createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        UriBuilder uriBuilder = mock(UriBuilder.class);
        URI uri = URI.create("http://localhost:8080/api/users/" + USER_ID_12);
        when((uriInfo).getRequestUriBuilder()).thenReturn(uriBuilder);
        when((uriBuilder).path(anyString())).thenReturn(uriBuilder);
        when((uriBuilder).build()).thenReturn(uri);
        when(userService.save(any(User.class))).thenReturn(userSaved);

        Response response = userController.insertUser(user);

        verify(userService).save(user);
        assertNotNull(response);
        assertThat(response.getStatus(), is(Response.Status.CREATED.getStatusCode()));
        assertThat(response.getLocation().toString(), is("http://localhost:8080/api/users/" + USER_ID_12));
    }

    @Test
    public void updateUser_existsUser_updateTheUser() {
        User userOld = createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        User user = createUser(USER_ID_12, null, null, PHONE_666222211, null);
        when(userService.findById(USER_ID_12)).thenReturn(userOld);
        when(userService.update(any(User.class))).then(returnsFirstArg());

        Response response = userController.updateUser(USER_ID_12, user);

        verify(userService).findById(USER_ID_12);
        verify(userService).update(userOld.setPhone(PHONE_666222211));
        assertNotNull(response);
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));
    }

    @Test
    public void updateUser_notExistUser_noUpdateTheUser() {
        when(userService.findById(USER_ID_12)).thenReturn(null);
        User user = createUser(USER_ID_12, null, null, PHONE_666222211, null);

        Response response = userController.updateUser(USER_ID_12, user);

        verify(userService).findById(USER_ID_12);
        verify(userService, never()).update(any(User.class));
        assertNotNull(response);
        assertThat(response.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
    }

    @Test
    public void deleteUser() {
        doNothing().when(userService).delete(anyLong());

        Response response = userController.deleteUser(USER_ID_12);

        verify(userService).delete(USER_ID_12);
        assertNotNull(response);
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));
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