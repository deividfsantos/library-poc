package com.library.rent.poc.client;

import com.library.rent.poc.client.response.User;
import com.library.rent.poc.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private RestTemplate restTemplate;

    @Value("${url.userservice}")
    private String serviceUrl;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUserByCpf(String cpf) {
        try {
            return restTemplate.getForObject(serviceUrl + cpf, User.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
                throw new NotFoundException("User not found, check whether the CPF is correct or sign up.");
            }
            throw e;
        }
    }

}
