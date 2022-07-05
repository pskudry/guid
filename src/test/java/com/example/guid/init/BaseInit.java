package com.example.guid.init;

import com.example.guid.dao.AtributeEntitiesRepository;
import com.example.guid.dao.AtributeValuesRepository;
import com.example.guid.dao.EntitiesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public  class BaseInit {


        @Autowired
        protected TestRestTemplate testRestTemplate ;

        private static int POSTGRES_PORT = 5432;

        private static Map<String,String> postgresEnvMap = new HashMap<>();

        static final  DockerComposeContainer environment;

        static {
            environment =
                    new DockerComposeContainer(new File("src/test/resources/docker-compose.yaml"))
                            .withExposedService("postgres", POSTGRES_PORT, Wait.forListeningPort())
                            .withLocalCompose(true)
            ;

            environment.start();

        }


        @DynamicPropertySource
        public static void properties(DynamicPropertyRegistry registry) {

            String postgresUrl = environment.getServiceHost("postgres", POSTGRES_PORT)
                    + ":" +
                    environment.getServicePort("postgres", POSTGRES_PORT);

            registry.add("spring.datasource.url", () -> "jdbc:postgresql://"+postgresUrl+"/guides");
            registry.add("spring.datasource.username", () ->"postgres");
            registry.add("spring.datasource.password", () ->"postgres");

        }

}
