package com.danigu.web;

import com.danigu.web.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author dani
 */
public class InfrastructureTest extends BaseTest {

    @Inject
    private EntityManagerFactory emf;

    @Test
    public void givenConfiguredProperlyWhenBootstrappedThenContextShouldContainPersistence() {
        assertThat(emf).isNotNull();
    }
}
