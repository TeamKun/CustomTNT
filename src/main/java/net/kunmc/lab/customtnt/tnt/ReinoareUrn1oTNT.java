package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.ReinoareUron1oConfig;
import org.bukkit.World;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

public class ReinoareUrn1oTNT extends CustomTNT {
    private final ReinoareUron1oConfig config;

    public ReinoareUrn1oTNT(Plugin plugin, ReinoareUron1oConfig config) {
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
        World world = tnt.getWorld();
        world.playSound(tnt.getLocation(), config.soundName.value(), config.volume.value(), config.pitch.value());
    }

    @Override
    public String tabCompleteName() {
        return "reinoareUrn1o";
    }
}
