package net.kunmc.lab.customtnt.command;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.configlib.ConfigCommand;

public class MainCommand extends Command {
    public MainCommand(ConfigCommand configCommand) {
        super("customtnt");
        addChildren(new GiveCommand(), configCommand, new ResetSkinCommand());
    }
}
