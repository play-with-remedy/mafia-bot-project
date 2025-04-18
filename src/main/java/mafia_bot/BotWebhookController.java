package mafia_bot;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class BotWebhookController {

    private final MafiaBot mafiaBot;
    private final Gson gson;

    public BotWebhookController(MafiaBot mafiaBot) {
        this.mafiaBot = mafiaBot;
        this.gson = new Gson(); // Инициализация Gson
    }

    // Обработка POST запроса на /bmf-rules
    @PostMapping("/bmf-rules")
    public void handleWebhook(@RequestBody String updateJson) {
        // Преобразуем строку в объект Update с помощью Gson
        Update update = gson.fromJson(updateJson, Update.class);

        // Передаем объект Update в метод onWebhookUpdateReceived
        mafiaBot.onWebhookUpdateReceived(update);
    }
}
