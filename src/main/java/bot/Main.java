package bot;



import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import okhttp3.OkHttpClient;

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
                        FileWriter writer = new FileWriter("C:\\SomeDir\\notes3.txt", false);
                        // запись всей строки
                        String text = "Мама мыла раму, раму мыла мама";
                        writer.write(text);
                        // запись по символам
                        writer.append('\n');
                        writer.append('E');

                        writer.flush();
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
