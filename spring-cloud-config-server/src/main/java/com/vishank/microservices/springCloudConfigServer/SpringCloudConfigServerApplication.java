package com.vishank.microservices.springCloudConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * This application is a spring cloud config server. It is supposed to serve configurations
 * to other microservices. The configuration data is stored in a git repository and this
 * application will fetch the configuration from that repository and distribute it to
 * other microservices that are configured to use this application as their configuration
 * server.
 */
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}

}
