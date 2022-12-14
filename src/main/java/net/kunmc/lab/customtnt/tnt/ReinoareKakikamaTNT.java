package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.ReinoareKakikamaConfig;
import org.bukkit.World;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

public class ReinoareKakikamaTNT extends CustomTNT {
    private final ReinoareKakikamaConfig config;

    public ReinoareKakikamaTNT(Plugin plugin, ReinoareKakikamaConfig config) {
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
        return "reinoareKakikama";
    }
}
