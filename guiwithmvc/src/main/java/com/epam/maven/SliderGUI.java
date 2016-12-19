package com.epam.maven;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Alexander_Gaptullin on 12/7/2016.
 */
public class SliderGUI implements Observer {

    private Scrollbar tempControl = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, -50, 160);
    private TemperatureModel model = null;
    private Frame sliderFrame = new Frame("Celsius");

    public SliderGUI(TemperatureModel m, int h, int v) {
        m.addObserver(this); //Observe the temperature model
        model = m;
        tempControl.setBackground(new Color(230,230,230));
        tempControl.setForeground(new Color(0, 0, 0));
        sliderFrame.add(tempControl);
        tempControl.addAdjustmentListener(new SlideListener());
        sliderFrame.setSize(250, 60);
        sliderFrame.setLocation(h, v);
        sliderFrame.setVisible(true);
        sliderFrame.addWindowListener(new TemperatureGUI.CloseListener());
    }

    public void update(Observable t, Object o) {
        double temp = ((TemperatureModel) t).getC();
        tempControl.setValue((int) temp); // Move the slider thumb
    }

    class SlideListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            model.setC(tempControl.getValue());
        }
    }

}
