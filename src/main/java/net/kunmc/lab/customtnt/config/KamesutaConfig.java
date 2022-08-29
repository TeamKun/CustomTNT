package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.StringValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class KamesutaConfig extends BaseConfig {
    public final StringValue soundName = new StringValue("kamepower");
    public final StringValue message = new StringValue("<Kamesuta> かめぱわ～～！!");
    public final StringValue displayName = new StringValue("かめすたTNT");

    public KamesutaConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
