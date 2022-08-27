package net.kunmc.lab.customtnt;

import net.kunmc.lab.commandlib.CommandLib;
import net.kunmc.lab.configlib.ConfigCommand;
import net.kunmc.lab.configlib.ConfigCommandBuilder;
import net.kunmc.lab.customtnt.command.MainCommand;
import net.kunmc.lab.customtnt.config.BlackHoleConfig;
import net.kunmc.lab.customtnt.config.LightningConfig;
import net.kunmc.lab.customtnt.config.MeteoriteConfig;
import net.kunmc.lab.customtnt.config.SandFireworksConfig;
import net.kunmc.lab.customtnt.tnt.BlackHoleTNT;
import net.kunmc.lab.customtnt.tnt.LightningTNT;
import net.kunmc.lab.customtnt.tnt.MeteoriteTNT;
import net.kunmc.lab.customtnt.tnt.SandFireWorksTNT;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomTNTPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        LightningConfig lightningConfig = new LightningConfig(this);
        BlackHoleConfig blackHoleConfig = new BlackHoleConfig(this);
        SandFireworksConfig sandFireworksConfig = new SandFireworksConfig(this);
        MeteoriteConfig meteoriteConfig = new MeteoriteConfig(this);

        CustomTNTRegistry registry = CustomTNTRegistry.getInstance();
        registry.register(new LightningTNT(this, lightningConfig));
        registry.register(new BlackHoleTNT(this, blackHoleConfig));
        registry.register(new SandFireWorksTNT(this, sandFireworksConfig));
        registry.register(new MeteoriteTNT(this, meteoriteConfig));

        ConfigCommand configCommand = new ConfigCommandBuilder(lightningConfig)
                .addConfig(blackHoleConfig)
                .addConfig(sandFireworksConfig)
                .addConfig(meteoriteConfig)
                .build();
        CommandLib.register(this, new MainCommand(configCommand));
    }

    @Override
    public void onDisable() {
    }
}
