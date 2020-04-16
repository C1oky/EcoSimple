package fr.Tarzan.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import fr.Tarzan.API.MoneyAPI;
import fr.Tarzan.Loader;

public class GiveMoneyCommand extends Command {
    private static final Loader instance = Loader.getInstance();
    private static final MoneyAPI moneys = Loader.getMoneyAPI();

    public GiveMoneyCommand() {
        super("givemoney", "allows you to give money");
        this.setPermission("eco.perm");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if (sender.hasPermission("eco.perm")) {

            if (args.length == 0) {
                sender.sendMessage("§l[§r§c!!!§f§l]§rYou have to do /givemoney [amount] / [name] [amount]");
                return false;
            }
            if (sender.isPlayer()) {
                if (args.length == 1) {
                    Double money = Double.valueOf(args[0]);
                    moneys.addMoney(sender.getName(), money.intValue());
                    sender.sendMessage("§l[§r§c!§f§l]§ryou gave yourself" + money.intValue() + " \uE102");
                    return false;

                }
            }
            if (args.length > 1) {
                Player player = instance.getServer().getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage("§l[§r§c!!!§f§l]§rPlayer is not logged in");
                    return false;
                }
                Double money = Double.valueOf(args[1]);
                moneys.addMoney(player.getName(), money.intValue());
                sender.sendMessage("§l[§r§c!§f§l]§ryou gave " + money.intValue() + " \uE102 at " + player.getName());

            } else {
                sender.sendMessage("§l[§r§c!!!§f§l]§rYou do not have permission to make this command");
            }
        }
        return true;
    }
}