package fr.Tarzan.ecosimple.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import fr.Tarzan.ecosimple.Loader;
import fr.Tarzan.ecosimple.components.LanguageUtil;
import fr.Tarzan.ecosimple.components.MoneyAPI;

public class TopMoneyCommand extends Command {

    private final MoneyAPI money = Loader.getMoneyApi();
    private final Loader loader = Loader.getInstance();

    public TopMoneyCommand() {
        super("topmoney", LanguageUtil.translate("command.topmoney.description"), "/topmoney");
        this.setPermission("eco.command.topmoney");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        sender.sendMessage(LanguageUtil.translate("command.topmoney.presentation")+ "\n\n" + money.getFormattedTopMoney(loader.getConfig().getInt("topmoney.playercount")));
        return true;
    }
}