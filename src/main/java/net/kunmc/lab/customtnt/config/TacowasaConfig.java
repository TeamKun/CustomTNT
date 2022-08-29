package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class TacowasaConfig extends BaseConfig {
    public final DoubleValue radius = new DoubleValue(10.0);
    public final StringValue tacowasaName = new StringValue("TACOWASA_999");
    public final StringValue displayName = new StringValue("たこわさTNT");

    public TacowasaConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
