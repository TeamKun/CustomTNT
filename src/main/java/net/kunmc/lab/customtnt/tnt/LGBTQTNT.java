package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.LGBTQConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LGBTQTNT extends CustomTNT {
    private final LGBTQConfig config;
    private final List<Material> colorMaterials = new ArrayList<Material>() {{
        add(Material.PINK_WOOL);
        add(Material.RED_WOOL);
        add(Material.ORANGE_WOOL);
        add(Material.YELLOW_WOOL);
        add(Material.GREEN_WOOL);
        add(Material.LIGHT_BLUE_WOOL);
        add(Material.PURPLE_WOOL);
        add(Material.MAGENTA_WOOL);
    }};

    public LGBTQTNT(Plugin plugin, LGBTQConfig config) {
        super(plugin);
        this.config = config;
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
        int radius = config.radius.value();
        int n = 0;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z < radius; z++) {
                Block b = tnt.getLocation().add(x, 0, z).toHighestLocation().getBlock();
                b.setType(colorMaterials.get(n % colorMaterials.size()));
            }

            n++;
        }
    }

    @Override
    public String tabCompleteName() {
        return "lgbtq";
    }
}
