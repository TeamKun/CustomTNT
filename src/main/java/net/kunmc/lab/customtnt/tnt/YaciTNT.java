package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.YaciConfig;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

public class YaciTNT extends CustomTNT {
    private final YaciConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final ItemStack yaciHead;

    public YaciTNT(Plugin plugin, YaciConfig config) {
        super(plugin);
        this.config = config;
        this.yaciHead = new ItemStack(Material.PLAYER_HEAD);

        yaciHead.editMeta(meta -> {
            ((SkullMeta) meta).setOwningPlayer(Bukkit.getOfflinePlayer(config.yaciName.value()));
        });

        config.yaciName.onModify(x -> {
            yaciHead.editMeta(meta -> {
                ((SkullMeta) meta).setOwningPlayer(Bukkit.getOfflinePlayer(x));
            });
        });
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
                .filter(x -> Math.abs(x.getLocation().distance(tnt.getLocation())) <= config.radius.value())
                .forEach(x -> {
                    x.playSound(Sound.sound(NamespacedKey.fromString(config.greetingVoice.value().name), Sound.Source.AMBIENT, 1.0F, 1.0F));
                });

        World world = tnt.getWorld();
        for (int i = 0; i < config.summonQuantity.value(); i++) {
            Entity entity = world.spawnEntity(tnt.getLocation(), EntityType.HUSK, CreatureSpawnEvent.SpawnReason.CUSTOM, x -> {
                Husk husk = ((Husk) x);
                husk.setSilent(true);
                husk.clearLootTable();
                husk.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                husk.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(config.speed.value());

                EntityEquipment equipment = husk.getEquipment();
                equipment.setHelmet(yaciHead);
                equipment.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                equipment.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                equipment.setBoots(new ItemStack(Material.LEATHER_BOOTS));
            });

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (entity.isDead()) {
                        cancel();
                        return;
                    }

                    world.playSound(entity.getLocation(), Voice.Yacidesu.name, config.volume.value(), 1.0F);
                }
            }.runTaskTimer(plugin, config.greetingVoice.value().ticks + 4, 24);
        }

        world.playSound(tnt.getLocation(), Voice.Yacidayo.name, 10.0F, 1.0F);
    }

    @Override
    public String tabCompleteName() {
        return "yaci";
    }

    public enum Voice {
        Yacidesu("yaci_desu", 20),
        Yahho("yaci_yahho", 20),
        Yacidayo("yaci_yacidayoyacidayo", 120);

        String name;
        int ticks;

        Voice(String name, int ticks) {
            this.name = name;
            this.ticks = ticks;
        }
    }
}
