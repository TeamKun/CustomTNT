package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.RandomizeBlockConfig;
import net.kunmc.lab.customtnt.util.Util;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RandomizeBlockTNT extends CustomTNT {
    private final RandomizeBlockConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final List<Material> materials = Arrays.stream(Material.values())
            .filter(Material::isBlock)
            .filter(x -> !x.isEmpty())
            .collect(Collectors.toList());

    public RandomizeBlockTNT(Plugin plugin, RandomizeBlockConfig config) {
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

        List<Block> floatTargets = Util.sphereAround(tnt.getLocation(), config.radius.intValue()).stream()
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        new BukkitRunnable() {
            int totalReplaced = 0;

            @Override
            public void run() {
                for (int i = 0; i < config.replacePerTick.value() && totalReplaced < floatTargets.size(); i++, totalReplaced++) {
                    Block target = floatTargets.get(totalReplaced);
                    target.setType(randomMaterial());
                }

                if (totalReplaced >= floatTargets.size()) {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 0);
    }

    private Material randomMaterial() {
        int n = random.nextInt(materials.size());
        return materials.get(n);
    }

    @Override
    public String tabCompleteName() {
        return "randomizeBlock";
    }
}
