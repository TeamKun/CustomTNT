package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class HumanFireworksConfig extends BaseConfig {
    public final DoubleValue radius = new DoubleValue(20.0);
    public final DoubleValue ascentSpeed = new DoubleValue(30.0);
    public final DoubleValue ascentAmount = new DoubleValue(36.0);
    public final StringValue displayName = new StringValue("人間花火TNT");

    public HumanFireworksConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
