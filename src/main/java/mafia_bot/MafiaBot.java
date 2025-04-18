package mafia_bot;

import lombok.extern.slf4j.Slf4j;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Map;

@Slf4j
public class MafiaBot extends TelegramLongPollingBot {

    private final Map<String, String> rules;

    public MafiaBot(Map<String, String> rules) {
        this.rules = rules;
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            handleCallbackQuery(chatId, callbackData);
        }

        if (update.getMessage() != null && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            log.info("Received message: {}", text);

            if (text.equals("/start")) {
                sendInlineKeyboard(chatId);
            }
        }
    }

    private void sendInlineKeyboard(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = getButtonLists();
        inlineKeyboardMarkup.setKeyboard(rows);

        SendMessage message = new SendMessage(Long.toString(chatId), "Выберите необходимый раздел:");
        message.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(message);
            log.info("Sent inline keyboard to chatId: {}", chatId);
        } catch (Exception e) {
            log.error("Error sending message with inline keyboard: ", e);
        }
    }

    private static @NotNull @Unmodifiable List<List<InlineKeyboardButton>> getButtonLists() {
        InlineKeyboardButton foulButton = new InlineKeyboardButton("Фол ⚖️");
        foulButton.setCallbackData("foul");

        InlineKeyboardButton yellowCartButton = new InlineKeyboardButton("Желтая 🟨");
        yellowCartButton.setCallbackData("yellow");

        InlineKeyboardButton redsCartButton = new InlineKeyboardButton("Красная 🟥");
        redsCartButton.setCallbackData("red");

        InlineKeyboardButton disqualifyingButton = new InlineKeyboardButton("Удаление 🚫");
        disqualifyingButton.setCallbackData("disqualifying");

        InlineKeyboardButton teamLossButton = new InlineKeyboardButton("ППК 🔴");
        teamLossButton.setCallbackData("teamLoss");

        InlineKeyboardButton specSituationButton = new InlineKeyboardButton("Особые ситуации ⚠️");
        specSituationButton.setCallbackData("specSituation");

        return List.of(
                List.of(foulButton, yellowCartButton),
                List.of(redsCartButton, disqualifyingButton),
                List.of(teamLossButton, specSituationButton)
        );
    }

    private void handleCallbackQuery(long chatId, @NotNull String callbackData) {
        String response = switch (callbackData) {
            case "foul" -> rules.get("foul");
            case "yellow" -> rules.get("yellow");
            case "red" -> rules.get("red");
            case "disqualifying" -> rules.get("disqualifying");
            case "teamLoss" -> rules.get("teamLoss");
            case "specSituation" -> rules.get("specSituation");
            default -> "Неизвестная команда.";
        };

        sendMessage(chatId, response);
        sendInlineKeyboard(chatId);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(Long.toString(chatId), text);
        message.enableMarkdown(true);

        try {
            execute(message);
            log.info("Message sent to chatId: {}", chatId);
        } catch (Exception e) {
            log.error("Error sending message: ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return "bmf_rules_bot";
    }

    @Override
    public String getBotToken() {
        return "7673511304:AAEcROKIfhRcZDFb6wAyoii-i0rwer5gbMk";
    }
}