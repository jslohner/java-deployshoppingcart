package com.lambdaschool.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main class to start the application.
 */
// @EnableJpaAuditing
@SpringBootApplication
public class ShoppingcartApplication {
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

	public static void main(String[] args) {
		// checkEnvironmentVariable("OAUTHCLIENTID");
		// checkEnvironmentVariable("OAUTHCLIENTSECRET");

		SpringApplication.run(ShoppingcartApplication.class,
			args);

		// if (!stop) {
		// }
	}
}
