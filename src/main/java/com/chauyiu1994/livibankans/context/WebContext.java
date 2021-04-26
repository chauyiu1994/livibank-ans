package com.chauyiu1994.livibankans.context;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WebContext {

    @Value("${cors.allowOrigin}")
    private String allowOrigin;
}
