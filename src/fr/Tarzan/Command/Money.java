package fr.Tarzan.Command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import fr.Tarzan.API.MoneyAPI;
import fr.Tarzan.Main;

public class Money extends Command {

    private static MoneyAPI money = Main.getMoneyAPI();

    public Money() { super("money", "let's see that money"); }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender.isPlayer()) {
            int mymoney = money.getMoney(sender.getName());
            sender.sendMessage("§lMoney$(§cs§f)$r: "+ mymoney+" \uE102");
        }else  {
            sender.sendMessage("§l[§r§c!!!§f§l]§ryou have to be in the game");
        }
        return true;
    }
}