package com.rafaellbarros.java.back.end.service;

import com.rafaellbarros.java.back.end.exception.UserNotFoundException;
import com.rafaellbarros.java.back.end.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    public UserDTO getUserByCpf(String cpf) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/user/cpf/" + cpf;
            ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
