package com.lbg.springbootDemo.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestSpringBootController {
    Logger logger = LoggerFactory.getLogger(RestSpringBootController.class);

    @GetMapping(value = "/atmInfo")
    public ResponseEntity<?> getATM() {
        logger.trace("ATM method accessed");
         
        String url = "https://api.lloydsbank.com/open-banking/v2.2/atms";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject result = new JSONObject(response);
        JSONArray data = result.getJSONArray("data");
        JSONArray brands = (JSONArray) data.getJSONObject(0).getJSONArray("Brand");        
        JSONObject jobj = brands.getJSONObject(0);
        JSONArray atms = jobj.getJSONArray("ATM");

        return new ResponseEntity<>(atms.toString(), HttpStatus.OK);
    }
}
