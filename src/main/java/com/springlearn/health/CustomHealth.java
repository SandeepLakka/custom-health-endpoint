package com.springlearn.health;

import lombok.Data;
import org.springframework.boot.actuate.health.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Data
public class CustomHealth {
    private String status;
    Map<String, Map<String, String>> components = new HashMap<String, Map<String, String>>() {{
        put("name", new HashMap<String, String>() {{
            put("firstName", "Sandeep");
            put("lastName", "Lakka");
        }});
        put("work", new HashMap<String, String>() {{
            put("type", "FullTime");
            put("isDev", "true");
        }});
    }};

    public String getStatus() {
        return ((new Random().nextBoolean()) ? Status.UP : Status.DOWN).toString();
    }
}