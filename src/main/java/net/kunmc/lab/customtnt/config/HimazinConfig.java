package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.FloatValue;
import net.kunmc.lab.configlib.value.StringValue;
import net.kunmc.lab.configlib.value.collection.StringListValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class HimazinConfig extends BaseConfig {
    public final DoubleValue radius = new DoubleValue(20.0);
    public final FloatValue volume = new FloatValue(1.0F);
    public final StringValue himazinName = new StringValue("ken_H1maz1n");
    public final StringListValue candidates = new StringListValue();
    public final StringValue displayName = new StringValue("");

    public HimazinConfig(@NotNull Plugin plugin) {
        super(plugin);

        candidates.add("ken_H1maz1n");
        candidates.add("ken_HImaz1n");
        candidates.add("ken_H1mazIn");
        candidates.add("ken_HImazIn");

        candidates.add("ken_H1rnaz1n");
        candidates.add("ken_HIrnaz1n");
        candidates.add("ken_H1rnazIn");
        candidates.add("ken_HIrnazIn");

        candidates.add("H1maz1n");
        candidates.add("HImaz1n");
        candidates.add("H1mazIn");
        candidates.add("HImazIn");

        candidates.add("H1rnaz1n");
        candidates.add("HIrnaz1n");
        candidates.add("H1rnazIn");
        candidates.add("HIrnazIn");
    }
}
