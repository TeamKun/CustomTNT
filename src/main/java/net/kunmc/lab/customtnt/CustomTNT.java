package net.kunmc.lab.customtnt;

import net.kunmc.lab.commandlib.Nameable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class CustomTNT implements Listener, Nameable {
    protected final Plugin plugin;

    public CustomTNT(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlaceTNT(BlockPlaceEvent e) {
                ItemStack item = e.getItemInHand();
                if (isThisTNTItem(item)) {
                    e.getBlock().setMetadata(tabCompleteName(), new FixedMetadataValue(plugin, ""));
                }
            }

            @EventHandler
            public void onDispense(BlockDispenseEvent e) {
                ItemStack item = e.getItem();
                if (isThisTNTItem(item)) {
                    e.setCancelled(true);
                    Inventory inventory = ((org.bukkit.block.Dispenser) e.getBlock().getState()).getInventory();
                    inventory.all(Material.TNT).entrySet().stream()
                            .filter(x -> isThisTNTItem(x.getValue()))
                            .findFirst()
                            .ifPresent(entry -> {
                                // イベントをキャンセルするとアイテム数を元の状態に戻す処理が走るため,遅延させてアイテムをセットしている
                                int amount = entry.getValue().getAmount();
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        inventory.setItem(entry.getKey(), entry.getValue().asQuantity(amount));
                                    }
                                }.runTask(plugin);
                            });
                    e.getBlock().getState().update(true);

                    BlockFace face = ((Dispenser) e.getBlock().getBlockData()).getFacing();
                    Location location = e.getBlock().getLocation().add(0.5, 0, 0.5).add(face.getDirection());

                    location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT, CreatureSpawnEvent.SpawnReason.CUSTOM, x -> {
                        TNTPrimed tnt = ((TNTPrimed) x);
                        onTNTPrimed(tnt);
                        tnt.setMetadata(tabCompleteName(), new FixedMetadataValue(plugin, ""));
                    });
                }
            }

            @EventHandler
            public void onBreakTNT(BlockBreakEvent e) {
                if (e.getBlock().hasMetadata(tabCompleteName())) {
                    e.getBlock().removeMetadata(tabCompleteName(), plugin);
                    e.setDropItems(false);
                    e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.DROPPED_ITEM, CreatureSpawnEvent.SpawnReason.CUSTOM, x -> {
                        ((Item) x).setItemStack(getItem());
                    });
                }
            }

            @EventHandler
            public void onTNTPrimedSpawn(EntitySpawnEvent e) {
                if (!(e.getEntity() instanceof TNTPrimed)) {
                    return;
                }
                TNTPrimed tnt = ((TNTPrimed) e.getEntity());

                if (tnt.getLocation().getBlock().hasMetadata(tabCompleteName())) {
                    onTNTPrimed(tnt);
                    tnt.setMetadata(tabCompleteName(), tnt.getLocation().getBlock().getMetadata(tabCompleteName()).get(0));
                    tnt.getLocation().getBlock().removeMetadata(tabCompleteName(), plugin);
                }
            }

            @EventHandler
            public void onPrimedTNT(ExplosionPrimeEvent e) {
                if (e.getEntity().hasMetadata(tabCompleteName())) {
                    onExplosionPrime(((TNTPrimed) e.getEntity()));
                    e.setCancelled(true);
                }
            }
        }, plugin);
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.TNT);
        item.editMeta(meta -> {
            meta.displayName(itemName());
            meta.getPersistentDataContainer().set(NamespacedKey.fromString(tabCompleteName().toLowerCase()), PersistentDataType.STRING, "");
        });

        return item;
    }

    protected final boolean isThisTNTItem(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString(tabCompleteName().toLowerCase()), PersistentDataType.STRING) != null;
    }

    private Component itemName() {
        String s = displayName();
        if (s == null || s.isEmpty()) {
            return Component.text(tabCompleteName().toUpperCase() + " TNT")
                    .decoration(TextDecoration.ITALIC, false);
        }
        return Component.text(displayName())
                .decoration(TextDecoration.ITALIC, false);
    }

    public abstract String displayName();

    protected abstract void onTNTPrimed(TNTPrimed tnt);

    protected abstract void onExplosionPrime(TNTPrimed tnt);

    @Override
    public String toString() {
        return ((TextComponent) itemName()).content();
    }
}
