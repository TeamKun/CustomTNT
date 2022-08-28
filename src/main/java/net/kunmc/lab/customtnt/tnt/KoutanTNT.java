package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.KoutanConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class KoutanTNT extends CustomTNT {
    private final KoutanConfig config;
    private final List<List<Material>> koutanFace = new ArrayList<List<Material>>() {{
        add(new ArrayList<Material>() {{
            IntStream.range(0, 8).forEach(x -> add(Material.YELLOW_CONCRETE));
        }});

        add(new ArrayList<Material>() {{
            add(Material.YELLOW_CONCRETE);
            add(Material.SNOW_BLOCK);
            add(Material.BLACK_CONCRETE);
            IntStream.range(0, 2).forEach(x -> add(Material.YELLOW_CONCRETE));
            add(Material.SNOW_BLOCK);
            add(Material.BLACK_CONCRETE);
            add(Material.YELLOW_CONCRETE);
        }});

        add(new ArrayList<Material>() {{
            add(Material.YELLOW_CONCRETE);
            IntStream.range(0, 2).forEach(x -> add(Material.SNOW_BLOCK));
            IntStream.range(0, 2).forEach(x -> add(Material.YELLOW_CONCRETE));
            IntStream.range(0, 2).forEach(x -> add(Material.SNOW_BLOCK));
            add(Material.YELLOW_CONCRETE);
        }});

        add(new ArrayList<Material>() {{
            IntStream.range(0, 8).forEach(x -> add(Material.YELLOW_CONCRETE));
        }});

        add(new ArrayList<Material>() {{
            add(Material.YELLOW_CONCRETE);
            IntStream.range(0, 6).forEach(x -> add(Material.RED_CONCRETE));
            add(Material.YELLOW_CONCRETE);
        }});

        add(new ArrayList<Material>() {{
            add(Material.YELLOW_CONCRETE);
            IntStream.range(0, 4).forEach(x -> add(Material.RED_CONCRETE));
            IntStream.range(0, 2).forEach(x -> add(Material.PINK_CONCRETE));
            add(Material.YELLOW_CONCRETE);
        }});

        add(new ArrayList<Material>() {{
            IntStream.range(0, 2).forEach(x -> add(Material.YELLOW_CONCRETE));
            IntStream.range(0, 2).forEach(x -> add(Material.RED_CONCRETE));
            IntStream.range(0, 2).forEach(x -> add(Material.PINK_CONCRETE));
            IntStream.range(0, 2).forEach(x -> add(Material.YELLOW_CONCRETE));
        }});

        add(new ArrayList<Material>() {{
            IntStream.range(0, 8).forEach(x -> add(Material.YELLOW_CONCRETE));
        }});
    }};
    private final ItemStack koutanHead;

    public KoutanTNT(Plugin plugin, KoutanConfig config) {
        super(plugin);
        this.config = config;
        this.koutanHead = new ItemStack(Material.PLAYER_HEAD);

        koutanHead.editMeta(meta -> {
            ((SkullMeta) meta).setOwningPlayer(Bukkit.getOfflinePlayer(config.koutanName.value()));
        });

        config.koutanName.onModify(x -> {
            koutanHead.editMeta(meta -> {
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
        tnt.getLocation().getNearbyLivingEntities(config.radius.value()).forEach(x -> {
            EntityEquipment slot = x.getEquipment();
            if (slot != null) {
                slot.setHelmet(koutanHead);
            }
        });

        int radius = config.radius.value();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Material material = koutanFace.get((x + radius) % 8).get((z + radius) % 8);
                Location location = tnt.getLocation().add(x, 0, z);
                int y = tnt.getWorld().getHighestBlockYAt(location);
                location.setY(y);
                location.getBlock().setType(material);
            }
        }
    }

    @Override
    public String tabCompleteName() {
        return "koutan";
    }
}
