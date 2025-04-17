package mafia_bot;

import java.util.Map;

public class RuleLoader {

    public static Map<String, String> loadRules() {
        return  Map.of(
                "foul", "*Фол дается за:*\n\n" +
                        "• Речь не в свою игровую минуту, в том числе междометия, шёпот и артикуляция со звуком\n" +
                        "• Использование излишней жестикуляции. Сначала Судья обязан устно предупредить игрока о таковой. И сделать это так, чтобы игрок понял, что обращаются конкретно к нему\n" +
                        "• “Дневные” прикосновения\n" +
                        "• Стуки по игровому столу, неэтичное поведение по отношению к другим игрокам и Судьям\n" +
                        "• Грубая оценки речи игроков («это была вонючая речь», «речь у тебя блевотная»)\n" +
                        "• Споры с Судьёй\n" +
                        "• Отдёргивание руки при голосовании до подведения итогов голосования (слова Судьи “Стоп” или \"Спасибо\"), постановка руки на стол до голосования\n" +
                        "• Жестикуляция и призывы во время фазы голосования (в том числе мычание)\n" +
                        "• Нарушение установленного порядка голосования (неголосование в случае, когда Судья требует поставить оставшиеся голоса против последней кандидатуры и т.п.)",

                "yellow", "*Желтая карточка дается за:*\n\n" +
                        "• Речь на повышенном тоне, запредельном эмоционале. Вы сделали предупреждение, поставили фол, а игрок продолжает кричать – демонстрируем ему жёлтую карточку\n" +
                        "• Фразы «свалил со стола», «пошёл вон», «игрок №…, вы кринж» - жёлтая\n" +
                        "• Фразы манипулятивного и оскорбительного характера: «с тобой противно за столом сидеть», «я с тобой больше за игровой стол не сяду», «ты такой жалкий» и тд",

                "red", "*Красная карточка дается за:*\n\n" +
                        "• Истеричное поведение за игровым столом. Визги, ор, бросок номерком, битье кулаками по столу. Что-то такое, что перебор для просто желтой карты, но ещё не дотягивает до ППК.",

                "disqualifying", "*Игрок удаляется за:*\n\n" +
                        "• Покидание игрового стола\n" +
                        "• Нарушение ночной посадки, в том числе “ночные” прикосновения к другим игрокам и “ночные” подсказки знаками Дону и Шерифу\n" +
                        "• Нанесение оскорблений другим игрокам, Судьям или зрителям («какой же ты клоун», «убери свои культяпки»)\n" +
                        "• Непроизвольное подглядывание “ночью”\n" +
                        "• “Ночные” разговоры или выкрики, иные нарушения правил ночного поведения\n" +
                        "• Слёзы и предслёзное состояние за игровым столом\n" +
                        "• Апеллирование к неигровым этическим и/или религиозным ценностям или иные \"неигровые апелляции\" с целью доказывания своей роли прямым или косвенным образом, или влияния на исход голосования...",

                "teamLoss", "*ППК присуждается за:*\n\n" +
                        "• Использование клятвы и/или пари в ЛЮБОЙ форме, а также их аналогов. «Клянусь, я поел», «Клянусь, я так же подумал»\n" +
                        "• Использование шантажа, угроз и/или подкупа\n" +
                        "• Подсказки из зрительного зала\n" +
                        "• Доказывание игроком, \"вскрывшимся\" шерифом, своей роли с помощью уникальной “ночной” информации (конкретные действия игроков или события, которые можно было увидеть во время \"проверки\"...",

                "specSituation", "*Особые ситуации*:\n\n" +
                        "• Если игрок покидает игровой стол, получая 4-й или дисквалифицирующий фол, то ближайшее или текущее голосование не проводится, кроме случаев, когда этот игрок был убит ночью или является покинувшим игру по результату голосования.\n" +
                        "• Если игрок покидает игровой стол, получая 4-й или дисквалифицирующий фол после того, как стал известен результат голосования текущего дня, и он не является покидающим игру в результате этого голосования, то голосование на следующий день не проводится."
        );
    }
}