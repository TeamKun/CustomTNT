package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.ServerStopConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerStopTNT extends CustomTNT {
    private final ServerStopConfig config;

    public ServerStopTNT(Plugin plugin, ServerStopConfig config) {
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
        String s = config.message.value();
        if (s.isEmpty()) {
            Bukkit.getServer().shutdown();
        } else {
            Bukkit.broadcast(Component.text(s));
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().shutdown();
                }
            }.runTaskLater(plugin, 60);
        }
    }

    @Override
    public String tabCompleteName() {
        return "serverStop";
    }
}
