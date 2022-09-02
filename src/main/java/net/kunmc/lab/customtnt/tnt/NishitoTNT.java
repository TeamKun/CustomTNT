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
import net.kunmc.lab.customtnt.config.NishitoConfig;
import org.bukkit.Location;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;

public class NishitoTNT extends CustomTNT {
    private final NishitoConfig config;

    public NishitoTNT(Plugin plugin, NishitoConfig config) {
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
        try (EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(tnt.getWorld()))) {
            InputStream nishitoSchem = plugin.getResource("nishito.schem");
            ClipboardReader reader = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getReader(nishitoSchem);
            ClipboardHolder clipboardHolder = new ClipboardHolder(reader.read());

            Location loc = tnt.getLocation();
            Operations.complete(clipboardHolder.createPaste(editSession)
                    .ignoreAirBlocks(true)
                    .to(BlockVector3.at(loc.getX(), loc.getY() + 10, loc.getZ()))
                    .build());
        } catch (IOException | WorldEditException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String tabCompleteName() {
        return "nishito";
    }
}
