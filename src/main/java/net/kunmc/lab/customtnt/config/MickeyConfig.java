package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MickeyConfig extends BaseConfig {
    public final StringValue displayName = new StringValue("なつTNT");

    public MickeyConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
