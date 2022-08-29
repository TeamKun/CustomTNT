package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.ReinoHimazinConfig;
import net.kyori.adventure.sound.Sound;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ReinoHimazinTNT extends CustomTNT {
    private final ReinoHimazinConfig config;

    public ReinoHimazinTNT(Plugin plugin, ReinoHimazinConfig config) {
        super(plugin);
        this.config = config;
    }

    @Override
    public String displayName() {
        return config.displayName.value();
    }

    @Override
    protected void onTNTPrimed(TNTPrimed tnt) {
        tnt.setFuseTicks(405);
        Bukkit.getOnlinePlayers().stream()
                .filter(x -> Math.abs(x.getLocation().distance(tnt.getLocation())) < config.radius.value())
                .forEach(x -> {
                    x.playSound(Sound.sound(NamespacedKey.fromString("reinohimazin"), Sound.Source.AMBIENT, 1.0F, 1.0F));
                });
    }

    @Override
    protected void onExplosionPrime(TNTPrimed tnt) {
        tnt.getWorld().spawnEntity(tnt.getLocation(), EntityType.FIREWORK, CreatureSpawnEvent.SpawnReason.CUSTOM, x -> {
            Firework firework = ((Firework) x);

            FireworkMeta meta = firework.getFireworkMeta();
            meta.setPower(1);
            meta.addEffect(FireworkEffect.builder()
                    .withColor(Color.WHITE)
                    .withTrail()
                    .with(FireworkEffect.Type.BALL_LARGE)
                    .build());
            firework.setFireworkMeta(meta);
        });

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().stream()
                        .filter(x -> Math.abs(x.getLocation().distance(tnt.getLocation())) < config.radius.value())
                        .forEach(x -> {
                            x.sendTitle(ChatColor.YELLOW + "KINCHO", ChatColor.YELLOW + "金鳥の夏、日本の夏", 10, 60, 20);
                        });
            }
        }.runTaskLater(plugin, 30);
    }

    @Override
    public String tabCompleteName() {
        return "reinoHimazin";
    }
}
