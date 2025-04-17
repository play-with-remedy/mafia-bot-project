package mafia_bot;

import lombok.extern.slf4j.Slf4j;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

@Slf4j
public class Main {

    public static void main(String[] args) {
        try {
            Map<String, String> rules = RuleLoader.loadRules();
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MafiaBot(rules));
        } catch (TelegramApiException ex) {
            log.error(ex.getMessage());
        }
    }
}