package com.lambdaschool.shoppingcart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.shoppingcart.models.*;
import com.lambdaschool.shoppingcart.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private List<User> userList;

	@Before
	public void setUp() throws Exception {
		userList = new ArrayList<>();

		ArrayList<Role> roles = new ArrayList<>();
		Role r1 = new Role("admin");
		r1.setRoleid(1);
		roles.add(r1);

		Role r2 = new Role("user");
		r2.setRoleid(2);
		roles.add(r2);

		ArrayList<Cart> carts = new ArrayList<>();
		carts.add(new Cart());
		carts.add(new Cart());

		ArrayList<UserRoles> userRoles = new ArrayList<>();
		userRoles.add(new UserRoles(new User(), r1));
		userRoles.add(new UserRoles(new User(), r2));
		User user1 = new User("jimbotestv1", "password", "", carts, userRoles);
		user1.setUserid(100);

		userList.add(user1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void listAllUsers() throws Exception {
		String apiUrl = "/users/users";
		Mockito.when(userService.findAll()).thenReturn(userList);
		RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

		MvcResult r = mockMvc.perform(rb).andReturn();
		String tr = r.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		String er = mapper.writeValueAsString(userList);

		System.out.println("Expect - " + er);
		System.out.println("Actual - " + tr);
		assertEquals("Rest Api Returns List", er, tr);
	}

	@Test
	public void getUserById() {
	}

	@Test
	public void getUserInfo() {
	}

	@Test
	public void addUser() {
	}

	@Test
	public void deleteUserById() {
	}
}