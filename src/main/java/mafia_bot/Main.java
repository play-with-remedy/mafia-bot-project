package mafia_bot;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, String> rules = RuleLoader.loadRules();
        MafiaBot bot = new MafiaBot(rules);
        bot.setWebhook();
    }
}