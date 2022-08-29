package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.KamesutaConfig;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class KamesutaTNT extends CustomTNT {
    private final KamesutaConfig config;

    public KamesutaTNT(Plugin plugin, KamesutaConfig config) {
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
        String message = config.message.value();
        if (message.isEmpty()) {
            Bukkit.getServer().shutdown();
        } else {
            Bukkit.broadcast(Component.text(message));
            Bukkit.getOnlinePlayers().forEach(x -> {
                x.playSound(Sound.sound(NamespacedKey.fromString(config.soundName.value()), Sound.Source.AMBIENT, 1.0F, 1.0F));
            });
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().shutdown();
                }
            }.runTaskLater(plugin, 40);
        }
    }

    @Override
    public String tabCompleteName() {
        return "kamesuta";
    }
}
