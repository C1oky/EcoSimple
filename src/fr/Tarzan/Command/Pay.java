package fr.Tarzan.Command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import fr.Tarzan.API.MoneyAPI;
import fr.Tarzan.Main;

public class Pay extends Command {

    private static final Main instance = Main.getInstance();
    private static final MoneyAPI moneys = Main.getMoneyAPI();

    public Pay() {
        super("pay", "allows you to pay a player");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!sender.isPlayer()) {
            sender.sendMessage("§l[§r§c!!!§f§l]§ryou have to be in the game");
            return false;
        }
            if (args.length < 1) {
                sender.sendMessage("§l[§r§c!!!§f§l]§rYou have to do /pay [name] [amount]");
                return false;
            }
            if (args.length > 1) {
                Player player = instance.getServer().getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage("§l[§r§c!!!§f§l]§rPlayer is not logged in");
                    return false;
                }
                Double money = Double.valueOf(args[1]);
                if(sender.getName().equals(player.getName())){
                    sender.sendMessage("§l[§r§c!§f§l]§ryou can't pay to yourself");
                    return false;
                }
                if (moneys.getMoney(sender.getName()) >= money.intValue()) {
                    moneys.RemoveMoney(sender.getName(), money.intValue());
                    moneys.addMoney(player.getName(), money.intValue());
                    player.sendMessage("you've been paid "+money.intValue()+" \uE102 a "+sender.getName());
                    sender.sendMessage("§l[§r§c!§f§l]§ryou paid " + money.intValue() + " \uE102 at " + player.getName());
                }
                if (moneys.getMoney(sender.getName()) < money.intValue()){
                    sender.sendMessage("§l[§r§c!§f§l]§ryou have enough money");
                }
            } else {
                sender.sendMessage("§l[§r§c!!!§f§l]§rYou do not have permission to make this command");
            }
        return true;
    }
}
