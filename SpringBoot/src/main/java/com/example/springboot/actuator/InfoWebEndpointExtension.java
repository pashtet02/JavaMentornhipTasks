package com.example.springboot.actuator;

import com.example.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
@RequiredArgsConstructor
public class InfoWebEndpointExtension {

    private final InfoEndpoint delegate;
    private final UserService userService;

    @ReadOperation
    public WebEndpointResponse<Map<String, Object>> info() {
        Map<String, Object> info = this.delegate.info();
        Map<String, Object> mutableMap = new HashMap<>(info);
        int userCount = userService.getUsers().size();

        mutableMap.put("Users in app: ", userCount);

        Integer status = getStatus(info);
        return new WebEndpointResponse<>(mutableMap, status);
    }

    private Integer getStatus(Map<String, Object> info) {
        return 325;
    }
}