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
import net.kunmc.lab.customtnt.config.MickeyConfig;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;

public class MickeyTNT extends CustomTNT {
    private final MickeyConfig config;

    public MickeyTNT(Plugin plugin, MickeyConfig config) {
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
        tnt.getWorld().playSound(tnt.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(tnt.getWorld()))) {
            InputStream mickeySchem = plugin.getResource("mickey.schem");
            ClipboardReader reader = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getReader(mickeySchem);
            ClipboardHolder clipboardHolder = new ClipboardHolder(reader.read());

            Location loc = tnt.getLocation();
            Operations.complete(clipboardHolder.createPaste(editSession)
                    .to(BlockVector3.at(loc.getX() + 26, loc.getY() + 40, loc.getZ()))
                    .build());
        } catch (IOException | WorldEditException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String tabCompleteName() {
        return "mickey";
    }
}
