package edu.iu.lvanjelg.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class HeatIndexDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float heatIndex;
    private Subject heatIndexDisplay;
    public HeatIndexDisplay(Subject heatIndexDisplay){
        this.heatIndexDisplay = heatIndexDisplay;
    }
    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Heat index: %s</label><br />", heatIndex);
        html += "</section>";
        html += "</div>";
        return html;
    }
    @Override
    public void update(float temperature, float humidity, float heatIndex) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.heatIndex = computeHeatIndex(temperature,humidity);
    }
    private float computeHeatIndex(float t, float rh) {
        float index = (float)((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh) +
                (0.00941695 * (t * t)) + (0.00728898 * (rh * rh)) +
                (0.000345372 * (t * t * rh)) - (0.000814971 * (t * rh * rh)) +
                (0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t)) + (0.0000291583 *
                (rh * rh * rh)) + (0.00000142721 * (t * t * t * rh)) +
                (0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
                0.000000000843296 * (t * t * rh * rh * rh)) -
                (0.0000000000481975 * (t * t * t * rh * rh * rh)));
        return index;
    }
    @Override
    public String name() {
        return "Heat Index Display";
    }

    @Override
    public String id() {
        return "heat-index";
    }
    public void subscribe() {
        heatIndexDisplay.registerObserver(this);
    }

    public void unsubscribe() {
        heatIndexDisplay.removeObserver(this);
    }
}
