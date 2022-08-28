package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.FloatValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class NagatukiConfig extends BaseConfig {
    public final StringValue nagatukiName = new StringValue("Nagatuki");
    public final FloatValue volume = new FloatValue(1.0F);
    public final FloatValue pitch = new FloatValue(1.0F);
    public final StringValue displayName = new StringValue("");

    public NagatukiConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
