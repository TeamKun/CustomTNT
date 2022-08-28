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
        RandomizeBlockConfig randomizeBlockConfig = new RandomizeBlockConfig(this);
        SummonWitherConfig summonWitherConfig = new SummonWitherConfig(this);
        SummonMobRandomlyConfig summonMobRandomlyConfig = new SummonMobRandomlyConfig(this);
        KantasukeKakikamaVoiceConfig kantasukeKakikamaVoiceConfig = new KantasukeKakikamaVoiceConfig(this);
        KantasukeUrn1oVoiceConfig kantasukeUrn1oVoiceConfig = new KantasukeUrn1oVoiceConfig(this);
        ServerStopConfig serverStopConfig = new ServerStopConfig(this);

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
        registry.register(new RandomizeBlockTNT(this, randomizeBlockConfig));
        registry.register(new SummonWitherTNT(this, summonWitherConfig));
        registry.register(new SummonMobRandomlyTNT(this, summonMobRandomlyConfig));
        registry.register(new KantasukeKakikamaVoiceTNT(this, kantasukeKakikamaVoiceConfig));
        registry.register(new KantasukeUrn1oVoiceTNT(this, kantasukeUrn1oVoiceConfig));
        registry.register(new ServerStopTNT(this, serverStopConfig));

        ConfigCommand configCommand = new ConfigCommandBuilder(lightningConfig)
                .addConfig(blackHoleConfig)
                .addConfig(sandFireworksConfig)
                .addConfig(meteoriteConfig)
                .addConfig(summonEnderDragonConfig)
                .addConfig(netherizationConfig)
                .addConfig(flameConfig)
                .addConfig(lgbtqConfig)
                .addConfig(floatingIslandConfig)
                .addConfig(randomizeBlockConfig)
                .addConfig(summonWitherConfig)
                .addConfig(summonMobRandomlyConfig)
                .addConfig(kantasukeKakikamaVoiceConfig)
                .addConfig(kantasukeUrn1oVoiceConfig)
                .addConfig(serverStopConfig)
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
