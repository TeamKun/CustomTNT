package net.kunmc.lab.customtnt.tnt;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.NagatukiConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;

public class NagatukiTNT extends CustomTNT {
    private final NagatukiConfig config;
    private final ItemStack nagatukiHead;

    public NagatukiTNT(Plugin plugin, NagatukiConfig config) {
        super(plugin);
        this.config = config;
        this.nagatukiHead = new ItemStack(Material.PLAYER_HEAD);

        nagatukiHead.editMeta(meta -> {
            ((SkullMeta) meta).setOwningPlayer(Bukkit.getOfflinePlayer(config.nagatukiName.value()));
        });

        config.nagatukiName.onModify(x -> {
            nagatukiHead.editMeta(meta -> {
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
        try (EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(tnt.getWorld()))) {
            InputStream nagatukiSchem = plugin.getResource("nagatuki.schem");
            ClipboardReader reader = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getReader(nagatukiSchem);
            ClipboardHolder clipboardHolder = new ClipboardHolder(reader.read());

            Location loc = tnt.getLocation();
            Operations.complete(clipboardHolder.createPaste(editSession)
                    .to(BlockVector3.at(loc.getX(), loc.getY() - 2, loc.getZ()))
                    .ignoreAirBlocks(true)
                    .build());

            tnt.getWorld().spawnEntity(loc.add(0.1, 2, -0.61), EntityType.ARMOR_STAND, CreatureSpawnEvent.SpawnReason.CUSTOM, entity -> {
                ArmorStand armorStand = ((ArmorStand) entity);
                armorStand.setBasePlate(false);
                armorStand.setArms(true);

                armorStand.setItem(EquipmentSlot.HEAD, nagatukiHead);
                armorStand.setItem(EquipmentSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
                armorStand.setItem(EquipmentSlot.LEGS, new ItemStack(Material.LEATHER_LEGGINGS));
                armorStand.setItem(EquipmentSlot.FEET, new ItemStack(Material.LEATHER_BOOTS));

                armorStand.setRightArmPose(armorStand.getRightArmPose().add(Math.toRadians(-45), 0, 0));
                armorStand.setLeftArmPose(armorStand.getLeftArmPose().add(Math.toRadians(-45), 0, 0));
            });

            tnt.getWorld().playSound(loc, "nagatuki", config.volume.value(), config.pitch.value());
        } catch (IOException | WorldEditException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String tabCompleteName() {
        return "nagatuki";
    }
}
