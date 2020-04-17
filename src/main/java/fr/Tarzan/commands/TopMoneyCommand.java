package fr.Tarzan.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import fr.Tarzan.Loader;
import fr.Tarzan.components.LanguageUtil;
import fr.Tarzan.components.MoneyAPI;

public class TopMoneyCommand extends Command {
    private static MoneyAPI money = Loader.getMoneyAPI();
    private static Loader instance = Loader.getInstance();
    //in Dev
    public TopMoneyCommand(){
        super("topmoney", LanguageUtil.translate("command.topmoney.description"));
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        //Is not Sorted in Dev
            sender.sendMessage(LanguageUtil.translate("command.topmoney.presentation")+ "\n" + "\n" + money.getFormattedTopMoney(instance.getConfig().get("topmoney.playercount",10)));
        return true;
    }
}
