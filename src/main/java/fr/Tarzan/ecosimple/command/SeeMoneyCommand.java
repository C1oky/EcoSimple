package fr.Tarzan.ecosimple.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import fr.Tarzan.ecosimple.Loader;
import fr.Tarzan.ecosimple.components.LanguageUtil;
import fr.Tarzan.ecosimple.components.MoneyAPI;

public class SeeMoneyCommand extends Command {

    private final MoneyAPI api = Loader.getMoneyApi();

    public SeeMoneyCommand() {
        super("seemoney", LanguageUtil.translate("command.seemoney.description"), "/seemoney [name]");
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

        Player player = Server.getInstance().getPlayer(args[0]);
        String playerName = args[0];

        if (player != null) {
            playerName = player.getName();
        }

        sender.sendMessage(LanguageUtil.translate("command.seemoney.message", api.getMoney(playerName), playerName));
        return true;
    }
}