package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.ShoppingcartApplication;
import com.lambdaschool.shoppingcart.models.Cart;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingcartApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void aFindAll() {
		assertEquals(3, userService.findAll().size());
	}

	@Test
	public void bFindUserById() {
		assertEquals("test stumps", userService.findUserById(3).getUsername());
	}

	@Test
	public void cFindByName() {
		assertEquals("test cinnamon", userService.findByName("test cinnamon").getUsername());
	}

	@Test
	public void dDelete() {
		userService.delete(3);
		assertEquals(2, userService.findAll().size());
	}

	@Test
	public void eSave() {
		List<Cart> thisCarts = new ArrayList<>();
		List<UserRoles> thisRoles = new ArrayList<>();
		User newUser = new User("testjimbov1", "password", "", thisCarts, thisRoles);

		User savedUser = userService.save(newUser);
		assertEquals("testjimbov1", savedUser.getUsername());
	}
}