package fr.Tarzan.Command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import fr.Tarzan.API.MoneyAPI;
import fr.Tarzan.Main;

public class SetMoney extends Command {
    private static Main instance = Main.getInstance();

    private static MoneyAPI moneys = Main.getMoneyAPI();

    public SetMoney() {
        super("setmoney","allows you to bring the economy up to the number you've set.");
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if (sender.hasPermission("eco.perm")){

            if (args.length == 0 ){
                sender.sendMessage("§l[§r§c!!!§f§l]§rYou have to do /setmoney [amount] / [name] [amount]");
                return false;
            }
            if (sender.isPlayer()) {
                if (args.length == 1) {
                    Double money = Double.valueOf(args[0]);
                    moneys.setMoney(sender.getName(), money.intValue());
                    sender.sendMessage("§l[§r§c!§f§l]§ryou set the money of" + money.intValue() + " \uE102 "+"to yourself");
                    return false;
                }
            }
            if (args.length > 1){
                Player player = instance.getServer().getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage("§l[§r§c!!!§f§l]§rPlayer is not logged in");
                    return false;
                }
                Double money = Double.valueOf(args[1]);
                moneys.setMoney(player.getName(), money.intValue());
                sender.sendMessage("§l[§r§c!§f§l]§ryou set the money of "+ money.intValue() + " \uE102 at "+ player.getName());
            }
        }else {
            sender.sendMessage("§l[§r§c!!!§f§l]§rYou do not have permission to make this command");
        }
        return true;
    }
}
