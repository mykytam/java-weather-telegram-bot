import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }


    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId());

        sendMessage.setReplyToMessageId(message.getMessageId());

        sendMessage.setText(text);
        try {
            setButtons(sendMessage); // putting buttons in message
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }



    public void onUpdateReceived(Update update) { // for messages receiving through LongPoll
        Model model = new Model();
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "/start" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                        break;
                case "hi" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "hello" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "привет" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "погода" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "weather" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "Hi" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "Hello" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "Привет" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "Погода" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "Weather" : sendMsg(message, "Hi! I'm weather bot, just type in the name of the city in English and I'll show you the weather :)");
                    break;
                case "/help" : sendMsg(message, "You should type in the name of the city in English and that's it.");
                    break;
                case "/about" : sendMsg(message, "Bot made by Mykyta Morar. Bot takes weather from OpenWeather.");
                    break;
                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMsg(message, "No city founded!");
                    }
            }
        }
    }


    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(); // new keyboard
        sendMessage.setReplyMarkup(replyKeyboardMarkup); // buttons = message
        replyKeyboardMarkup.setSelective(true); // for whom
        replyKeyboardMarkup.setResizeKeyboard(true);// buttons amount
        replyKeyboardMarkup.setOneTimeKeyboard(false);// show or hide keyboard after message

        List<KeyboardRow> keyboardRowList = new ArrayList<>(); // making buttons
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/about"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getBotUsername() { // return botUserName
        return "WeatherMorarBot";
    }

    public String getBotToken() { // token, when made a bot
        return "KEYHERE";
    }
}
