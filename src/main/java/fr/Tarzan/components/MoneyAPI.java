package fr.Tarzan.components;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import fr.Tarzan.event.PlayerCreateAccountEvent;

public class MoneyAPI {

    public static String configPath = Server.getInstance().getDataPath() + "plugins/EcoSimple/data/money.json";
    private static final Config config = new Config(configPath, Config.JSON);
    private static double defaultMoney = config.getDouble("default", 1000);
    private static MoneyAPI instance;

    public MoneyAPI() {
        instance = this;
    }

    public void createAccount(Player player) {
        this.createAccount(player, defaultMoney);
    }

    public void createAccount(Player player, double amount) {
        String playerName = player.getName().toLowerCase();
        PlayerCreateAccountEvent createAccountEvent = new PlayerCreateAccountEvent(player, amount);
        if (!createAccountEvent.isCancelled() && config.exists(playerName)) {
            double defaultMoney = createAccountEvent.getDefaultMoney();
            config.set(playerName, defaultMoney < 0 ? 0 : defaultMoney);
        }
    }

    public double getMoney(Player player) {
        return this.getMoney(player.getName());
    }

    public double getMoney(String player) {
        return config.getDouble(player.toLowerCase(), 0);
    }

    public void setMoney(Player player, double amount) {
        this.setMoney(player.getName(), amount);
    }

    public void setMoney(String player, double amount) {
        config.set(player.toLowerCase(), amount < 0 ? 0 : amount);
        config.save(true);
    }

    public void addMoney(Player player, double amount) {
        this.addMoney(player.getName(), amount);
    }

    public void addMoney(String player, double amount) {
        config.set(player, amount + this.getMoney(player));
        config.save(true);
    }

    public void removeMoney(Player player, double amount) {
        this.removeMoney(player.getName(), amount);
    }

    public void removeMoney(String player, double amount) {
        amount = this.getMoney(player) - amount;
        config.set(player.toLowerCase(), amount < 0 ? 0 : amount);
        config.save(true);
    }

    public void setDefaultMoney(double amount) {
        defaultMoney = amount;
        config.set("default", defaultMoney);
        config.save(true);
    }

    public double getDefaultMoney() {
        return defaultMoney;
    }

    public String getFormattedTopMoney(int playersCount) {
        return "";
    }

    public static MoneyAPI getInstance() {
        return instance;
    }
}