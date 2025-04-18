package mafia_bot;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;

public class MafiaBot extends TelegramWebhookBot  {

    private final Map<String, String> rules;

    public MafiaBot(Map<String, String> rules) {
        this.rules = rules;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(@NotNull Update update) {
        // Проверяем наличие callback-запроса
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            return handleCallbackQuery(chatId, callbackData);
        }

        // Проверяем, если это текстовое сообщение
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            System.out.println("Received message: " + text);

            if (text.equals("/start")) {
                return sendInlineKeyboard(chatId); // Отправка клавиатуры
            }
        }

        return null; // В случае, если запрос не требует ответа
    }

    private @NotNull SendMessage sendInlineKeyboard(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = getButtonLists();
        inlineKeyboardMarkup.setKeyboard(rows);

        SendMessage message = new SendMessage(Long.toString(chatId), "Выберите необходимый раздел:");
        message.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(message);
            System.out.println("Sent inline keyboard to chatId: {}" + chatId);
        } catch (Exception e) {
            System.out.println("Error sending message with inline keyboard: " + e);
        }

        return message; // Возвращаем SendMessage, который Telegram должен отправить
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

    private @NotNull SendMessage handleCallbackQuery(long chatId, @NotNull String callbackData) {
        String response = switch (callbackData) {
            case "foul" -> rules.get("foul");
            case "yellow" -> rules.get("yellow");
            case "red" -> rules.get("red");
            case "disqualifying" -> rules.get("disqualifying");
            case "teamLoss" -> rules.get("teamLoss");
            case "specSituation" -> rules.get("specSituation");
            default -> "Неизвестная команда.";
        };

        return sendMessage(chatId, response); // Возвращаем сообщение для отправки
    }

    private @NotNull SendMessage sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(Long.toString(chatId), text);
        message.enableMarkdown(true);

        try {
            execute(message);
            System.out.println("Message sent to chatId: {}" + chatId);
        } catch (Exception e) {
            System.out.println("Error sending message: " + e);
        }

        return message; // Возвращаем SendMessage для отправки через Telegram API
    }

    @Override
    public String getBotUsername() {
        return "bmf_rules_bot";
    }

    @Override
    public String getBotToken() {
        return "7673511304:AAEcROKIfhRcZDFb6wAyoii-i0rwer5gbMk";
    }

    @Override
    public String getBotPath() {
        return "/bmf-rules"; // Указываем путь для Webhook
    }

    public void setWebhook() {
        String webhookUrl = "https://fierce-oasis-69058-93ed7aa5961d.herokuapp.com" + getBotPath();
        SetWebhook setWebhook = new SetWebhook();
        setWebhook.setUrl(webhookUrl);

        try {
            execute(setWebhook);  // Устанавливаем webhook
        } catch (TelegramApiException e) {
            System.out.println("Error setting webhook: " + e);
        }
    }
}