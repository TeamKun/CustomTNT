package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class RandomizeBlockConfig extends BaseConfig {
    public final IntegerValue radius = new IntegerValue(8);
    public final IntegerValue replacePerTick = new IntegerValue(250);
    public final StringValue displayName = new StringValue("");

    public RandomizeBlockConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
