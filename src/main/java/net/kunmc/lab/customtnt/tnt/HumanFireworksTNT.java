package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.HumanFireworksConfig;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class HumanFireworksTNT extends CustomTNT {
    private final HumanFireworksConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public HumanFireworksTNT(Plugin plugin, HumanFireworksConfig config) {
        super(plugin);
        this.config = config;

        Bukkit.getPluginManager().registerEvents(this, plugin);
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
        Bukkit.getOnlinePlayers().stream()
                .filter(x -> Math.abs(x.getLocation().distance(tnt.getLocation())) < config.radius.value())
                .filter(x -> x.getGameMode() != GameMode.CREATIVE)
                .filter(x -> x.getGameMode() != GameMode.SPECTATOR)
                .forEach(x -> {
                    x.setGravity(false);

                    new BukkitRunnable() {
                        final double initialY = x.getLocation().getY();

                        @Override
                        public void run() {
                            double n = config.ascentSpeed.divide(20);
                            x.setVelocity(new Vector(0, n, 0));

                            if (x.getLocation().getY() >= config.ascentAmount.plus(initialY) ||
                                    !x.getLocation().add(0, 2.0, 0).getBlock().isPassable()) {
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        Location location = x.getLocation();
                                        World world = x.getWorld();
                                        world.spawnEntity(location, EntityType.FIREWORK, CreatureSpawnEvent.SpawnReason.CUSTOM, entity -> {
                                            Firework firework = ((Firework) entity);

                                            FireworkMeta meta = firework.getFireworkMeta();
                                            meta.setPower(random.nextInt(30));
                                            meta.addEffect(FireworkEffect.builder()
                                                    .with(randomEnum(FireworkEffect.Type.class))
                                                    .flicker(random.nextDouble() < 0.5)
                                                    .trail(random.nextDouble() < 0.5)
                                                    .withColor(randomColor())
                                                    .withFade(randomColor())
                                                    .build());
                                            firework.setFireworkMeta(meta);

                                            firework.detonate();
                                            x.setGravity(true);
                                        });
                                    }
                                }.runTask(plugin);

                                cancel();
                            }
                        }
                    }.runTaskTimerAsynchronously(plugin, 0, 0);
                });
    }

    private Color randomColor() {
        return Color.fromRGB(random.nextInt(16677216));
    }

    private <T extends Enum<T>> T randomEnum(Class<T> clazz) {
        T[] arr = clazz.getEnumConstants();
        return arr[random.nextInt(arr.length)];
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        e.getPlayer().setGravity(true);
    }

    @Override
    public String tabCompleteName() {
        return "humanFireworks";
    }
}
