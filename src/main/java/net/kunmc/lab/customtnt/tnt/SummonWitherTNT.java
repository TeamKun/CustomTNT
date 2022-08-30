package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.SummonWitherConfig;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

public class SummonWitherTNT extends CustomTNT {
    private final SummonWitherConfig config;

    public SummonWitherTNT(Plugin plugin, SummonWitherConfig config) {
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

        for (int i = 0; i < config.quantity.value(); i++) {
            tnt.getWorld().spawnEntity(tnt.getLocation(), EntityType.WITHER);
        }
    }

    @Override
    public String tabCompleteName() {
        return "summonWither";
    }
}
