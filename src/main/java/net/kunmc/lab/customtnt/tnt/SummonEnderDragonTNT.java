package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.SummonEnderDragonConfig;
import net.minecraft.server.v1_16_R3.DragonControllerPhase;
import net.minecraft.server.v1_16_R3.EntityEnderDragon;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;

public class SummonEnderDragonTNT extends CustomTNT {
    private final SummonEnderDragonConfig config;

    public SummonEnderDragonTNT(Plugin plugin, SummonEnderDragonConfig config) {
        super(plugin);
        this.config = config;
    }

    @Override
    public String displayName() {
        return config.displayName.value();
    }

    @Override
    protected void onTNTPrimed(TNTPrimed tnt) {
    }

    @Override
    protected void onExplosionPrime(TNTPrimed tnt) {
        config.changeBaseLocationOnExplosion.ifTrue(() -> {
            config.enderDragonBaseLocation.setValueWithEvent(tnt.getLocation().toVector());
            config.saveConfigIfPresent();
        });

        for (int i = 0; i < config.quantity.value(); i++) {
            tnt.getWorld().spawnEntity(tnt.getLocation(), EntityType.ENDER_DRAGON, CreatureSpawnEvent.SpawnReason.CUSTOM, x -> {
                EntityEnderDragon enderDragon = ((CraftEnderDragon) x).getHandle();
                enderDragon.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
            });
        }
    }

    @Override
    public String tabCompleteName() {
        return "summonEnderDragon";
    }
}
