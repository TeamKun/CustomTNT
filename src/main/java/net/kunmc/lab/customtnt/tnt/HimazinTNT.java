package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.HimazinConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.haoshoku.nick.api.NickAPI;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class HimazinTNT extends CustomTNT {
    private final HimazinConfig config;
    private final ItemStack himazinHead;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public HimazinTNT(Plugin plugin, HimazinConfig config) {
        super(plugin);
        this.config = config;
        this.himazinHead = new ItemStack(Material.PLAYER_HEAD);

        himazinHead.editMeta(meta -> {
            ((SkullMeta) meta).setOwningPlayer(Bukkit.getOfflinePlayer(config.himazinName.value()));
        });

        config.himazinName.onModify(x -> {
            himazinHead.editMeta(meta -> {
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
        new BukkitRunnable() {
            @Override
            public void run() {
                tnt.getWorld().playSound(tnt.getLocation(), "himazin_eguite", 1.0F, 1.0F);
            }
        }.runTaskLater(plugin, 20);
        affectPlayers(tnt);
        summonMobs(tnt);


        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=husk]").stream()
                        .filter(x -> x.customName() != null)
                        .filter(x -> x.customName() instanceof TextComponent)
                        .filter(x -> ((TextComponent) x.customName()).content().equals("H1maz1n"))
                        .forEach(x -> {
                            x.getWorld().playSound(x.getLocation(), "himazin_eedesune", 1.0F, 1.0F);
                        });
            }
        }.runTaskTimer(plugin, 120, 19);
    }

    private void affectPlayers(TNTPrimed tnt) {
        Location location = tnt.getLocation();
        List<Player> targets = Bukkit.getOnlinePlayers().stream()
                .filter(x -> Math.abs(x.getLocation().distance(location)) < config.radius.value())
                .collect(Collectors.toList());
        if (targets.isEmpty()) {
            return;
        }

        int i = 0;
        for (; i < 4 && i < targets.size(); i++) {
            Player p = targets.get(i);
            new BukkitRunnable() {
                @Override
                public void run() {
                    NickAPI.nick(p, config.himazinName.value());
                    NickAPI.setSkin(p, config.himazinName.value());
                    NickAPI.refreshPlayer(p);
                }
            }.runTaskLater(plugin, i * 4);
        }
        if (i == targets.size()) {
            return;
        }

        int n = i;
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = n; i < targets.size(); i++) {
                    Player p = targets.get(i);
                    NickAPI.nick(p, config.candidates.get(random.nextInt(config.candidates.size())));
                    NickAPI.setSkin(p, config.himazinName.value());
                    NickAPI.refreshPlayer(p);
                }
            }
        }.runTaskLater(plugin, i * 4);
    }

    private void summonMobs(TNTPrimed tnt) {
        for (int i = 0; i < 12; i++) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    summon(tnt.getLocation());
                }
            }.runTaskLater(plugin, i * 2);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    summon(tnt.getLocation().add(random.nextDouble(20) - 10, 0, random.nextDouble(20) - 10));
                }
            }
        }.runTaskLater(plugin, 88);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    summon(tnt.getLocation().add(random.nextDouble(20) - 10, 0, random.nextDouble(20) - 10));
                }
            }
        }.runTaskLater(plugin, 110);
    }

    private void summon(Location location) {
        location.getWorld().spawnEntity(location, EntityType.HUSK, CreatureSpawnEvent.SpawnReason.CUSTOM, entity -> {
            Husk husk = ((Husk) entity);
            husk.setSilent(true);
            husk.clearLootTable();
            husk.customName(Component.text("H1maz1n"));
            husk.setCustomNameVisible(false);

            EntityEquipment equipment = husk.getEquipment();
            equipment.setHelmet(himazinHead);
            equipment.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            equipment.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            equipment.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        });
    }

    @Override
    public String tabCompleteName() {
        return "himazin";
    }
}
