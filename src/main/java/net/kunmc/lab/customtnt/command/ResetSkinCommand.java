package net.kunmc.lab.customtnt.command;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.CommandContext;
import org.bukkit.Bukkit;
import xyz.haoshoku.nick.api.NickAPI;

public class ResetSkinCommand extends Command {
    public ResetSkinCommand() {
        super("resetSkin");
    }

    @Override
    protected void execute(CommandContext ctx) {
        Bukkit.getOnlinePlayers().forEach(x -> {
            if (NickAPI.isNicked(x)) {
                NickAPI.resetNick(x);
                NickAPI.resetSkin(x);
                NickAPI.refreshPlayer(x);
            }
        });
    }
}
