package com.weatherchecker.ui;

import com.weatherchecker.model.Weather;
import com.weatherchecker.applicationlogic.OpenWeatherReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class UserInterface extends JFrame {
    private final String ICON_PATH = "resource/icon.png";
    private final String MINTEMP_PATH = "resource/min.png";
    private final String MAXTEMP_PATH = "resource/max.png";
    private final String HUMIDITY_PATH = "resource/humidity.png";
    private final String PRESSURE_PATH = "resource/pressure.png";
    private final String WIND_PATH = "resource/wind.png";

    private OpenWeatherReader openWeatherReader;
    private Weather weather;

    private JTextField northCityTextField;
    private JPanel mainPanel, northPanel, westPanel, centerPanel, eastPanel;
    private JButton northSearchButton;
    private JLabel centerCityLabel, centerDayLabel, centerDateLabel, centerTempLabel, centerDscLabel;
    private JLabel westIconPanel;
    private JLabel eastMinLabel, eastMaxLabel, eastPressureLabel, eastHumiLabel, eastWindLabel;

    public UserInterface(OpenWeatherReader openWeatherReader, Weather weather) {
        this.openWeatherReader = openWeatherReader;
        this.weather = weather;
    }

    public void run() {
        createComponents();
        setTitle("Weather Checker");
        setSize(1000, 550);
        setVisible(true);
    }

    public void createComponents() {
        mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new BorderLayout());

        setupWestPanel();  //1
        setupCenterPanel();//2
        setupEastPanel();  //3
        setupNorthPanel(); //4

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.PAGE_START);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);

        add(mainPanel);
    }

    //1.
    private void setupWestPanel() {
        westPanel = new BackgroundPanel();
        westPanel.setLayout(new GridLayout(1, 1));
        westPanel.setBorder(new EmptyBorder(0, 30, 80, 0));
        westIconPanel = new JLabel("",new ImageIcon(ICON_PATH), JLabel.CENTER);
        westPanel.add(westIconPanel);
    }

    //2.
    private void setupCenterPanel() {
        centerPanel = new BackgroundPanel();
        centerPanel.setLayout(new GridLayout(6, 1));
        centerPanel.setBorder(new EmptyBorder(0, 25, 0, 0));

        centerCityLabel = new JLabel("Weather Checker", JLabel.CENTER);
        centerTempLabel = new JLabel("", JLabel.CENTER);
        centerDayLabel = new JLabel("", JLabel.CENTER);
        centerDateLabel = new JLabel("", JLabel.CENTER);
        centerDscLabel = new JLabel("", JLabel.CENTER);

        adjustLabelFont(centerCityLabel, 50);
        adjustLabelFont(centerTempLabel, 50);
        adjustLabelFont(centerDayLabel, 30);
        adjustLabelFont(centerDateLabel, 30);
        adjustLabelFont(centerDscLabel, 25);

        centerPanel.add(centerCityLabel);
        centerPanel.add(centerTempLabel);
        centerPanel.add(centerDayLabel);
        centerPanel.add(centerDateLabel);
        centerPanel.add(centerDscLabel);
    }

    //3.
    private void setupEastPanel() {
        eastPanel = new BackgroundPanel();
        eastPanel.setLayout(new GridLayout(6, 1));
        eastPanel.setBorder(new EmptyBorder(0, 100, 0, 30));

        eastMinLabel = new JLabel("", new ImageIcon(MINTEMP_PATH), JLabel.LEFT);
        eastMaxLabel = new JLabel("", new ImageIcon(MAXTEMP_PATH), JLabel.LEFT);
        eastHumiLabel = new JLabel("", new ImageIcon(HUMIDITY_PATH), JLabel.LEFT);
        eastPressureLabel = new JLabel("", new ImageIcon(PRESSURE_PATH), JLabel.LEFT);
        eastWindLabel = new JLabel("", new ImageIcon(WIND_PATH), JLabel.LEFT);

        ArrayList<JLabel> labelList = new ArrayList<>();
        labelList.add(eastMinLabel);
        labelList.add(eastMaxLabel);
        labelList.add(eastHumiLabel);
        labelList.add(eastPressureLabel);
        labelList.add(eastWindLabel);

        for (JLabel eastLabel : labelList) {
            adjustLabelFont(eastLabel, 24);
            eastPanel.add(eastLabel);
        }
    }

    //4.
    private void setupNorthPanel() {
        northPanel = new BackgroundPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setBorder(new EmptyBorder(40, 0, 40, 0));

        createSearchTextField(); //4a
        createSearchButton(); //4b

        northPanel.add(northCityTextField);
        northPanel.add(northSearchButton);
    }

    //4a.
    private void createSearchTextField() {
        northCityTextField = new JTextField("Enter city", 50);
        northCityTextField.setPreferredSize(new Dimension(100, 40));

        northCityTextField.addActionListener(new ClickListener(openWeatherReader, weather,
                northCityTextField, centerCityLabel, centerDayLabel, centerDscLabel, centerDateLabel, centerTempLabel,
                eastMinLabel, eastMaxLabel, eastPressureLabel, eastHumiLabel, eastWindLabel, westIconPanel));
    }

    //4b.
    private void createSearchButton() {
        northSearchButton = new JButton();
        northSearchButton.setText("GO!");
        northSearchButton.setPreferredSize(new Dimension(50, 40));

        northSearchButton.addActionListener(new ClickListener(openWeatherReader, weather,
                northCityTextField, centerCityLabel, centerDayLabel, centerDscLabel, centerDateLabel, centerTempLabel,
                eastMinLabel, eastMaxLabel, eastPressureLabel, eastHumiLabel, eastWindLabel, westIconPanel));
    }

    private void adjustLabelFont(JLabel label, int fontSize) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize));
    }
}


