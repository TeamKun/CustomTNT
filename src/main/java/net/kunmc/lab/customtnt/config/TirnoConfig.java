package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class TirnoConfig extends BaseConfig {
    public final DoubleValue radius = new DoubleValue(32.0);
    public final IntegerValue quantity = new IntegerValue(40);
    public final DoubleValue spawnRange = new DoubleValue(24.0);
    public final StringValue tirnoName = new StringValue("tirno");
    public final StringValue displayName = new StringValue("チルノTNT");

    public TirnoConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
