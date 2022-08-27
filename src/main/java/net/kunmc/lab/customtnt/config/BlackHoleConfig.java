package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BlackHoleConfig extends BaseConfig {
    public final IntegerValue duration = new IntegerValue(120);
    public final IntegerValue radius = new IntegerValue(7);
    public final DoubleValue involveBlocksProbability = new DoubleValue(0.1);
    public final DoubleValue damage = new DoubleValue(0.3);
    public final IntegerValue limitOfBlocks = new IntegerValue(150);

    public BlackHoleConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
