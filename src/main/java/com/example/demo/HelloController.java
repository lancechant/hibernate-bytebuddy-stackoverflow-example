package com.example.demo;

import com.example.demo.entity.jpa.TestEntity;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 *
 */
@Path("/hello")
@Singleton
@Transactional
@Slf4j
public class HelloController {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @GET
    public String sayHello() {
        log.info("yup");
//        em.persist(new TestEntity());
        return "Hello World";
    }
}
