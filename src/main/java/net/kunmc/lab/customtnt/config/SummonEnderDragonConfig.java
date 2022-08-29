package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.BooleanValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import net.kunmc.lab.configlib.value.VectorValue;
import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.WorldGenEndTrophy;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class SummonEnderDragonConfig extends BaseConfig {
    public final IntegerValue quantity = new IntegerValue(3);
    public final VectorValue enderDragonBaseLocation = new VectorValue(new Vector(0, 0, 0))
            .onModify(this::changeEnderDragonDestination);
    public final BooleanValue changeBaseLocationOnExplosion = new BooleanValue(true);
    public final StringValue displayName = new StringValue("エンドラTNT");

    public SummonEnderDragonConfig(@NotNull Plugin plugin) {
        super(plugin);
        onInitialize(() -> {
            changeEnderDragonDestination(enderDragonBaseLocation.value());
        });
    }

    @Override
    public void saveConfigIfPresent() {
        super.saveConfigIfPresent();
    }

    private void changeEnderDragonDestination(Vector v) {
        BlockPosition pos = WorldGenEndTrophy.a;
        pos.setX(v.getBlockX());
        pos.setZ(v.getBlockZ());
    }
}
