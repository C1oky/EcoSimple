package fr.Tarzan;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import fr.Tarzan.components.MoneyAPI;
import fr.Tarzan.commands.*;
import fr.Tarzan.listener.PlayerJoinListener;

import java.util.Arrays;

public class Loader extends PluginBase {

    private static Loader instance;
    private static MoneyAPI moneys = new MoneyAPI();

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.registerListeners();
        this.registerCommands();
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerJoinListener()
        ).forEach(listener -> Server.getInstance().getPluginManager().registerEvents(listener, instance));
    }

    private void registerCommands() {
        Server.getInstance().getCommandMap().registerAll("", Arrays.asList(
                new GiveMoneyCommand(),
                new SetMoneyCommand(),
                new MyMoneyCommand(),
                new PayCommand()
        ));
    }

    public static Loader getInstance() {
        return instance;
    }

    public static MoneyAPI getMoneyAPI() {
        return moneys;
    }

    @Override
    public void onDisable() {
        MoneyAPI.saveAll();
    }
}