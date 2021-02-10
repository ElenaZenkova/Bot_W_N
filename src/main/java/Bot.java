import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Bot  extends TelegramLongPollingBot {

    private static final String TOKEN = "1559476764:AAFtJi_xIIfpvtoBXFr6-vfDfqep59j_L90";
    private static final String USERNAME = "hz_2021_bot";


    private static final String APIKEYWEATHER = "&appid=4d60cb579bb9d3088a8aa14cd0c51a31";
    private static final String APIWEATHER = "api.openweathermap.org/data/2.5/weather?q=";

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
           switch (message.getText()) {
               case "/start":
                   sendMsg(message, "Привет, начнём! Я бот и могу рассказать тебе новости /news или погоду /weather!");
                   break;
               case "/help":
                   sendMsg(message, "Узнать что я могу: /news - расскажу новости, /weather - расскажу о погоде");
                   break;
               case "/news":
                   sendMsg(message, "Прочитать последние новости можно тут: https://ria.ru/");
                   break;
               case "/weather":
                   sendMsg(message, "Хочешь узнать погоду? Тогда напиши на английском языке название города");
                   break;
               default:
           }
                // получить ответ от пользователя и записать его в city name
                // подставить значения в строку: api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

            Message cityName = update.getMessage();
            String textCity = cityName.getText();
            if (cityName != null && cityName.hasText()) {
                sendMsg(cityName, APIWEATHER + cityName + APIKEYWEATHER);
            }

            String urlString = APIWEATHER + cityName + APIKEYWEATHER;

            URL urlObject = null;
            try {
                urlObject = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //
            try {
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
        // switch case default
    }

}


