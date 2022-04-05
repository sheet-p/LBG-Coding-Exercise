package com.lbg.springbootDemo.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestSpringBootController {
    Logger logger = LoggerFactory.getLogger(RestSpringBootController.class);

    @GetMapping(value = "/atmInfo")
    public ResponseEntity<?> getATM(@RequestParam(name = "url")String link, @RequestParam(name = "identification")String identifier) {
        logger.trace("ATM method accessed");
         
        //String url = "https://api.lloydsbank.com/open-banking/v2.2/atms";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(link, String.class);

        JSONObject result = new JSONObject(response);
        JSONArray data = result.getJSONArray("data");
        JSONArray brands = (JSONArray) data.getJSONObject(0).getJSONArray("Brand");        
        JSONObject jobj = brands.getJSONObject(0);
        JSONArray atms = jobj.getJSONArray("ATM");

        //to store final list of matching ATMs
        StringBuilder matchATMs =new StringBuilder();

        //loop through arrays to find the matching identification
        for(int loop=0; loop<atms.length(); loop++) {
            JSONObject each = atms.getJSONObject(loop);

            String ident = each.getString("Identification");
            if(ident.equals(identifier)) {
                matchATMs.append(each.toString());
            }
        }

        return new ResponseEntity<>(matchATMs.toString(), HttpStatus.OK);
    }
}
