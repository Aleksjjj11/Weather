public class Weather {
    private String timeDay;
    private String temperature;
    private String feelingTemperature;
    private String pressure;
    private String winde;
    private String humidity;

    public void setTimeDay(String timeDay) {
        this.timeDay = timeDay;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setFeelingTemperature(String feelingTemperature) {
        this.feelingTemperature = feelingTemperature;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setWinde(String winde) {
        this.winde = winde;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTimeDay() {
        return timeDay;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getFeelingTemperature() {
        return feelingTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWinde() {
        return winde;
    }

    public String getHumidity() {
        return humidity;
    }
    public String getAllInfo(){
        String info = "";
        info += timeDay + " " + temperature + " " + feelingTemperature + " " + pressure +
                " " + winde + " " + humidity;
        return info;
    }
}