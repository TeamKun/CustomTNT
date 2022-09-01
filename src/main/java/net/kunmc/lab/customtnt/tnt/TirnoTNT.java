package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.TirnoConfig;
import net.kyori.adventure.sound.Sound;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class TirnoTNT extends CustomTNT {
    private final TirnoConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final ItemStack tirnoHead;

    public TirnoTNT(@NotNull Plugin plugin, TirnoConfig config) {
        super(plugin);
        this.config = config;
        this.tirnoHead = new ItemStack(Material.PLAYER_HEAD);

        tirnoHead.editMeta(meta -> {
            ((SkullMeta) meta).setOwningPlayer(Bukkit.getOfflinePlayer(config.tirnoName.value()));
        });

        config.tirnoName.onModify(x -> {
            tirnoHead.editMeta(meta -> {
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
        Location center = tnt.getLocation();
        World world = tnt.getWorld();
        Optional<Player> target = getTarget();

        for (int i = 0; i < config.quantity.value(); i++) {
            double range = config.spawnRange.value();
            Location location = center.clone().add(random.nextDouble(range) - range / 2, 0, random.nextDouble(range) - range / 2);

            world.spawnEntity(location, EntityType.ARMOR_STAND, CreatureSpawnEvent.SpawnReason.CUSTOM, entity -> {
                ArmorStand armorStand = ((ArmorStand) entity);
                armorStand.setItem(EquipmentSlot.HEAD, tirnoHead);
                armorStand.setItem(EquipmentSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
                armorStand.setItem(EquipmentSlot.LEGS, new ItemStack(Material.LEATHER_LEGGINGS));
                armorStand.setItem(EquipmentSlot.FEET, new ItemStack(Material.LEATHER_BOOTS));
                armorStand.setArms(true);
                armorStand.setMetadata("customtnt-tirno", new FixedMetadataValue(plugin, ""));

                target.ifPresent(x -> {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (armorStand.isDead()) {
                                cancel();
                                return;
                            }
                            Location directed = location.clone().setDirection(x.getLocation().toVector().subtract(location.toVector()))
                                    .add(0, 0.5, 0);
                            armorStand.teleportAsync(directed);
                        }
                    }.runTaskTimerAsynchronously(plugin, 0, 4);
                });

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        armorStand.remove();
                    }
                }.runTaskLater(plugin, 280 + tnt.getFuseTicks());
            });

            Bukkit.getOnlinePlayers().stream()
                    .filter(x -> Math.abs(x.getLocation().distance(tnt.getLocation())) < config.radius.value())
                    .forEach(x -> x.getEquipment().setHelmet(tirnoHead));
        }
    }

    @Override
    protected void onExplosionPrime(TNTPrimed tnt) {
        Bukkit.getOnlinePlayers().stream()
                .filter(x -> Math.abs(x.getLocation().distance(tnt.getLocation())) < config.radius.value())
                .forEach(x -> {
                    Bukkit.getOnlinePlayers().forEach(y -> {
                        x.sendMessage(String.format("<%s> ちるのハッピーハッピー!", y.getName()));
                    });
                    x.playSound(Sound.sound(NamespacedKey.fromString("tirno"), Sound.Source.AMBIENT, 1.0F, 1.0F));
                });
    }

    @Override
    public String tabCompleteName() {
        return "tirno";
    }

    private Optional<Player> getTarget() {
        Player kun = Bukkit.getPlayer("roadhog_kun");
        if (kun != null) {
            return Optional.of(kun);
        }

        List<? extends Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (players.isEmpty()) {
            return Optional.empty();
        }

        Collections.shuffle(players);
        return Optional.of(players.get(0));
    }
}
