package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.MeteoriteConfig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class MeteoriteTNT extends CustomTNT {
    private final MeteoriteConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public MeteoriteTNT(Plugin plugin, MeteoriteConfig config) {
        super(plugin);
        this.config = config;
    }

    @Override
    public String displayName() {
        return config.displayName.value();
    }

    @Override
    protected void onTNTPrimed(TNTPrimed tnt) {
        tnt.setGravity(false);

        double maxY = tnt.getLocation().getY() + config.maxAscentAmount.value();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (tnt.isDead()) {
                    cancel();
                    return;
                }

                if (tnt.getLocation().getY() >= maxY) {
                    tnt.setVelocity(new Vector());
                    cancel();
                    return;
                }

                tnt.setVelocity(new Vector(0, config.ascentSpeed.value(), 0));
            }
        }.runTaskTimerAsynchronously(plugin, 0, 0);
    }

    @Override
    protected void onExplosionPrime(TNTPrimed tnt) {
        Location origin = tnt.getLocation();
        World world = origin.getWorld();

        new BukkitRunnable() {
            private final int summonPerTime = 16;
            private int totalSummoned = 0;

            @Override
            public void run() {
                for (int i = 0; i < summonPerTime && totalSummoned <= config.meteoriteQuantity.value(); i++, totalSummoned++) {
                    world.spawnEntity(randomizeLocation(origin), EntityType.FIREBALL, CreatureSpawnEvent.SpawnReason.CUSTOM, x -> {
                        Fireball fireball = ((Fireball) x);
                        fireball.setDirection(new Vector(0, -1, 0));
                        fireball.setVelocity(new Vector(0, -config.fallSpeed.value(), 0));
                        fireball.setYield(config.explosionPower.value());
                    });
                }

                if (totalSummoned > config.meteoriteQuantity.value()) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 2);
    }

    private Location randomizeLocation(Location origin) {
        double x = random.nextDouble(config.maxRadius.multiply(2)) - config.maxRadius.value();
        double z = random.nextDouble(config.maxRadius.multiply(2)) - config.maxRadius.value();
        return origin.clone().add(x, 0, z);
    }

    @Override
    public String tabCompleteName() {
        return "meteorite";
    }
}
