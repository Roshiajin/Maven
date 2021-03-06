package com.epam.maven;

public class MVCTempConvert {
    public static void main(String args[]) {
        TemperatureModel temperature = new TemperatureModel();
        new FarenheitGUI(temperature, 100, 100);
        new CelsiusGUI(temperature, 100, 250);
        new GraphGUI(temperature,300, 100);
        new SliderGUI(temperature, 100, 400);
    }
}