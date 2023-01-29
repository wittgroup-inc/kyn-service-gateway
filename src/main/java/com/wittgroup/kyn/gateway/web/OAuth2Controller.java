package com.wittgroup.kyn.gateway.web;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OAuth2Controller {

    @GetMapping("/authorized")
    public ResponseEntity<?> handleOAuth2Callback2(@RequestParam String code, @RequestParam String state) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "authorization_code");
            map.add("code", code);
            map.add("redirect_uri", "http://127.0.0.1:8080/login/oauth2/code/{registrationId}");
            map.add("client_id", "kyn-cloud-gateway");  // Replace with actual client ID
            map.add("client_secret", "1232kyn123");  // Replace with actual client secret

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            // Ensure the URL is correct and matches your OAuth2 provider's configuration
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8085/oauth2/token", request, String.class);

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            // Handle exceptions such as network errors or invalid responses
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing OAuth2 callback: " + e.getMessage());
        }
    }
}
