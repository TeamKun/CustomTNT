package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SummonWitherConfig extends BaseConfig {
    public final IntegerValue quantity = new IntegerValue(3);
    public final StringValue displayName = new StringValue("");

    public SummonWitherConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
