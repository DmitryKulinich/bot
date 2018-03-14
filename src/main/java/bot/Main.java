package bot;



import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import okhttp3.OkHttpClient;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot.Builder("521960020:AAHGQKGlW_QbuedUGikDDcfagOMqbMYguI4").okHttpClient(new OkHttpClient()).build();
        bot.setUpdatesListener(new UpdatesListener() {
            public int process(List<Update> list) {
                for(Update item: list){
                    try
                    {
                        FileWriter writer = new FileWriter("./logs/mainLogFile.txt", true);
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write("Date: "+item.message().date() +" text:"+item.message().text()+" chat:"+item.message().chat()+"\n");
                        bufferWriter.close();
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
