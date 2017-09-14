package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.UserRepository;

public class HomeControllerTests {
	
	private PasswordEncoder encoder;
	private UserRepository repo;
	private HomeController controller;
	
	@Before
	public void setup() {
		encoder = mock(PasswordEncoder.class);
		repo = mock(UserRepository.class);
		controller = new HomeController(repo, encoder);
	}

	@Test
	public void test_home_uses_default_template() {
		String actual = controller.home();
		
		assertThat(actual).isEqualTo("home/default");
	}
	
	@Test
	public void test_signup_uses_signup_template() {
		String actual = controller.signup();
		
		assertThat(actual).isEqualTo("home/signup");
	}
	
	@Test
	public void test_handleSignup_encodes_password_saves_user_and_redirects_to_login() {
		User user = new User();
		user.setPassword("secret");
		when(encoder.encode("secret")).thenReturn("encrypted");
		
		ModelAndView mv = controller.handleSignup(user);
		 
		verify(repo).save(user);
		verify(encoder).encode("secret");
		assertThat(user.getPassword()).isEqualTo("encrypted");
		assertThat(mv.getViewName()).isEqualTo("redirect:/login");
	}
	
	@Test
	public void test_handleSignup_encodes_password_saves_user_and_renders_signup_for_DataIntegrityViolationException() {
		User user = new User();
		user.setPassword("secret");
		when(encoder.encode("secret")).thenReturn("encrypted");
		when(repo.save(user)).thenThrow(new DataIntegrityViolationException(""));
		
		ModelAndView mv = controller.handleSignup(user);
		
		verify(repo).save(user);
		verify(encoder).encode("secret");
		assertThat(user.getPassword()).isEqualTo("encrypted");
		assertThat(mv.getViewName()).isEqualTo("home/signup");
		assertThat(mv.getModel().get("errorMessage")).isEqualTo("Cannot signup with that username");
	}

}
