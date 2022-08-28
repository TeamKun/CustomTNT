package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.FloatValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class KaikingConfig extends BaseConfig {
    public final IntegerValue quantity = new IntegerValue(4);
    public final FloatValue volume = new FloatValue(0.5F);
    public final FloatValue pitch = new FloatValue(1.0F);
    public final StringValue displayName = new StringValue("");

    public KaikingConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
