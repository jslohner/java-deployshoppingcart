package com.lambdaschool.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main class to start the application.
 */
// @EnableJpaAuditing
@SpringBootApplication
public class ShoppingcartApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}

	// @Autowired
	// private static Environment env;
	//
	// private static boolean stop = false;
	//
	// private static void checkEnvironmentVariable(String envvar) {
	// 	if (System.getenv(envvar) == null) {
	// 		stop = true;
	// 	}
	// }
	//
	// public static void main(String[] args) {
	// 	checkEnvironmentVariable("OAUTHCLIENTID");
	// 	checkEnvironmentVariable("OAUTHCLIENTSECRET");
	//
	// 	if (!stop) {
	// 		SpringApplication.run(ShoppingcartApplication.class,
	// 			args);
	// 	}
	// }
}
