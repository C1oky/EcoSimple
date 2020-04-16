package fr.Tarzan;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import fr.Tarzan.API.MoneyAPI;
import fr.Tarzan.commands.*;

public class Loader extends PluginBase {

    public static Loader instance;
    private final Config money = new Config("plugins/EcoSimple/data/MoneyData.json", Config.JSON);
    public static MoneyAPI moneys;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        instance = this;
        moneys = new MoneyAPI();
        this.getServer().getLogger().info("EcoSimple is Enable");
        registerCommand();
    }

    @Override
    public void onDisable() {
        money.save();
        this.getServer().getLogger().info("EcoSimple is Disable");
    }

    private void registerCommand() {
        SimpleCommandMap command = this.getServer().getCommandMap();
        command.register("givemoney", new GiveMoneyCommand());
        command.register("setmoney", new SetMoneyCommand());
        command.register("money", new MyMoneyCommand());
        command.register("pay", new PayCommand());
    }

    public static Loader getInstance() {
        return instance;
    }

    public static MoneyAPI getMoneyAPI() {
        return moneys;
    }
}