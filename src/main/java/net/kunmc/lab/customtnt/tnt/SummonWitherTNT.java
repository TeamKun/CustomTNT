package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.SummonWitherConfig;
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
        for (int i = 0; i < config.quantity.value(); i++) {
            tnt.getWorld().spawnEntity(tnt.getLocation(), EntityType.WITHER);
        }
    }

    @Override
    public String tabCompleteName() {
        return "summonWither";
    }
}
