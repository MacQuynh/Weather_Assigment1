package dk.au.mad21spring.assignment.weatherapplikation.au583669;

import java.io.Serializable;

public class City implements Serializable {
    public String cityName;
    public String countries;
    public String temperature;
    public String humidity;
    public String weatherCondition;
    public Float rating;
    public String userNotes;

    public City(String name, String countries, String temp, String humidity, String wCondition, Float rating, String userNotes) {
        this.cityName = name;
        this.countries = countries;
        this.temperature = temp;
        this.humidity = humidity;
        this.weatherCondition = wCondition;
        this.rating = rating;
        this.userNotes = userNotes;

    }
}
