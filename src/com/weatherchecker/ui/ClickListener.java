package com.weatherchecker.ui;

import com.weatherchecker.applicationlogic.OpenWeatherReader;
import com.weatherchecker.model.Weather;
import com.weatherchecker.tool.DateProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener implements ActionListener {
    private OpenWeatherReader openWeatherReader;
    private Weather weather;
    private JTextField cityTextField;
    private JLabel centerCityLabel, centerDayLabel, centerDateLabel, centerTempLabel, centerDscLabel;
    private JLabel eastMinLabel, eastMaxLabel, eastPressureLabel, eastHumLabel, eastWindLabel;
    private JLabel westIconLabel;

    public ClickListener(OpenWeatherReader openWeatherReader, Weather weather, JTextField cityTextField,
                         JLabel centerCityLabel, JLabel centerDayLabel, JLabel centerDscLabel, JLabel centerDateLabel,
                         JLabel centerTempLabel, JLabel eastMinLabel, JLabel eastMaxLabel,
                         JLabel eastPressureLabel, JLabel eastHumLabel, JLabel eastWindLabel, JLabel westIconLabel) {

        this.openWeatherReader = openWeatherReader;
        this.weather = weather;
        this.cityTextField = cityTextField;
        this.centerCityLabel = centerCityLabel;
        this.centerDayLabel = centerDayLabel;
        this.centerDscLabel = centerDscLabel;
        this.centerDateLabel = centerDateLabel;
        this.centerTempLabel = centerTempLabel;
        this.eastMinLabel = eastMinLabel;
        this.eastMaxLabel = eastMaxLabel;
        this.eastPressureLabel = eastPressureLabel;
        this.eastHumLabel = eastHumLabel;
        this.eastWindLabel = eastWindLabel;
        this.westIconLabel = westIconLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cityName = cityTextField.getText();

        if (cityName != null && !cityName.isEmpty()) {
            openWeatherReader.searchWeather(weather, cityName);
            cityTextField.setText("");
            displayResults();
        } else
            cityTextField.setText("Enter city");
    }

    private void displayResults() {
        centerCityLabel.setText(weather.getLocation());
        centerDscLabel.setText(weather.getDescription());
        centerTempLabel.setText(weather.getTemp());
        eastMinLabel.setText(weather.getTempMin());
        eastMaxLabel.setText(weather.getTempMax());
        eastPressureLabel.setText(weather.getPressure());
        eastHumLabel.setText(weather.getHumidity());
        eastWindLabel.setText(weather.getWind());
        westIconLabel.setIcon(new ImageIcon(weather.getIcon()));

        centerDayLabel.setText(DateProvider.getDayOfWeek());
        centerDateLabel.setText(DateProvider.getDate());
    }
}
