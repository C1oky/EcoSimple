package fr.Tarzan.API;

import cn.nukkit.utils.Config;
import fr.Tarzan.Main;

public class MoneyAPI {

    private static Main instance = Main.getInstance();
    private static final Config config = new Config("plugins/EcoSimple/data/MoneyData.json",Config.JSON);

    public MoneyAPI() {

    }

    public void setMoney(String player, double coin){
        config.set(player,coin);
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

    public void addMoney(String player, double coin){
        coin += this.getMoney(player);
        config.set(player,coin);
        config.save();
    }

    public void RemoveMoney(String player, double coin){
        coin = this.getMoney(player) - coin;
        if ( coin < 0){
            coin = 0;
        }
        config.set(player, coin);
        config.save();
    }

}
