package web.tech.project.api.controllers;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import web.tech.project.api.core.model.Card;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @PostMapping("/payForOrder/{id}")
    public String payment(@RequestBody Card card, @PathVariable Long id) {
        String errorUrl = "http://localhost:8080/api/order/" + id + "/payment/error";
        String successUrl = "http://localhost:8080/api/order/" + id + "/payment/success";
        RestTemplate restTemplate = new RestTemplate();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
        restTemplate.setRequestFactory(requestFactory);

        SimpleDateFormat format = new SimpleDateFormat("MM/yy");
        format.setLenient(false);

        Date date;
        try {
            date = format.parse(card.getExpiryDate());
        } catch (ParseException e) {
            return restTemplate.patchForObject(errorUrl, null, String.class);
        }
        Pattern numberPattern = Pattern.compile("^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$");
        Pattern cvcPattern = Pattern.compile("^[0-9]{3}$");

        if (numberPattern.matcher(card.getNumber()).find() && cvcPattern.matcher(card.getCvc()).find() && date.after(new Date())) {
            return restTemplate.patchForObject(successUrl, null, String.class);
        } else {
            return restTemplate.patchForObject(errorUrl, null, String.class);
        }
    }
}
