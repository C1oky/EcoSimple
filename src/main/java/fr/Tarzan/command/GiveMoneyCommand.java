package fr.Tarzan.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import fr.Tarzan.components.LanguageUtil;
import fr.Tarzan.components.MoneyAPI;
import fr.Tarzan.Loader;

public class GiveMoneyCommand extends Command {

    private final MoneyAPI api = Loader.getMoneyApi();

    public GiveMoneyCommand() {
        super("givemoney", LanguageUtil.translate("command.givemoney.description"), "/givemoney [name] [amount]");
        this.getCommandParameters().put("default",
                new CommandParameter[]{
                        new CommandParameter("player", CommandParamType.TARGET, true),
                        new CommandParameter("amount", CommandParamType.INT, true)
                });
        this.setPermission("eco.command.givemoney");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.usage", this.usageMessage));
            return false;
        }

        Player player = Server.getInstance().getPlayer(args[0]);
        String playerName = args[0];
        if (player != null) {
            playerName = player.getName();
        }

        try {
            double money = Double.parseDouble(args[1]);

            api.addMoney(playerName, money);
            sender.sendMessage(LanguageUtil.translate("command.givemoney.success", money, playerName));
        } catch (Exception exception) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.usage", this.usageMessage));
            return false;
        }
        return true;
    }
}