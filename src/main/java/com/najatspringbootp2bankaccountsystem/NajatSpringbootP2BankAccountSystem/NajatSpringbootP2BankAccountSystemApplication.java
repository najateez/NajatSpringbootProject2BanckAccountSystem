package com.najatspringbootp2bankaccountsystem.NajatSpringbootP2BankAccountSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling //-> for CRON
//@EnableWebSecurity //-->for spring security
//@EnableGlobalMethodSecurity(prePostEnabled=true) //-->for spring security
@SpringBootApplication
public class NajatSpringbootP2BankAccountSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NajatSpringbootP2BankAccountSystemApplication.class, args);
	}

}
