package fr.Tarzan.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import fr.Tarzan.Loader;
import fr.Tarzan.components.LanguageUtil;
import fr.Tarzan.components.MoneyAPI;

public class SeeMoneyCommand extends Command {
    private static MoneyAPI moneys = Loader.getMoneyAPI();
    private static Loader instance = Loader.getInstance();
    public SeeMoneyCommand(){
        super("seemoney", LanguageUtil.translate("command.seemoney.description"),"/seemoney [Name]");
        this.setPermission("eco.command.seemoney");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.usage", this.usageMessage));
            return false;
        }

        Player player = instance.getServer().getPlayer(args[0]);
        String playerName = args[0];

        if (player != null) {
            playerName = player.getName();
        }

        try {

            sender.sendMessage(LanguageUtil.translate("command.seemoney.message", moneys.getMoney(playerName), playerName ));
        } catch (Exception exception) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.usage", this.usageMessage));
        }

        return true;
    }
}
