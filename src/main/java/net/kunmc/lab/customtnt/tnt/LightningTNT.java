package net.kunmc.lab.customtnt.tnt;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.LightningConfig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LightningTNT extends CustomTNT {
    private final LightningConfig config;

    public LightningTNT(Plugin plugin, LightningConfig config) {
        super(plugin);
        this.config = config;
    }

    @Override
    protected void onTNTPrime(TNTPrimeEvent e) {
    }

    @Override
    protected void onExplosionPrime(TNTPrimed tnt) {
        Location center = tnt.getLocation();
        World world = center.getWorld();

        Location loc = center.clone();
        double y = world.getHighestBlockYAt(center);
        loc.setY(y);
        world.strikeLightning(loc);

        new BukkitRunnable() {
            private double radius = config.increasingRadius.value();
            private int count = 0;

            @Override
            public void run() {
                for (int i = 0; i < 8; i++) {
                    double radian = Math.toRadians(360.0 * i / 8);
                    double cos = Math.cos(radian);
                    double sin = Math.sin(radian);
                    Location loc = center.clone().add(cos * radius, 0, sin * radius);
                    double y = world.getHighestBlockYAt(loc);
                    loc.setY(y);
                    world.strikeLightning(loc);
                }

                radius += config.increasingRadius.value();
                count++;
                if (count == config.times.value() - 1) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 16, 16);
    }

    @Override
    public String tabCompleteName() {
        return "lightning";
    }
}
