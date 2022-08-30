package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.SummonMobRandomlyConfig;
import org.bukkit.Sound;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class SummonMobRandomlyTNT extends CustomTNT {
    private final SummonMobRandomlyConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public SummonMobRandomlyTNT(Plugin plugin, SummonMobRandomlyConfig config) {
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
        tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);

        List<EntityType> candidates = config.candidates.stream()
                .filter(x -> x != EntityType.WITHER || config.summonWither.isTrue())
                .filter(x -> x != EntityType.ENDER_DRAGON || config.summonEnderDragon.isTrue())
                .collect(Collectors.toList());
        double radius = config.radius.value();

        for (int i = 0; i < config.quantity.value(); i++) {
            int n = random.nextInt(candidates.size());
            EntityType type = candidates.get(n);
            tnt.getWorld().spawnEntity(tnt.getLocation().add(random.nextDouble(radius * 2) - radius, 0, random.nextDouble(radius * 2) - radius),
                    type,
                    CreatureSpawnEvent.SpawnReason.CUSTOM,
                    x -> {
                        if (x instanceof EnderDragon) {
                            ((EnderDragon) x).setPhase(EnderDragon.Phase.LAND_ON_PORTAL);
                        }
                    });
        }
    }

    @Override
    public String tabCompleteName() {
        return "summonMobRandomly";
    }
}
