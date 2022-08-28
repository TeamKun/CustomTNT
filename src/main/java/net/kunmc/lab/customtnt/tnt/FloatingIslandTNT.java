package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.FloatingIslandConfig;
import net.kunmc.lab.customtnt.util.Util;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class FloatingIslandTNT extends CustomTNT {
    private final FloatingIslandConfig config;

    public FloatingIslandTNT(Plugin plugin, FloatingIslandConfig config) {
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
        List<Block> floatTargets = new ArrayList<>(Util.sphereAround(tnt.getLocation(), config.radius.intValue()));

        new BukkitRunnable() {
            int totalReplaced = 0;

            @Override
            public void run() {
                for (int i = 0; i < config.replacePerTick.value() && totalReplaced < floatTargets.size(); i++, totalReplaced++) {
                    Block target = floatTargets.get(totalReplaced);
                    BlockData targetData = target.getBlockData();
                    target.getLocation().add(0, config.floatAmount.value(), 0).getBlock().setBlockData(targetData, false);
                    target.setType(Material.AIR);
                }

                if (totalReplaced >= floatTargets.size()) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 0);
    }

    @Override
    public String tabCompleteName() {
        return "floatingIsland";
    }
}
