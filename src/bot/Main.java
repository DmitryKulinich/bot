package bot;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot.Builder("521960020:AAHGQKGlW_QbuedUGikDDcfagOMqbMYguI4").okHttpClient(new OkHttpClient()).build();
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> list) {
                list.stream().forEach(x -> System.out.println(x.message().text()));
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }
}
