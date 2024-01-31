package com.asrevo.testissue;

import com.asrevo.testissue.domain.Xtable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
public class TestTestIssueApplication1 {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"));
    }

    @Bean
    public CommandLineRunner runner(EntityManager entityManager) {
        return args -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<Xtable> cq = cb.createQuery(Xtable.class);
            Root<Xtable> rootEntry = cq.from(Xtable.class);
            CriteriaQuery<Xtable> all = cq.select(rootEntry);

            List<Xtable> resultList = entityManager.createQuery(all).setFirstResult(0).setMaxResults(10).getResultList();
            System.out.println(resultList);
        };
    }

    public static void main(String[] args) {
        SpringApplication.from(TestIssueApplication::main).with(TestTestIssueApplication1.class).run(args);
    }

}
