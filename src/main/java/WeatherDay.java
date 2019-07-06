import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherDay{
    public final static int AMOUNT_DATA_FOUR = 4;
    public final static int AMOUNT_DATA_TWO = 2;
    private String date;
    private String urlSourse = "https://world-weather.ru/pogoda/russia/surgut_1/3days/";
    private Weather night;
    private Weather morning;
    private Weather day;
    private Weather evening;

    public Weather getNight() {
        return night;
    }

    public Weather getMorning() {
        return morning;
    }

    public Weather getDay() {
        return day;
    }

    public Weather getEvening() {
        return evening;
    }

    public String getDate() {
        return date;
    }
    

    private static Document getPage(int index) throws IOException {
        String url = "https://world-weather.ru/pogoda/russia/surgut_1/3days/";
        if (index > 2) {
            url = "https://world-weather.ru/pogoda/russia/surgut_1/10days/";
        }
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }
    //Какой по счёту день из трёх
    public WeatherDay(int index) throws IOException{
        //Если запрашивается день больше 3, то заполняем, как третий
        if (index > 9) {
            index = 9;
        }
        //Если меньше 0, то заполняется, как первый
        if (index < 0) {
            index = 0;
        }
        Document page = getPage(index);
        //Заполяем данные с даты
        Element days = page.select("div[class=weather-short]").get(index);
        Pattern patternDate = Pattern.compile("\\d{2}\\ \\D.+?\\ ");
        Matcher matcher = patternDate.matcher(days.text());
        if (matcher.find()){
            date = matcher.group();
        }
        else {
            date = "not found";
        }
        //Заполняем все остальные поля
        night = loadInformation(0+(index*4), page);
        morning = loadInformation(1+(index*4), page);
        day = loadInformation(2+(index*4), page);
        evening = loadInformation(3+(index*4), page);
    }
    //Загрузка для конкретной части дня одного из трёх дней
    private Weather loadInformation(int index, Document page) {
        Weather weather = new Weather();
        Elements timeDays = page.select("td[class=weather-day]");
        Elements temperatures = page.select("td[class=weather-temperature]");
        Elements feelingTempt = page.select("td[class=weather-feeling]");
        Elements pressures = page.select("td[class=weather-pressure]");
        Elements winds = page.select("td[class=weather-wind]");
        Elements humitidies = page.select("td[class=weather-humidity]");
        
        weather.setTimeDay(timeDays.get(index).text());
        weather.setTemperature(temperatures.get(index).text());
        weather.setFeelingTemperature(feelingTempt.get(index).text());
        weather.setPressure(pressures.get(index).text());
        weather.setWinde(winds.get(index).text());
        weather.setHumidity(humitidies.get(index).text());
        return weather;
    }
    public String getAllInformation(){
        String info = date;
        info += "\n" + night.getAllInfo() + "\n" + morning.getAllInfo() + "\n" + day.getAllInfo() + "\n" + evening.getAllInfo() + "\n" ;
        return info;
    }
    private void reload(){
        
    }
}
