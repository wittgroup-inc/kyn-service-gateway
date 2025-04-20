package com.wittgroup.kyn.gateway.web;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class OAuth2Controller {
    @GetMapping("/authorized")
    public ResponseEntity<?> handleOAuth2Callback2(@RequestParam String code, @RequestParam String state) {
        try {
            System.out.println("code: " + code);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "authorization_code");
            map.add("code", code);
            map.add("redirect_uri", "http://127.0.0.1:8080/authorized");


            String clientId = "kyn-cloud-gateway";
            String clientSecret = "1232kyn123"; // Plain text secret
            String credentials = clientId + ":" + clientSecret;
            String base64Creds = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Basic " + base64Creds);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            // Ensure the URL is correct and matches your OAuth2 provider's configuration
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8085/oauth2/token", request, String.class);

            System.out.println(response);

            System.out.println("Token received successfully: " + response.getBody());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions such as network errors or invalid responses
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing OAuth2 callback: " + e.getMessage());
        }
    }
}
