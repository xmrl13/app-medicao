package md.medicao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"md.medicao", "controller", "dto", "enums", "security", "service", "config"})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "model")
public class MedicaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicaoApplication.class, args);
    }
}
