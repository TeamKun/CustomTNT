package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ReinoHimazinConfig extends BaseConfig {
    public final DoubleValue radius = new DoubleValue(64.0);
    public final StringValue displayName = new StringValue("例のひまじんTNT");

    public ReinoHimazinConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
