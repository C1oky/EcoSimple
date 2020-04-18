package fr.Tarzan.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

public class PlayerCreateAccountEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private double defaultMoney;

    public PlayerCreateAccountEvent(Player player, double defaultMoney) {
        this.player = player;
        this.defaultMoney = defaultMoney;
    }

    public double getDefaultMoney() {
        return this.defaultMoney;
    }

    public void setDefaultMoney(double defaultMoney) {
        this.defaultMoney = defaultMoney;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }
}