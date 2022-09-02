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
        HisuiConfig flameConfig = new HisuiConfig(this);
        TadasyakaConfig lgbtqConfig = new TadasyakaConfig(this);
        FloatingIslandConfig floatingIslandConfig = new FloatingIslandConfig(this);
        RandomizeBlockConfig randomizeBlockConfig = new RandomizeBlockConfig(this);
        SummonWitherConfig summonWitherConfig = new SummonWitherConfig(this);
        SummonMobRandomlyConfig summonMobRandomlyConfig = new SummonMobRandomlyConfig(this);
        net.kunmc.lab.customtnt.config.ReinoareKakikamaConfig kantasukeKakikamaVoiceConfig = new net.kunmc.lab.customtnt.config.ReinoareKakikamaConfig(this);
        ReinoareUron1oConfig kantasukeUrn1oVoiceConfig = new ReinoareUron1oConfig(this);
        KamesutaConfig serverStopConfig = new KamesutaConfig(this);
        KoutanConfig koutanConfig = new KoutanConfig(this);
        NatsuConfig mickeyConfig = new NatsuConfig(this);
        KaikingConfig kaikingConfig = new KaikingConfig(this);
        NagatukiConfig nagatukiConfig = new NagatukiConfig(this);
        TacowasaConfig tacowasaConfig = new TacowasaConfig(this);
        YaciConfig yaciConfig = new YaciConfig(this);
        HimazinConfig himazinConfig = new HimazinConfig(this);
        HumanFireworksConfig humanFireworksConfig = new HumanFireworksConfig(this);
        ReinoHimazinConfig reinoHimazinConfig = new ReinoHimazinConfig(this);
        TirnoConfig tirnoConfig = new TirnoConfig(this);

        CustomTNTRegistry registry = CustomTNTRegistry.getInstance();
        registry.register(new LightningTNT(this, lightningConfig));
        registry.register(new BlackHoleTNT(this, blackHoleConfig));
        registry.register(new SandFireWorksTNT(this, sandFireworksConfig));
        registry.register(new MeteoriteTNT(this, meteoriteConfig));
        registry.register(new SummonEnderDragonTNT(this, summonEnderDragonConfig));
        registry.register(new NetherizationTNT(this, netherizationConfig));
        registry.register(new HisuiTNT(this, flameConfig));
        registry.register(new TadasyakaTNT(this, lgbtqConfig));
        registry.register(new FloatingIslandTNT(this, floatingIslandConfig));
        registry.register(new RandomizeBlockTNT(this, randomizeBlockConfig));
        registry.register(new SummonWitherTNT(this, summonWitherConfig));
        registry.register(new SummonMobRandomlyTNT(this, summonMobRandomlyConfig));
        registry.register(new ReinoareKakikamaTNT(this, kantasukeKakikamaVoiceConfig));
        registry.register(new ReinoareUrn1oTNT(this, kantasukeUrn1oVoiceConfig));
        registry.register(new KamesutaTNT(this, serverStopConfig));
        registry.register(new KoutanTNT(this, koutanConfig));
        registry.register(new NatsuTNT(this, mickeyConfig));
        registry.register(new KaikingTNT(this, kaikingConfig));
        registry.register(new NagatukiTNT(this, nagatukiConfig));
        registry.register(new TacowasaTNT(this, tacowasaConfig));
        registry.register(new YaciTNT(this, yaciConfig));
        registry.register(new HimazinTNT(this, himazinConfig));
        registry.register(new HumanFireworksTNT(this, humanFireworksConfig));
        registry.register(new ReinoHimazinTNT(this, reinoHimazinConfig));
        registry.register(new TirnoTNT(this, tirnoConfig));

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
                .addConfig(koutanConfig)
                .addConfig(mickeyConfig)
                .addConfig(kaikingConfig)
                .addConfig(nagatukiConfig)
                .addConfig(tacowasaConfig)
                .addConfig(yaciConfig)
                .addConfig(himazinConfig)
                .addConfig(humanFireworksConfig)
                .addConfig(reinoHimazinConfig)
                .addConfig(tirnoConfig)
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
