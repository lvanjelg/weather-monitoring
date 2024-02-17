package edu.iu.lvanjelg.weathermonitoring.model;

import org.springframework.stereotype.Component;
@Component
public class WeatherStats implements Observer, DisplayElement{
    private float avgTemp;
    private float minTemp;
    private float maxTemp;
    private Subject weatherData;
    public WeatherStats(Subject weatherData){
        this.weatherData = weatherData;
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
        html += String.format("<label>Avg. temp: %s</label><br />", avgTemp);
        html += String.format("<label>Min. temp: %s</label><br />", minTemp);
        html += String.format("<label>Max temp: %s</label>", maxTemp);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float avgTemp, float minTemp, float maxTemp) {
        this.avgTemp = avgTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    @Override
    public String name() {
        return "Weather Stats Display";
    }

    @Override
    public String id() {
        return "weather-stats";
    }
    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
