package bgroup.stocktradingsystem.stsserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StsServerApplication {

//	private static final Logger logger = LoggerFactory.getLogger(StsServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StsServerApplication.class, args);
	}

}
