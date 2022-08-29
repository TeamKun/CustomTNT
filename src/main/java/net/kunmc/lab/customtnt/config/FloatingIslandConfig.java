package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class FloatingIslandConfig extends BaseConfig {
    public final IntegerValue radius = new IntegerValue(8);
    public final IntegerValue replacePerTick = new IntegerValue(200);
    public final IntegerValue floatAmount = new IntegerValue(32);
    public final StringValue displayName = new StringValue("浮島TNT");

    public FloatingIslandConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
