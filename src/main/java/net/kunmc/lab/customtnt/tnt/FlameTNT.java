package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.FlameConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

public class FlameTNT extends CustomTNT {
    private final FlameConfig config;

    public FlameTNT(Plugin plugin, FlameConfig config) {
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
        int radius = config.radius.value();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Block b = tnt.getLocation().add(x, 0, z).toHighestLocation().add(0, 1, 0).getBlock();
                b.setType(Material.FIRE);
            }
        }
    }

    @Override
    public String tabCompleteName() {
        return "flame";
    }
}
