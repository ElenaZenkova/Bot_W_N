import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;


public class Bot  extends TelegramLongPollingBot {

    public static void main(String[] args){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }
    private static final String TOKEN = "1559476764:AAFtJi_xIIfpvtoBXFr6-vfDfqep59j_L90";
    private static final String USERNAME = "hz_2021_bot";

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

    String message = update.getMessage().getText();
    sendMsg(update.getMessage().getChatId().toString(), message);

        if (message.equals("/start")) {
            sendMsg(message, "Привет, начнём! Я бот и могу рассказать тебе новости или погоду!");
        }

        if (message.equals("/help")){
            sendMsg(message,"Узнать что я могу");
        }
        if (message.equals("/news")){
            sendMsg(message,"Рассказать последние новости");
        }
        if (message.equals("/weather")){
            sendMsg(message,"Рассказать погоду");
        }

    }

    public void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {

            sendMessage(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

}



