package bot;



import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import okhttp3.OkHttpClient;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        final TelegramBot bot = new TelegramBot.Builder("521960020:AAHGQKGlW_QbuedUGikDDcfagOMqbMYguI4").okHttpClient(new OkHttpClient()).build();
        String url = "http://cryptic-journey-87140.herokuapp.com/";
        SetWebhook request = new SetWebhook();
        request.url(url);
        bot.execute(request, new Callback<SetWebhook, BaseResponse>() {
            public void onResponse(SetWebhook request, BaseResponse response) {

            }
            public void onFailure(SetWebhook request, IOException e) {

            }
        });
        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
        final GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();
        bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {
            public void onResponse(GetUpdates request, GetUpdatesResponse response) {
                List<Update> updates = updatesResponse.updates();
                for(final Update item: updates){
                    try{
                        SendMessage sm = new SendMessage(item.message().chat().id(), item.message().text());
                        bot.execute(sm, new Callback<SendMessage, SendResponse>() {
                            public void onResponse(SendMessage sendMessage, SendResponse sendResponse) {

                            }

                            public void onFailure(SendMessage sendMessage, IOException e) {

                            }
                        });
                    }catch (Exception e){

                    }
                }
            }

            public void onFailure(GetUpdates request, IOException e) {

            }
        });


    }
}
