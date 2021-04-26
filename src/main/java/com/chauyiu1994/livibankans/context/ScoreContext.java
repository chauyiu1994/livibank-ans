package com.chauyiu1994.livibankans.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "rules")
public class ScoreContext {

    private TreeMap<Integer, Integer> numberOfEmployees;
    private Map<String, Integer> companyType;
    private TreeMap<Integer, Integer> numberOfYearsOperated;
}
