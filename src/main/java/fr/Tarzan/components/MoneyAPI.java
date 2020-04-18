package fr.Tarzan.components;

import cn.nukkit.utils.Config;
import fr.Tarzan.Loader;
import java.util.Map;

public class MoneyAPI {

    private static Loader instance = Loader.getInstance();
    private static final Config config = new Config("plugins/EcoSimple/data/MoneyData.json", Config.JSON);

    public void setMoney(String player, double money) {
        if (money < 0) {
            money = 0;
        }
        config.set(player, money);
        config.save();
    }

    public int getMoney(String player) {
        if (!config.exists(player)) {
            return 0;
        } else {
            Double money = (double) config.get(player);
            return money.intValue();
        }
    }

    public void addMoney(String player, double money) {
        money += this.getMoney(player);
        config.set(player, money);
        config.save();
    }

    public void removeMoney(String player, double money) {
        money = this.getMoney(player) - money;
        if (money < 0) {
            money = 0;
        }
        config.set(player, money);
        config.save();
    }


    public String getFormattedTopMoney(int PlayersCount){
        //not sorted in Dev
        Map<String, Object> top = config.getAll();
        String formattedTop = "";
        int viewN = 1;
        for (Map.Entry<String, Object> moneytop: top.entrySet()) {
            formattedTop = formattedTop + "§7#" + viewN + " §c" + moneytop.getKey() + " §favec §c" + moneytop.getValue()  + " §f" + "$ \n";
            viewN++;

            if (viewN == PlayersCount) {
                return formattedTop;
            }

        }
        return formattedTop;
    }

    public static void saveAll() {
        config.save();
    }
}