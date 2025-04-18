package mafia_bot;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Map<String, String> rules = RuleLoader.loadRules();
        MafiaBot bot = new MafiaBot(rules);
        bot.setWebhook();
    }
}