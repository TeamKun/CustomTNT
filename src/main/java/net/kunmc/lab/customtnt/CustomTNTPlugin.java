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
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

public final class CustomTNTPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        LightningConfig lightningConfig = new LightningConfig(this);
        BlackHoleConfig blackHoleConfig = new BlackHoleConfig(this);
        SandFireworksConfig sandFireworksConfig = new SandFireworksConfig(this);
        MeteoriteConfig meteoriteConfig = new MeteoriteConfig(this);
        SummonEnderDragonConfig summonEnderDragonConfig = new SummonEnderDragonConfig(this);

        CustomTNTRegistry registry = CustomTNTRegistry.getInstance();
        registry.register(new LightningTNT(this, lightningConfig));
        registry.register(new BlackHoleTNT(this, blackHoleConfig));
        registry.register(new SandFireWorksTNT(this, sandFireworksConfig));
        registry.register(new MeteoriteTNT(this, meteoriteConfig));
        registry.register(new SummonEnderDragonTNT(this, summonEnderDragonConfig));

        ConfigCommand configCommand = new ConfigCommandBuilder(lightningConfig)
                .addConfig(blackHoleConfig)
                .addConfig(sandFireworksConfig)
                .addConfig(meteoriteConfig)
                .addConfig(summonEnderDragonConfig)
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

        new BukkitRunnable() {
            @Override
            public void run() {
                summonEnderDragonConfig.changeEnderDragonDestination(summonEnderDragonConfig.enderDragonBaseLocation.value());
            }
        }.runTaskLater(this, 4);
    }

    @Override
    public void onDisable() {
    }
}
