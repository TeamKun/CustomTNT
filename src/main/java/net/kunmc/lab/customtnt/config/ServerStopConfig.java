package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ServerStopConfig extends BaseConfig {
    public final StringValue message = new StringValue("");
    public final StringValue displayName = new StringValue("");

    public ServerStopConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
