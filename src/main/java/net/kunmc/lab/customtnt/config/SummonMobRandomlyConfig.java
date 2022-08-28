package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.BooleanValue;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import net.kunmc.lab.configlib.value.collection.EnumSetValue;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SummonMobRandomlyConfig extends BaseConfig {
    public final IntegerValue quantity = new IntegerValue(30);
    public final DoubleValue radius = new DoubleValue(10.0);
    public final EnumSetValue<EntityType> candidates = new EnumSetValue<EntityType>().listable(false);
    public final BooleanValue summonWither = new BooleanValue(false);
    public final BooleanValue summonEnderDragon = new BooleanValue(false);
    public final StringValue displayName = new StringValue("");

    public SummonMobRandomlyConfig(@NotNull Plugin plugin) {
        super(plugin);

        Arrays.stream(EntityType.values())
                .filter(EntityType::isSpawnable)
                .filter(EntityType::isAlive)
                .forEach(candidates::add);
    }
}
