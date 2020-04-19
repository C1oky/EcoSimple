package fr.Tarzan.ecosimple;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import fr.Tarzan.ecosimple.components.MoneyAPI;
import fr.Tarzan.ecosimple.command.*;
import java.util.Arrays;

public class Loader extends PluginBase implements Listener {

    private static final MoneyAPI MONEY_API = new MoneyAPI();
    private static Loader instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.registerCommands();
        Server.getInstance().getPluginManager().registerEvents(this, this);
    }

    private void registerCommands() {
        Server.getInstance().getCommandMap().registerAll("", Arrays.asList(
                new GiveMoneyCommand(),
                new SetMoneyCommand(),
                new SeeMoneyCommand(),
                new MyMoneyCommand(),
                new TopMoneyCommand(),
                new MyMoneyCommand(),
                new SetLangCommand(),
                new PayCommand()
        ));
    }

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        MONEY_API.createAccount(event.getPlayer());
    }

    public static Loader getInstance() {
        return instance;
    }

    public static MoneyAPI getMoneyApi() {
        return MONEY_API;
    }
}