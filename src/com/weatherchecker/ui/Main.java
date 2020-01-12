package com.weatherchecker.ui;

import com.weatherchecker.model.Weather;
import com.weatherchecker.applicationlogic.OpenWeatherReader;

public class Main {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface(new OpenWeatherReader(), new Weather());
        ui.run();
    }
}
