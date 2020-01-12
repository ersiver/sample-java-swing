package com.weatherchecker.applicationlogic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weatherchecker.model.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenWeatherReader {
    private String API_KEY = "bxxxxxxxxxxxxxxxxxxxxxxxx"; //replace with the api key
    private String urlString;

    public void searchWeather(Weather weather, String cityName) {
        this.urlString = "https://openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + API_KEY;
        processData(weather);
    }

    private void processData(Weather weather) {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null)
                result.append(line);

            reader.close();

            Map<String, Object> resultMap = jsonToMap(result.toString());
            accessWeatherMap(resultMap, weather); //1
            accessMainMap(resultMap, weather);    //2
            accessWindMap(resultMap, weather);    //3
            weather.setLocation(resultMap.get("name").toString());

        } catch (IOException e) {
            invalidInputResult(weather); //4
        }
    }

    //1.
    private void accessWeatherMap(Map<String, Object> resultMap, Weather weather) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> weatherList = (ArrayList<Map<String, Object>>) resultMap.get("weather");
        String description = "";
        String iconId = "";

        for (Map<String, Object> element : weatherList) {
            description = element.get("description").toString();
            iconId = element.get("icon").toString();
        }

        weather.setDescription(description != null ?
                description.substring(0, 1).toUpperCase().concat(description.substring(1)) : "");

        weather.setIcon(iconId != null ? "resource/"+iconId+".png" : "resource/icon.png");
    }

    //2.
    private void accessMainMap(Map<String, Object> resultMap, Weather weather) {
        Map<String, Object> mainMap = jsonToMap(resultMap.get("main").toString());
        Double temp = (Double) mainMap.get("temp");
        Double tempMin = (Double) mainMap.get("temp_min");
        Double tempMax = (Double) mainMap.get("temp_max");
        Double pressure = (Double) mainMap.get("pressure");
        Double humidity = (Double) mainMap.get("humidity");

        weather.setTemp(temp != null ? String.format("%.1f°C", temp) : "");
        weather.setTempMin(tempMin != null ? String.format("%.1f°C", tempMin) : "");
        weather.setTempMax(tempMax != null ? String.format("%.1f°C", tempMax) : "");
        weather.setPressure(pressure != null ? String.format("%.1f hPa", pressure) : "");
        weather.setHumidity(humidity != null ? humidity + "%" : "");
    }

    //3.
    private void accessWindMap(Map<String, Object> resultMap, Weather weather) {
        Map<String, Object> windMap = jsonToMap(resultMap.get("wind").toString());
        Double windSpeed = (Double) windMap.get("speed");
        weather.setWind(windSpeed != null ? String.format("%.1f m/s", windSpeed) : "");
    }

    //4.
    private void invalidInputResult(Weather weather) {
        weather.setLocation("Not Found");
        weather.setTemp("");
        weather.setTempMin("");
        weather.setTempMax("");
        weather.setPressure("");
        weather.setHumidity("");
        weather.setWind("");
        weather.setDescription("");
    }

    private Map<String, Object> jsonToMap(String str) {
        return new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );

    }
}
