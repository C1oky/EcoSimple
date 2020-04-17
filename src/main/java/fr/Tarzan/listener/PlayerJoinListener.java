package fr.Tarzan.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import fr.Tarzan.Loader;
import fr.Tarzan.components.MoneyAPI;

import java.util.Map;
import java.util.UUID;

public class PlayerJoinListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        //TODO
    }
}