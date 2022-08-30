package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.NetherizationConfig;
import net.kunmc.lab.customtnt.util.Util;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

public class NetherizationTNT extends CustomTNT {
    private final NetherizationConfig config;

    public NetherizationTNT(Plugin plugin, NetherizationConfig config) {
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
        tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);

        List<Block> replaceTargets = Util.sphereAround(tnt.getLocation(), config.radius.intValue()).stream()
                .filter(x -> config.convertTable.containsKey(x.getType()))
                .collect(Collectors.toList());

        new BukkitRunnable() {
            int totalReplaced = 0;

            @Override
            public void run() {
                for (int i = 0; i < config.replacePerTick.value() && totalReplaced < replaceTargets.size(); i++, totalReplaced++) {
                    Block target = replaceTargets.get(totalReplaced);
                    target.setType(config.convertTable.getOrDefault(target.getType(), Material.NETHERRACK), false);
                }

                if (totalReplaced >= replaceTargets.size()) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 0);
    }

    @Override
    public String tabCompleteName() {
        return "netherization";
    }
}
