package com.example.plantmonitorapp;

public class Plant {

    private String sensorAtSign;
    private String name;
    private String temp;
    private String humid;
    private String light;
    private String soil;

    public Plant(){}

    /**
     *
     * @param sensorAtSign AtSign that the ESP32 is configured with
     * @param name   Plant's name
     * @param temp   Plant's temperature
     * @param humid  Plant's air humidity
     * @param light  Plant's light exposure
     * @param soil   Plant's soil moisture
     */
    public Plant(String sensorAtSign, String name, String temp, String humid, String light, String soil) {
        this.sensorAtSign = sensorAtSign;
        this.name = name;
        this.temp = String.valueOf(temp);
        this.humid = String.valueOf(humid);
        this.light = String.valueOf(light);
        this.soil = String.valueOf(soil);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumid() {
        return humid;
    }
    public void setHumid(String humid) {
        this.humid = humid;
    }

    public void setLight(String light) {
        this.light = light;
    }
    public String getLight() {
        return light;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }
    public String getSoil() {
        return soil;
    }

    public void setAtSign(String sensorAtSign) {
        this.sensorAtSign = sensorAtSign;
    }
    public String getAtSign() {
        return sensorAtSign;
    }

    public String[] getRealTimeRanges() {
        String tempRange;
        if (Double.parseDouble(temp) > 85) { tempRange = "high"; }
        else if (Double.parseDouble(temp) < 50) { tempRange = "low"; }
        else { tempRange = "moderate"; }

        String humidRange;
        if (Double.parseDouble(humid) > 85) { humidRange = "high"; }
        else if (Double.parseDouble(humid) < 30) { humidRange = "low"; }
        else { humidRange = "moderate"; }

        String lightRange;
        if (Double.parseDouble(light) > 4000) { lightRange = "high"; }
        else if (Double.parseDouble(light) < 3000) { lightRange = "low"; }
        else { lightRange = "moderate"; }

        String soilRange;
        if (Double.parseDouble(soil) > 700) { soilRange = "high"; }
        else if (Double.parseDouble(soil) < 300) { soilRange = "low"; }
        else { soilRange = "moderate"; }

        return new String[]{tempRange, humidRange, lightRange, soilRange};
    }


}
