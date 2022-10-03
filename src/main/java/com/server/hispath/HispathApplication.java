package com.server.hispath;

import com.server.hispath.notice.domain.Notice;
import com.server.hispath.notice.domain.repository.NoticeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HispathApplication {

	public static void main(String[] args) {
		SpringApplication.run(HispathApplication.class, args);
	}

}
