import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Weather {

    private static final String APIKEYWEATHER = "&appid=4d60cb579bb9d3088a8aa14cd0c51a31";
    private static final String APIWEATHER = "api.openweathermap.org/data/2.5/weather?q=";


    String cityName;
    String urlString = APIWEATHER + cityName + APIKEYWEATHER;

    URL urlObject = new URL(urlString);

    //
    HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
    public Weather() throws IOException {
    }



}
