package net.kunmc.lab.customtnt.tnt;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import net.kunmc.lab.customtnt.CustomTNT;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class BlackHoleTNT extends CustomTNT {
    public BlackHoleTNT(Plugin plugin) {
        super(plugin);
    }

    @Override
    protected void onTNTPrime(TNTPrimeEvent e) {

    }

    @Override
    protected void onExplosionPrime(TNTPrimed tnt) {
        Location center = tnt.getLocation();
        World world = center.getWorld();
        int totalTick = 120;

        new BukkitRunnable() {
            private int tick = 0;

            @Override
            public void run() {
                world.spawnParticle(Particle.REDSTONE, center, 3, new Particle.DustOptions(Color.BLACK, 10));
                tick += 4;
                if (tick >= totalTick) {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 4);

        Set<Entity> involvedEntities = Collections.synchronizedSet(new HashSet<>());
        center.getBlock().setType(Material.AIR);
        new BukkitRunnable() {
            private int tick = 0;
            private final ThreadLocalRandom random = ThreadLocalRandom.current();

            @Override
            public void run() {
                int radius = 7;
                sphereAround(center, 5).forEach(x -> {
                    if (x.isEmpty()) {
                        return;
                    }
                    if (random.nextDouble() <= 0.1) {
                        FallingBlock fallingBlock = world.spawnFallingBlock(x.getLocation(), x.getBlockData());
                        fallingBlock.setGravity(false);
                        fallingBlock.setInvulnerable(true);
                        involvedEntities.add(fallingBlock);
                        x.setType(Material.AIR);
                    }
                });

                Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e").stream()
                        .filter(x -> x.getLocation().getWorld().equals(center.getWorld()))
                        .filter(x -> x.getLocation().distance(center) <= radius)
                        .filter(x -> {
                            if (x instanceof Player) {
                                Player p = ((Player) x);
                                return p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE;
                            }
                            return true;
                        })
                        .forEach(involvedEntities::add);

                tick += 4;
                if (tick >= totalTick) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 4);

        new BukkitRunnable() {
            private int tick = 0;

            @Override
            public void run() {
                involvedEntities.forEach(x -> {
                    Vector sub = center.toVector().subtract(x.getLocation().toVector());
                    sub.multiply(0.35 / sub.length());
                    x.setVelocity(sub);
                    if (x instanceof LivingEntity) {
                        ((LivingEntity) x).damage(0.3);
                    }
                });

                tick++;
                if (tick >= totalTick) {
                    involvedEntities.stream()
                            .filter(x -> x instanceof FallingBlock)
                            .forEach(Entity::remove);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 0);
    }

    @Override
    public String tabCompleteName() {
        return "blockHole";
    }

    private static Set<Block> sphereAround(Location location, int radius) {
        Set<Block> sphere = new HashSet<>();
        Block center = location.getBlock();
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = center.getRelative(x, y, z);
                    if (center.getLocation().distance(b.getLocation()) <= radius) {
                        sphere.add(b);
                    }
                }
            }
        }
        return sphere;
    }
}
