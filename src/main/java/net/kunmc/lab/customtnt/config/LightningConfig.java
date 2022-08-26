package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class LightningConfig extends BaseConfig {
    public final DoubleValue increasingRadius = new DoubleValue(2.5);
    public final IntegerValue times = new IntegerValue(4);

    public LightningConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
