package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.FloatValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MeteoriteConfig extends BaseConfig {
    public final DoubleValue ascentSpeed = new DoubleValue(1.0);
    public final DoubleValue maxAscentAmount = new DoubleValue(64.0);
    public final IntegerValue meteoriteQuantity = new IntegerValue(32);
    public final DoubleValue fallSpeed = new DoubleValue(3.0);
    public final DoubleValue maxRadius = new DoubleValue(24.0);
    public final FloatValue explosionPower = new FloatValue(2.5F);

    public MeteoriteConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
