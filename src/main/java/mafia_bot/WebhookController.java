package mafia_bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@RestController
public class WebhookController {

    private final MafiaBot mafiaBot;

    @Autowired
    public WebhookController(MafiaBot myWebhookBot) {
        this.mafiaBot = myWebhookBot;
    }

    @PostMapping("/bmf-rules")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return mafiaBot.onWebhookUpdateReceived(update);
    }
}
