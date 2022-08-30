package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.SandFireworksConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class SandFireWorksTNT extends CustomTNT {
    private final SandFireworksConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public SandFireWorksTNT(Plugin plugin, SandFireworksConfig config) {
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
        tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);

        Location origin = tnt.getLocation();
        World world = origin.getWorld();

        new BukkitRunnable() {
            private final int summonPerTime = 16;
            private int totalSummoned = 0;

            @Override
            public void run() {
                for (int i = 0; i < summonPerTime && totalSummoned <= config.sandQuantity.value(); i++, totalSummoned++) {
                    FallingBlock fallingBlock = world.spawnFallingBlock(origin, Material.SAND.createBlockData());
                    Vector velocity = new Vector(random.nextDouble(2.0) - 1.0, 0, random.nextDouble(2.0) - 1.0)
                            .normalize()
                            .multiply(randomVelocity());
                    fallingBlock.setVelocity(velocity);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Block under = fallingBlock.getLocation().subtract(0, 0.8, 0).getBlock();
                            if (!under.isEmpty()) {
                                fallingBlock.remove();
                                world.createExplosion(fallingBlock.getLocation(), config.explosionPower.value());
                                cancel();
                            }
                        }
                    }.runTaskTimer(plugin, 0, 4);
                }

                if (totalSummoned > config.sandQuantity.value()) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 2);
    }

    private double randomVelocity() {
        return random.nextDouble(config.initialVelocityRange.getRight() - config.initialVelocityRange.getLeft()) + config.initialVelocityRange.getLeft();
    }

    @Override
    public String tabCompleteName() {
        return "sandFireWorks";
    }
}
