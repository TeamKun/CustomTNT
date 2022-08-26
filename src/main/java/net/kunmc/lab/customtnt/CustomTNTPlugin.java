package net.kunmc.lab.customtnt;

import net.kunmc.lab.commandlib.CommandLib;
import net.kunmc.lab.customtnt.command.MainCommand;
import net.kunmc.lab.customtnt.tnt.BlackHoleTNT;
import net.kunmc.lab.customtnt.tnt.LightningTNT;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomTNTPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        CustomTNTRegistry registry = CustomTNTRegistry.getInstance();
        registry.register(new LightningTNT(this));
        registry.register(new BlackHoleTNT(this));
       
        CommandLib.register(this, new MainCommand());
    }

    @Override
    public void onDisable() {
    }
}
