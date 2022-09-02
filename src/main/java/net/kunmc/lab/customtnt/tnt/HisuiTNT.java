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
import net.kunmc.lab.customtnt.config.HisuiConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;

public class HisuiTNT extends CustomTNT {
    private final HisuiConfig config;

    public HisuiTNT(Plugin plugin, HisuiConfig config) {
        super(plugin);
        this.config = config;
    }

    @Override
    public String displayName() {
        return config.displayName.value();
    }

    @Override
    protected void onTNTPrimed(TNTPrimed tnt) {
        try (EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(tnt.getWorld()))) {
            InputStream hisuiSchem = plugin.getResource("hisui.schem");
            ClipboardReader reader = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getReader(hisuiSchem);
            ClipboardHolder clipboardHolder = new ClipboardHolder(reader.read());

            Location loc = tnt.getLocation();
            Operations.complete(clipboardHolder.createPaste(editSession)
                    .to(BlockVector3.at(loc.getX(), loc.getY(), loc.getZ() + 32))
                    .build());
        } catch (IOException | WorldEditException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onExplosionPrime(TNTPrimed tnt) {
        int radius = config.radius.value();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Location loc = tnt.getLocation().add(x, 0, z);
                int y = tnt.getWorld().getHighestBlockYAt(loc);
                loc.setY(y);

                if (!loc.getBlock().getType().name().endsWith("WOOL")) {
                    loc.getBlock().setType(Material.NETHERRACK);
                }
                Block b = loc.add(0, 1, 0).getBlock();
                b.setType(Material.FIRE);
            }
        }
    }

    @Override
    public String tabCompleteName() {
        return "hisui";
    }
}
