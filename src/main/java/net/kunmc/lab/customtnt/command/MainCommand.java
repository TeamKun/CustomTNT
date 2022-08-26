package net.kunmc.lab.customtnt.command;

import net.kunmc.lab.commandlib.Command;

public class MainCommand extends Command {
    public MainCommand() {
        super("customtnt");
        addChildren(new GiveCommand());
    }
}
