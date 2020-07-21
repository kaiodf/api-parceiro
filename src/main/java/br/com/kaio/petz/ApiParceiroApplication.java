package br.com.kaio.petz;

import br.com.kaio.petz.config.RestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.kaio.petz")
@Import({ RestConfiguration.class })
@EnableSwagger2
@EnableAutoConfiguration
public class ApiParceiroApplication{

	public static void main(String[] args) {
		SpringApplication.run(ApiParceiroApplication.class, args);
	}
}
