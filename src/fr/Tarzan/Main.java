package fr.Tarzan;

import cn.nukkit.command.Command;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import fr.Tarzan.API.MoneyAPI;
import fr.Tarzan.Command.GiveMoney;
import fr.Tarzan.Command.Money;
import fr.Tarzan.Command.Pay;
import fr.Tarzan.Command.SetMoney;

public class Main extends PluginBase {

    public static Main instance;
    private final Config  money = new Config("plugins/EcoSimple/data/MoneyData.json",Config.JSON);
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

    private void registerCommand(){
        SimpleCommandMap command = this.getServer().getCommandMap();
        command.register("givemoney",new GiveMoney());
        command.register("setmoney", new SetMoney());
        command.register("money",new Money());
        command.register("pay",new Pay());
    }

    public static Main getInstance(){
        return instance;
    }

    public static MoneyAPI getMoneyAPI(){
        return moneys;
    }
}
