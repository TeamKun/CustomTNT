package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.*;
import net.kunmc.lab.customtnt.tnt.YaciTNT;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class YaciConfig extends BaseConfig {
    public final DoubleValue radius = new DoubleValue(20.0);
    public final FloatValue volume = new FloatValue(1.0F);
    public final DoubleValue speed = new DoubleValue(1.0);
    public final IntegerValue summonQuantity = new IntegerValue(6);
    public final EnumValue<YaciTNT.Voice> greetingVoice = new EnumValue<>(YaciTNT.Voice.Yacidayo);
    public final StringValue yaciName = new StringValue("Yaci56");
    public final StringValue displayName = new StringValue("");

    public YaciConfig(@NotNull Plugin plugin) {
        super(plugin);
    }
}
