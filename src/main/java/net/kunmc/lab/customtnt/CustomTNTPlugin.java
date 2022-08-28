package net.kunmc.lab.customtnt;

import net.kunmc.lab.commandlib.CommandLib;
import net.kunmc.lab.configlib.ConfigCommand;
import net.kunmc.lab.configlib.ConfigCommandBuilder;
import net.kunmc.lab.customtnt.command.MainCommand;
import net.kunmc.lab.customtnt.config.*;
import net.kunmc.lab.customtnt.tnt.*;
import net.minecraft.server.v1_16_R3.DedicatedServer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class CustomTNTPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        LightningConfig lightningConfig = new LightningConfig(this);
        BlackHoleConfig blackHoleConfig = new BlackHoleConfig(this);
        SandFireworksConfig sandFireworksConfig = new SandFireworksConfig(this);
        MeteoriteConfig meteoriteConfig = new MeteoriteConfig(this);
        SummonEnderDragonConfig summonEnderDragonConfig = new SummonEnderDragonConfig(this);
        NetherizationConfig netherizationConfig = new NetherizationConfig(this);
        FlameConfig flameConfig = new FlameConfig(this);
        LGBTQConfig lgbtqConfig = new LGBTQConfig(this);
        FloatingIslandConfig floatingIslandConfig = new FloatingIslandConfig(this);

        CustomTNTRegistry registry = CustomTNTRegistry.getInstance();
        registry.register(new LightningTNT(this, lightningConfig));
        registry.register(new BlackHoleTNT(this, blackHoleConfig));
        registry.register(new SandFireWorksTNT(this, sandFireworksConfig));
        registry.register(new MeteoriteTNT(this, meteoriteConfig));
        registry.register(new SummonEnderDragonTNT(this, summonEnderDragonConfig));
        registry.register(new NetherizationTNT(this, netherizationConfig));
        registry.register(new FlameTNT(this, flameConfig));
        registry.register(new LGBTQTNT(this, lgbtqConfig));
        registry.register(new FloatingIslandTNT(this, floatingIslandConfig));

        ConfigCommand configCommand = new ConfigCommandBuilder(lightningConfig)
                .addConfig(blackHoleConfig)
                .addConfig(sandFireworksConfig)
                .addConfig(meteoriteConfig)
                .addConfig(summonEnderDragonConfig)
                .addConfig(netherizationConfig)
                .addConfig(flameConfig)
                .addConfig(lgbtqConfig)
                .addConfig(floatingIslandConfig)
                .build();
        CommandLib.register(this, new MainCommand(configCommand));

        try {
            DedicatedServer server = ((CraftServer) Bukkit.getServer()).getServer();
            Field f = MinecraftServer.class.getDeclaredField("allowFlight");
            f.setAccessible(true);
            f.set(server, true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
    }
}
