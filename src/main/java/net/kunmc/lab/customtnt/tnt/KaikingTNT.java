package net.kunmc.lab.customtnt.tnt;

import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.config.KaikingConfig;
import net.kyori.adventure.text.Component;
import net.minecraft.server.v1_16_R3.EntityPigZombie;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPigZombie;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class KaikingTNT extends CustomTNT {
    private final KaikingConfig config;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public KaikingTNT(Plugin plugin, KaikingConfig config) {
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
        for (int i = 0; i < config.quantity.value(); i++) {
            tnt.getWorld().spawnEntity(tnt.getLocation(), EntityType.ZOMBIFIED_PIGLIN, CreatureSpawnEvent.SpawnReason.CUSTOM, entity -> {
                PigZombie pigZombie = ((PigZombie) entity);
                pigZombie.setAngry(true);
                pigZombie.setAnger(Integer.MAX_VALUE);
                pigZombie.setSilent(true);
                pigZombie.registerAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                pigZombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
                pigZombie.registerAttribute(Attribute.GENERIC_FOLLOW_RANGE);
                pigZombie.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(128);
                pigZombie.customName(Component.text("Kaiking"));
                pigZombie.clearLootTable();
                pigZombie.setInvisible(true);
                EntityPigZombie entityPigZombie = ((CraftPigZombie) pigZombie).getHandle();
                entityPigZombie.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)));
                entityPigZombie.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(getKaikingFaceRandomly()));

                Runnable setTarget = () -> {
                    Bukkit.getOnlinePlayers().stream()
                            .filter(x -> x.getGameMode() != GameMode.CREATIVE)
                            .filter(x -> x.getGameMode() != GameMode.SPECTATOR)
                            .min(Comparator.comparingDouble(x -> x.getLocation().distance(pigZombie.getLocation())))
                            .ifPresent(pigZombie::setTarget);
                };
                setTarget.run();

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (pigZombie.isDead()) {
                            cancel();
                            return;
                        }
                        pigZombie.getWorld().playSound(pigZombie.getLocation(), "kaiking", config.volume.value(), config.pitch.value());

                        if (random.nextDouble() < 0.3) {
                            setTarget.run();
                        }
                    }
                }.runTaskTimer(plugin, random.nextInt(200), 400);
            });
        }
    }

    private ItemStack getKaikingFaceRandomly() {
        ItemStack item;
        if (random.nextDouble() < 0.5) {
            item = new ItemStack(Material.WHITE_DYE);
        } else {
            item = new ItemStack(Material.BLACK_DYE);
        }

        item.editMeta(x -> {
            x.setCustomModelData(256);
        });
        return item;
    }

    @Override
    public String tabCompleteName() {
        return "kaiking";
    }
}
