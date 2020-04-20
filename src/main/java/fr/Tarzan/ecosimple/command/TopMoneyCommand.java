package fr.Tarzan.ecosimple.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import fr.Tarzan.ecosimple.Loader;
import fr.Tarzan.ecosimple.components.LanguageUtil;

public class TopMoneyCommand extends Command {

    private final Config config = Loader.getInstance().getConfig();

    public TopMoneyCommand() {
        super("topmoney", LanguageUtil.translate("command.topmoney.description"), "/topmoney");
        this.setPermission("eco.command.topmoney");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        //TODO: Will be implemented in the future...
        return true;
    }
}