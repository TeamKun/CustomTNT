package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.TacowasaConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import xyz.haoshoku.nick.api.NickAPI;

import java.util.concurrent.ThreadLocalRandom;

public class TacowasaTNT extends CustomTNT {
    private final TacowasaConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public TacowasaTNT(Plugin plugin, TacowasaConfig config) {
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
        Bukkit.getOnlinePlayers().stream()
                .filter(x -> Math.abs(tnt.getLocation().distance(x.getLocation())) <= config.radius.value())
                .forEach(x -> {
                    NickAPI.nick(x, "TACOWASA_" + random.nextInt(1000));
                    NickAPI.setSkin(x, config.tacowasaName.value());
                    NickAPI.refreshPlayer(x);
                });
    }

    @Override
    public String tabCompleteName() {
        return "tacowasa";
    }
}
