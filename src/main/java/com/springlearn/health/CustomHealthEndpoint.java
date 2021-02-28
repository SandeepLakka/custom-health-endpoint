package com.springlearn.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
@Endpoint(id = "customHealth")
public class CustomHealthEndpoint {

    private CustomHealth customHealth = new CustomHealth();
    private Health.Builder healthStatus = Health.down();
    private float randomFloat;

    @ReadOperation
    public WebEndpointResponse<CustomHealth> getHealth() {
        healthStatus = Health.down();
        randomFloat = new Random().nextFloat();
        log.info("randomFloat is {}", randomFloat);
        if (randomFloat > 0.5) {
            healthStatus.up();
        }
        Health health = healthStatus.build();
        log.info("health is {}", health);

        //return healthStatus.withDetails(customHealth.getComponents()).build();
        return new WebEndpointResponse<>(customHealth, HttpCodeStatusMapper.DEFAULT.getStatusCode(health.getStatus()));
    }
}
