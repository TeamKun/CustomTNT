package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class KoutanConfig extends BaseConfig {
    public final IntegerValue radius = new IntegerValue(12);
    public final StringValue koutanName = new StringValue("GOD_koutan");
    public final StringValue displayName = new StringValue("こうたんTNT");

    public KoutanConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
