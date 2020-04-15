package fr.Tarzan.API;

import cn.nukkit.utils.Config;
import fr.Tarzan.Main;

public class MoneyAPI {

    private static Main instance = Main.getInstance();
    private static final Config config = new Config("plugins/EcoSimple/data/MoneyData.json",Config.JSON);

    public MoneyAPI() {

    }

    public void setMoney(String player, double money){
        if ( money < 0){
            money = 0;
        }
        config.set(player,money);
        config.save();
    }

    public int getMoney(String player){
        if(!config.exists(player)){
            return 0;
        }else {
            Double money = (double) config.get(player);
            return money.intValue();
        }
    }

    public void addMoney(String player, double money){
        money += this.getMoney(player);
        config.set(player,money);
        config.save();
    }

    public void RemoveMoney(String player, double money){
        money = this.getMoney(player) - money;
        if ( money < 0){
            money = 0;
        }
        config.set(player, money);
        config.save();
    }

}
