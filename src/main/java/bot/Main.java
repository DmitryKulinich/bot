package bot;



import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import okhttp3.OkHttpClient;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final TelegramBot bot = new TelegramBot.Builder("521960020:AAHGQKGlW_QbuedUGikDDcfagOMqbMYguI4").okHttpClient(new OkHttpClient()).build();
        SetWebhook wh = new SetWebhook();
        wh.url("https://cryptic-journey-87140.herokuapp.com/ ");
        bot.execute(wh, new Callback<SetWebhook, BaseResponse>() {
            public void onResponse(SetWebhook setWebhook, BaseResponse baseResponse) {

            }

            public void onFailure(SetWebhook setWebhook, IOException e) {
                try {
                    FileWriter writer = new FileWriter("./logs/failLoge.txt", true);
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write("fail, while try set webhook");
                    bufferWriter.close();
                } catch( Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        bot.setUpdatesListener(new UpdatesListener() {
            public int process(List<Update> list) {
                for(final Update item: list){
                    try
                    {
                        FileWriter writer = new FileWriter("./logs/mainLogFile.txt", true);
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write("Date: "+item.message().date() +" text:"+item.message().text()+" chat:"+item.message().chat()+"\n");
                        bufferWriter.close();
                        SendMessage sm = new SendMessage(item.message().chat().id(), item.message().text());
                        bot.execute(sm, new Callback<SendMessage, SendResponse>() {
                            public void onResponse(SendMessage sendMessage, SendResponse sendResponse) {

                            }

                            public void onFailure(SendMessage sendMessage, IOException e) {
                                try {
                                    FileWriter writer = new FileWriter("./logs/failLoge.txt", true);
                                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                                    bufferWriter.write("Date: " + item.message().date() + " text:" + item.message().text() + " chat:" + item.message().chat() + "\n");
                                    bufferWriter.close();
                                } catch( Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }
                    catch(IOException ex){

                        System.out.println(ex.getMessage());
                    }
                }
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }
}
