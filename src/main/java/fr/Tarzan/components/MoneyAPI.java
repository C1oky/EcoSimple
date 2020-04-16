package fr.Tarzan.components;

import cn.nukkit.utils.Config;
import fr.Tarzan.Loader;

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

    public static void saveAll() {
        config.save();
    }
}