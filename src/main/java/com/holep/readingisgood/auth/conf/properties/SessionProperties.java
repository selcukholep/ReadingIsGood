package com.holep.readingisgood.auth.conf.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("session")
@Getter
@Setter
public class SessionProperties {
    private List<String> excludedPaths;
}
