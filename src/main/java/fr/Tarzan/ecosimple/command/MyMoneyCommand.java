package fr.Tarzan.ecosimple.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import fr.Tarzan.ecosimple.components.LanguageUtil;
import fr.Tarzan.ecosimple.components.MoneyAPI;
import fr.Tarzan.ecosimple.Loader;

public class MyMoneyCommand extends Command {

    private final MoneyAPI api = Loader.getMoneyApi();

    public MyMoneyCommand() {
        super("money", LanguageUtil.translate("command.mymoney.description"), "/money", new String[]{"mymoney", "balance", "bal"});
        this.setPermission("eco.command.balance");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.inGame"));
            return false;
        }

        if (!this.testPermission(sender)) {
            return false;
        }

        sender.sendMessage(LanguageUtil.translate("command.mymoney.success", api.getMoney(sender.getName())));
        return true;
    }
}