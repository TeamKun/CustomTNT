package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class FlameConfig extends BaseConfig {
    public final IntegerValue radius = new IntegerValue(10);
    public final StringValue displayName = new StringValue("");

    public FlameConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
