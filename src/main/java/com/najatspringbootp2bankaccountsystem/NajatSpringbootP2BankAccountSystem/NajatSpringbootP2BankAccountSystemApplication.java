package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling //-> for CRON
@SpringBootApplication
public class NajatSpringbootP2BankAccountSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NajatSpringbootP2BankAccountSystemApplication.class, args);
	}

}
