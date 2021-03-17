import Config.Config;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return Config.USERNAME;
    }

    @Override
    public String getBotToken() {
        return Config.TOKEN;
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Items items = new Items();
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
                    sendMsg(message, "Хочешь узнать погоду? Тогда напиши название города");

                    break;
                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), items));
                    } catch (IOException e) {
                        sendMsg(message, "Такой город не найден");
                    }
            }
        }
    }
}










