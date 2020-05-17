package fr.Tarzan.ecosimple.components;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import fr.Tarzan.ecosimple.Loader;
import fr.Tarzan.ecosimple.event.PlayerCreateAccountEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.DeflaterOutputStream;

public class MoneyAPI {

    public static String configPath = Server.getInstance().getDataPath() + "plugins/EcoSimple/data/money.json";
    private static final Config config = new Config(configPath, Config.JSON);
    private static double defaultMoney = config.getDouble("default", 1000);
    private static MoneyAPI instance;
    private static Loader loader;

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

    public String getFormattedTopMoney(int playersCount) {

        Map<String, Double> counter = calculateMoney();
        String formattedTop = "";
        int viewN = 1;

        for(Map.Entry<String, Double> entry : counter.entrySet()) {
            String name = entry.getKey();
            Double money = entry.getValue();
            formattedTop = formattedTop+ LanguageUtil.translate("command.topmoney.subtitle",viewN,name,money) + "\n";
        viewN++;

        if (viewN == playersCount) {
            return formattedTop;
        }
    }

        return formattedTop;
    }

    public double getDefaultMoney() {
        return defaultMoney;
    }

    public static MoneyAPI getInstance() {
        return instance;
    }

    private Map<String, Double> calculateMoney() {
        HashMap<String, Double> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : config.getAll().entrySet()) {
            map.put(entry.getKey(), (Double) entry.getValue());
        }
        Map<String, Double> sorted = map;
        sorted = map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));

        return sorted;
    }
}