package com.lambdaschool.shoppingcart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.shoppingcart.models.*;
import com.lambdaschool.shoppingcart.services.UserAuditing;
import com.lambdaschool.shoppingcart.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @RunWith(SpringRunner.class)
// @WebMvcTest(value = UserController.class)

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "jimbotestv1", roles = {"USER", "ADMIN"})
public class UserControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserAuditing userAuditing;

	@MockBean
	private UserService userService;

	private List<User> userList;

	@Before
	public void setUp() throws Exception {
		userList = new ArrayList<>();

		Role r1 = new Role("admin");
		r1.setRoleid(1);

		Role r2 = new Role("user");
		r2.setRoleid(2);

		ArrayList<Cart> carts1 = new ArrayList<>();
		carts1.add(new Cart());
		carts1.add(new Cart());

		ArrayList<UserRoles> roles1 = new ArrayList<>();
		roles1.add(new UserRoles(new User(), r1));
		roles1.add(new UserRoles(new User(), r2));
		User user1 = new User("jimbotestv1", "password", "", carts1, roles1);
		user1.setUserid(100);

		userList.add(user1);

		ArrayList<Cart> carts2 = new ArrayList<>();
		carts2.add(new Cart());
		carts2.add(new Cart());

		ArrayList<UserRoles> roles2 = new ArrayList<>();
		roles2.add(new UserRoles(new User(), r1));
		roles2.add(new UserRoles(new User(), r2));
		User user2 = new User("jimbotestv2", "password", "", carts2, roles2);
		user2.setUserid(200);

		userList.add(user2);
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
	public void getUserById() throws Exception {
		String apiUrl = "/users/user/2";
		Mockito.when(userService.findUserById(2)).thenReturn(userList.get(1));
		RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

		MvcResult r = mockMvc.perform(rb).andReturn();
		String tr = r.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		String er = mapper.writeValueAsString(userList.get(1));

		System.out.println("Expect - " + er);
		System.out.println("Actual - " + tr);
		assertEquals("Rest Api Returns List", er, tr);
	}

	@Test
	public void getUserInfo() throws Exception {
		String apiUrl = "/users/myinfo";
		Mockito.when(userService.findByName(userAuditing.getCurrentAuditor().get())).thenReturn(userList.get(0));
		RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

		MvcResult r = mockMvc.perform(rb).andReturn();
		String tr = r.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		String er = mapper.writeValueAsString(userList.get(0));

		System.out.println("Expect - " + er);
		System.out.println("Actual - " + tr);
		assertEquals("Rest Api Returns List", er, tr);
	}

	@Test
	public void addUser() throws Exception {
		// String apiUrl = "/users/user";
		// ArrayList<Cart> thisCarts = new ArrayList<>();
		// ArrayList<UserRoles> thisRoles = new ArrayList<>();
		// User user3 = new User("jimbotestv3", "password", "", thisCarts, thisRoles);
		// user3.setUserid(300);
		// System.out.println(userService.save(any(User.class)));
		//
		// ObjectMapper mapper = new ObjectMapper();
		// String userString = mapper.writeValueAsString(user3);
		// Mockito.when(userService.save(any(User.class))).thenReturn(user3);
		//
		// RequestBuilder rb = MockMvcRequestBuilders
		// 	.post(apiUrl)
		// 	.contentType(MediaType.APPLICATION_JSON)
		// 	.accept(MediaType.APPLICATION_JSON)
		// 	.content(userString);
		//
		// mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteUserById() throws Exception {
		String apiUrl = "/users/user/{userid}";
		RequestBuilder rb = MockMvcRequestBuilders
			.delete(apiUrl, 200)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(rb).andExpect((status().isOk())).andDo(MockMvcResultHandlers.print());
	}
}