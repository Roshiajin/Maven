package com.epam.maven;

/**
 * Created by Alexander_Gaptullin on 12/7/2016.
 */

class TemperatureGauge {
    private int max, min, current;

    public TemperatureGauge(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void set(int level) {
        current = level;
    }

    public int get() {
        return current;
    }

    public int getMax() {
        return this.max;
    }

    public int getMin() {
        return this.min;
    }


}

