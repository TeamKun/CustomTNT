package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class HisuiConfig extends BaseConfig {
    public final IntegerValue radius = new IntegerValue(32);
    public final StringValue displayName = new StringValue("ヒスイ転生TNT");

    public HisuiConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
