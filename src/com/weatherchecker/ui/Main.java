package com.weatherchecker.ui;

import com.weatherchecker.model.Weather;
import com.weatherchecker.applicationlogic.OpenWeatherReader;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface ui = new UserInterface(new OpenWeatherReader(), new Weather());
            ui.run();

        });
    }
}
