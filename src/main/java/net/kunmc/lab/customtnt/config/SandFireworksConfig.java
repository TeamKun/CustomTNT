package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.FloatValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import net.kunmc.lab.configlib.value.tuple.Double2DoublePairValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SandFireworksConfig extends BaseConfig {
    public final DoubleValue ascentSpeed = new DoubleValue(1.0);
    public final DoubleValue maxAscentAmount = new DoubleValue(20.0);
    public final IntegerValue sandQuantity = new IntegerValue(32);
    public final Double2DoublePairValue initialVelocityRange = new Double2DoublePairValue(0.05, 1.7);
    public final FloatValue explosionPower = new FloatValue(3.0F);
    public final StringValue displayName = new StringValue("砂花火TNT");

    public SandFireworksConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
