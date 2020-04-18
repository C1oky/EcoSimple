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

public class PayCommand extends Command {

    private final MoneyAPI api = Loader.getMoneyApi();

    public PayCommand() {
        super("pay", LanguageUtil.translate("command.pay.description"), "/pay [name] [amount]");
        this.getCommandParameters().put("default",
                new CommandParameter[]{
                        new CommandParameter("player", CommandParamType.TARGET, true),
                        new CommandParameter("amount", CommandParamType.INT, true)
                });
        this.setPermission("eco.command.pay");
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

        if (args.length < 2) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.usage", this.usageMessage));
            return false;
        }

        Player target = Server.getInstance().getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.playerNotFount"));
            return false;
        }

        try {
            double money = Double.parseDouble(args[1]);

            if (sender.equals(target)) {
                sender.sendMessage(LanguageUtil.translate("command.pay.yourself"));
                return false;
            }

            if (api.getMoney(sender.getName()) >= money) {
                api.removeMoney(sender.getName(), money);
                api.addMoney(target.getName(), money);

                sender.sendMessage(LanguageUtil.translate("command.pay.success", target.getName(), money));
                target.sendMessage(LanguageUtil.translate("command.pay.successTarget", sender.getName(), money));
                return true;
            }

            sender.sendMessage(LanguageUtil.translate("command.pay.deficiency"));
        } catch (Exception exception) {
            sender.sendMessage(LanguageUtil.translate("commands.generic.usage", this.usageMessage));
            return false;
        }
        return true;
    }
}