package com.hydatis.KycmicroserviceCQRS;

import com.hydatis.KycmicroserviceCQRS.command.model.*;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.EtatDeCompte;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.SourceAlimentation;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeAgent;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDocument;
import com.hydatis.KycmicroserviceCQRS.command.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.Arrays;
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.hydatis.KycmicroserviceCQRS")
@EnableMongoRepositories
public class KycMicroserviceCqrsApplication {



	public static void main(String[] args) {
		SpringApplication.run(KycMicroserviceCqrsApplication.class, args);
		System.out.println("hi");
	}




}