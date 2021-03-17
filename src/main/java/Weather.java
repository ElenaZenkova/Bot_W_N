import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import Config.Config;
import org.json.JSONArray;
import org.json.JSONObject;

public class Weather {

    public static String getWeather (String cityName, Items items) throws IOException {
        URL url = new URL(Config.APIWEATHER + cityName + Config.APIKEYWEATHER);
        Scanner scan = new Scanner((InputStream) url.getContent());
        String result = "";

        while (scan.hasNext()) {
            result += scan.nextLine();
            JSONObject object = new JSONObject(result);
            items.setName(object.getString("name"));

            JSONObject main = object.getJSONObject("main");
            items.setTemp_max(main.getDouble("temp_max"));
            items.setTemp_min(main.getDouble("temp_min"));

            JSONObject wind = object.getJSONObject("wind");
            items.setSpeed(wind.getDouble("speed"));


            JSONArray getArray = object.getJSONArray("weather");
            for (int i = 0; i < getArray.length(); i++) {
                JSONObject obj = getArray.getJSONObject(i);
                items.setMain(obj.getString("main"));
                items.setIcon(obj.getString("icon"));
            }
        }
            return "Город: " + items.getName() + "\n" +
                    "Температура днем: " + items.getTemp_max().intValue() + " C" + "\n" +
                    "Температура ночью: " + items.getTemp_min().intValue() + " C" + "\n" +
                    "Скорость ветра: " + items.getSpeed() + " м/с" + "\n" +
                    "Осадки: " + items.getMain() + "\n" +
                    "http://openweathermap.org/img/w/" + items.getIcon() + ".png";

        }
    }
