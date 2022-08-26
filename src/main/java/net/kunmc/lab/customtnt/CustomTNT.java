package net.kunmc.lab.customtnt;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import net.kunmc.lab.commandlib.Nameable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

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
            public void onPrimedTNT(TNTPrimeEvent e) {
                if (e.getBlock().hasMetadata(tabCompleteName())) {
                    onTNTPrime(e);
                    e.setCancelled(false);
                }
            }

            @EventHandler
            public void onTNTPrimedSpawn(EntitySpawnEvent e) {
                if (!(e.getEntity() instanceof TNTPrimed)) {
                    return;
                }

                if (e.getLocation().getBlock().hasMetadata(tabCompleteName())) {
                    e.getEntity().setMetadata(tabCompleteName(), e.getLocation().getBlock().getMetadata(tabCompleteName()).get(0));
                    e.getLocation().getBlock().removeMetadata(tabCompleteName(), plugin);
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
            meta.displayName(displayName());
            meta.getPersistentDataContainer().set(NamespacedKey.fromString(tabCompleteName().toLowerCase()), PersistentDataType.STRING, "");
        });

        return item;
    }

    protected final boolean isThisTNTItem(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString(tabCompleteName().toLowerCase()), PersistentDataType.STRING) != null;
    }

    public Component displayName() {
        return Component.text(tabCompleteName().toUpperCase() + " TNT");
    }

    protected abstract void onTNTPrime(TNTPrimeEvent e);

    protected abstract void onExplosionPrime(TNTPrimed tnt);

    @Override
    public String toString() {
        return ((TextComponent) displayName()).content();
    }
}
