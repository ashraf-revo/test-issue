package com.asrevo.testissue;

import com.asrevo.testissue.domain.Xtable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Properties;

public class TestTestIssueApplication2 {
   static PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"));
    }

    public static void main(String[] args) {
        PostgreSQLContainer<?> postgreSQLContainer = postgresContainer();
        postgreSQLContainer.start();
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", postgreSQLContainer.getJdbcUrl());

        //You can use any database you want, I had it configured for Postgres
        prop.setProperty("hibernate.dialect", "org.hibernate.community.dialect.PostgreSQLLegacyDialect");

        prop.setProperty("hibernate.connection.username", postgreSQLContainer.getUsername());
        prop.setProperty("hibernate.connection.password", postgreSQLContainer.getPassword());
        prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        prop.setProperty("hibernate.show_sql", "true"); //If you wish to see the generated sql query


        SessionFactory sessionFactory = new Configuration()
                .addProperties(prop)
                .addAnnotatedClass(Xtable.class)
                .buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Xtable> cq = cb.createQuery(Xtable.class);
        Root<Xtable> rootEntry = cq.from(Xtable.class);
        CriteriaQuery<Xtable> all = cq.select(rootEntry);

        List<Xtable> resultList = entityManager.createQuery(all).setFirstResult(0).setMaxResults(10).getResultList();
        System.out.println(resultList);
    }

}
