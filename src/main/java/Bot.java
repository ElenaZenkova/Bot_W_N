import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
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

        if (message !=null && message.hasText()) {
            String text = message.getText();
            if ("/start".equals(text)) {
                sendMsg(message, "Привет, начнём! Я бот и могу рассказать тебе новости /news или погоду /weather!");
            } else if ("/help".equals(text)) {
                sendMsg(message, "Узнать что я могу: /news - расскажу новости, /weather - расскажу о погоде");
            } else if ("/news".equals(text)) {
                sendMsg(message, "Прочитать последние новости можно тут: https://ria.ru/");
            } else if ("/weather".equals(text)) {
                sendMsg(message, "Чтобы посмотреть погоду, зайди сюда: https://www.gismeteo.ru/");
            }
        }
    }


}



