package edu.iu.lvanjelg.weathermonitoring.controllers;

import edu.iu.lvanjelg.weathermonitoring.model.CurrentConditionDisplay;
import edu.iu.lvanjelg.weathermonitoring.model.HeatIndexDisplay;
import edu.iu.lvanjelg.weathermonitoring.model.WeatherStats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/displays")
public class DisplayController {
    private CurrentConditionDisplay currentConditionDisplay;
    private WeatherStats weatherStats;
    private HeatIndexDisplay heatIndexDisplay;

    public DisplayController(CurrentConditionDisplay currentConditionDisplay, WeatherStats weatherStats, HeatIndexDisplay heatIndexDisplay) {
        this.currentConditionDisplay = currentConditionDisplay;
        this.weatherStats = weatherStats;
        this.heatIndexDisplay = heatIndexDisplay;
    }

    @GetMapping
    public ResponseEntity index() {
        String html =
                String.format("<h1>Available screens:</h1>");
        html += "<ul>";
        html += "<li>";
        html += String.format("<a href=/displays/%s>%s</a>", currentConditionDisplay.id(), currentConditionDisplay.name());
        html += "</li>";
        html += "<li>";
        html += String.format("<a href=/displays/%s>%s</a>", weatherStats.id(), weatherStats.name());
        html += "</li>";
        html += "<li>";
        html += String.format("<a href=/displays/%s>%s</a>", heatIndexDisplay.id(), heatIndexDisplay.name());
        html += "</li>";
        html += "</ul>";
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(html);
    }


    @GetMapping("/{id}")
    public ResponseEntity display(@PathVariable String id) {
        String html = "";
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())){
            html = currentConditionDisplay.display();
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(weatherStats.id())){
            html = weatherStats.display();
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(heatIndexDisplay.id())){
            html = heatIndexDisplay.display();
            status = HttpStatus.FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }

    @GetMapping("/{id}/subscribe")
    public ResponseEntity subscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(weatherStats.id())) {
            weatherStats.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(heatIndexDisplay.id())){
            heatIndexDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        } else {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }

    @GetMapping("/{id}/unsubscribe")
    public ResponseEntity unsubscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        } else if(id.equalsIgnoreCase(weatherStats.id())) {
            weatherStats.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(heatIndexDisplay.id())){
            heatIndexDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        } else {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(status)
                .body(html);
    }
}
