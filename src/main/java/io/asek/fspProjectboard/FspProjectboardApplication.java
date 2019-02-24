package io.asek.fspProjectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("io.asek.fspProjectboard")
public class FspProjectboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FspProjectboardApplication.class, args);
	}

}

