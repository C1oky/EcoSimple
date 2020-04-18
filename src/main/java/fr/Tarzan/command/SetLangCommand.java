package fr.Tarzan.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import fr.Tarzan.Loader;
import fr.Tarzan.components.LanguageUtil;

public class SetLangCommand extends Command {

    private static final Config config = Loader.getInstance().getConfig();

    public SetLangCommand() {
        super("setlang", LanguageUtil.translate("command.setlang.description"), "/setlang [lang]");
        this.setPermission("eco.command.setlang");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        //TODO: Will be implemented in the future...
        return true;
    }
}