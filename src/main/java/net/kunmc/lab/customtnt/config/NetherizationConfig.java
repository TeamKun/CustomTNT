package net.kunmc.lab.customtnt.config;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.StringValue;
import net.kunmc.lab.configlib.value.map.Enum2EnumMapValue;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class NetherizationConfig extends BaseConfig {
    public final DoubleValue radius = new DoubleValue(10.0);
    public final Enum2EnumMapValue<Material, Material> convertTable = new Enum2EnumMapValue<>(Material.class, Material.class)
            .listable(false);
    public final IntegerValue replacePerTick = new IntegerValue(300);
    public final StringValue displayName = new StringValue("");

    public NetherizationConfig(@NotNull Plugin plugin) {
        super(plugin);

        convertTable.putAll(new HashMap<Material, Material>() {{
            put(Material.STONE, Material.NETHERRACK);
            put(Material.GRANITE, Material.NETHERRACK);
            put(Material.POLISHED_GRANITE, Material.NETHERRACK);
            put(Material.DIORITE, Material.NETHERRACK);
            put(Material.POLISHED_DIORITE, Material.NETHERRACK);
            put(Material.ANDESITE, Material.NETHERRACK);
            put(Material.POLISHED_ANDESITE, Material.NETHERRACK);
            put(Material.GRASS_BLOCK, Material.CRIMSON_NYLIUM);
            put(Material.DIRT, Material.CRIMSON_NYLIUM);
            put(Material.COARSE_DIRT, Material.CRIMSON_NYLIUM);
            put(Material.PODZOL, Material.CRIMSON_NYLIUM);
            put(Material.COBBLESTONE, Material.NETHERRACK);
            put(Material.OAK_PLANKS, Material.WARPED_PLANKS);
            put(Material.SPRUCE_PLANKS, Material.WARPED_PLANKS);
            put(Material.BIRCH_PLANKS, Material.WARPED_PLANKS);
            put(Material.JUNGLE_PLANKS, Material.CRIMSON_PLANKS);
            put(Material.ACACIA_PLANKS, Material.CRIMSON_PLANKS);
            put(Material.DARK_OAK_PLANKS, Material.CRIMSON_PLANKS);
            put(Material.SAND, Material.SOUL_SAND);
            put(Material.RED_SAND, Material.SOUL_SAND);
            put(Material.GOLD_ORE, Material.NETHER_GOLD_ORE);
            put(Material.COAL_ORE, Material.NETHER_QUARTZ_ORE);
            put(Material.OAK_LOG, Material.WARPED_STEM);
            put(Material.SPRUCE_LOG, Material.WARPED_STEM);
            put(Material.BIRCH_LOG, Material.WARPED_STEM);
            put(Material.JUNGLE_LOG, Material.CRIMSON_STEM);
            put(Material.ACACIA_LOG, Material.CRIMSON_STEM);
            put(Material.DARK_OAK_LOG, Material.CRIMSON_STEM);
            put(Material.STRIPPED_OAK_LOG, Material.STRIPPED_WARPED_STEM);
            put(Material.STRIPPED_SPRUCE_LOG, Material.STRIPPED_WARPED_STEM);
            put(Material.STRIPPED_BIRCH_LOG, Material.STRIPPED_WARPED_STEM);
            put(Material.STRIPPED_JUNGLE_LOG, Material.STRIPPED_CRIMSON_STEM);
            put(Material.STRIPPED_ACACIA_LOG, Material.STRIPPED_CRIMSON_STEM);
            put(Material.STRIPPED_DARK_OAK_LOG, Material.STRIPPED_CRIMSON_STEM);
            put(Material.STRIPPED_CRIMSON_STEM, Material.STRIPPED_ACACIA_LOG);
            put(Material.STRIPPED_WARPED_STEM, Material.STRIPPED_OAK_LOG);
            put(Material.STRIPPED_OAK_WOOD, Material.STRIPPED_WARPED_HYPHAE);
            put(Material.STRIPPED_SPRUCE_WOOD, Material.STRIPPED_WARPED_HYPHAE);
            put(Material.STRIPPED_BIRCH_WOOD, Material.STRIPPED_WARPED_HYPHAE);
            put(Material.STRIPPED_JUNGLE_WOOD, Material.STRIPPED_CRIMSON_HYPHAE);
            put(Material.STRIPPED_ACACIA_WOOD, Material.STRIPPED_CRIMSON_HYPHAE);
            put(Material.STRIPPED_DARK_OAK_WOOD, Material.STRIPPED_CRIMSON_HYPHAE);
            put(Material.STRIPPED_CRIMSON_HYPHAE, Material.STRIPPED_ACACIA_WOOD);
            put(Material.STRIPPED_WARPED_HYPHAE, Material.STRIPPED_OAK_WOOD);
            put(Material.OAK_WOOD, Material.WARPED_HYPHAE);
            put(Material.SPRUCE_WOOD, Material.WARPED_HYPHAE);
            put(Material.BIRCH_WOOD, Material.WARPED_HYPHAE);
            put(Material.JUNGLE_WOOD, Material.CRIMSON_HYPHAE);
            put(Material.ACACIA_WOOD, Material.CRIMSON_HYPHAE);
            put(Material.DARK_OAK_WOOD, Material.CRIMSON_HYPHAE);
            put(Material.OAK_LEAVES, Material.WARPED_WART_BLOCK);
            put(Material.SPRUCE_LEAVES, Material.WARPED_WART_BLOCK);
            put(Material.BIRCH_LEAVES, Material.WARPED_WART_BLOCK);
            put(Material.JUNGLE_LEAVES, Material.NETHER_WART_BLOCK);
            put(Material.ACACIA_LEAVES, Material.NETHER_WART_BLOCK);
            put(Material.DARK_OAK_LEAVES, Material.NETHER_WART_BLOCK);
            put(Material.SANDSTONE, Material.SOUL_SAND);
            put(Material.CHISELED_SANDSTONE, Material.SOUL_SAND);
            put(Material.CUT_SANDSTONE, Material.SOUL_SAND);
            put(Material.OAK_SLAB, Material.WARPED_SLAB);
            put(Material.SPRUCE_SLAB, Material.WARPED_SLAB);
            put(Material.BIRCH_SLAB, Material.WARPED_SLAB);
            put(Material.JUNGLE_SLAB, Material.CRIMSON_SLAB);
            put(Material.ACACIA_SLAB, Material.CRIMSON_SLAB);
            put(Material.DARK_OAK_SLAB, Material.CRIMSON_SLAB);
            put(Material.STONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.SMOOTH_STONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.SANDSTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.CUT_SANDSTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.PETRIFIED_OAK_SLAB, Material.WARPED_SLAB);
            put(Material.COBBLESTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.BRICK_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.STONE_BRICK_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.QUARTZ_SLAB, Material.PURPUR_SLAB);
            put(Material.RED_SANDSTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.CUT_RED_SANDSTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.PURPUR_SLAB, Material.QUARTZ_SLAB);
            put(Material.PRISMARINE_SLAB, Material.QUARTZ_SLAB);
            put(Material.PRISMARINE_BRICK_SLAB, Material.QUARTZ_SLAB);
            put(Material.DARK_PRISMARINE_SLAB, Material.QUARTZ_SLAB);
            put(Material.SMOOTH_QUARTZ, Material.SMOOTH_STONE);
            put(Material.SMOOTH_RED_SANDSTONE, Material.SMOOTH_QUARTZ);
            put(Material.SMOOTH_SANDSTONE, Material.NETHERRACK);
            put(Material.SMOOTH_STONE, Material.SMOOTH_QUARTZ);
            put(Material.BRICKS, Material.POLISHED_BLACKSTONE_BRICKS);
            put(Material.MOSSY_COBBLESTONE, Material.NETHERRACK);
            put(Material.OAK_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.FARMLAND, Material.CRIMSON_NYLIUM);
            put(Material.COBBLESTONE_STAIRS, Material.POLISHED_BLACKSTONE_STAIRS);
            put(Material.STONE_PRESSURE_PLATE, Material.POLISHED_BLACKSTONE_PRESSURE_PLATE);
            put(Material.OAK_PRESSURE_PLATE, Material.WARPED_PRESSURE_PLATE);
            put(Material.SPRUCE_PRESSURE_PLATE, Material.WARPED_PRESSURE_PLATE);
            put(Material.BIRCH_PRESSURE_PLATE, Material.WARPED_PRESSURE_PLATE);
            put(Material.JUNGLE_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.ACACIA_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.DARK_OAK_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.SNOW, Material.NETHERRACK);
            put(Material.ICE, Material.NETHERRACK);
            put(Material.SNOW_BLOCK, Material.NETHERRACK);
            put(Material.CLAY, Material.NETHERRACK);
            put(Material.OAK_FENCE, Material.WARPED_FENCE);
            put(Material.SPRUCE_FENCE, Material.WARPED_FENCE);
            put(Material.BIRCH_FENCE, Material.WARPED_FENCE);
            put(Material.JUNGLE_FENCE, Material.CRIMSON_FENCE);
            put(Material.ACACIA_FENCE, Material.CRIMSON_FENCE);
            put(Material.DARK_OAK_FENCE, Material.CRIMSON_FENCE);
            put(Material.SOUL_SAND, Material.SAND);
            put(Material.SOUL_SOIL, Material.DIRT);
            put(Material.BASALT, Material.DIORITE);
            put(Material.POLISHED_BASALT, Material.POLISHED_DIORITE);
            put(Material.TORCH, Material.SOUL_TORCH);
            put(Material.SOUL_TORCH, Material.TORCH);
            put(Material.LAPIS_ORE, Material.GLOWSTONE);
            put(Material.GLOWSTONE, Material.LAPIS_ORE);
            put(Material.OAK_TRAPDOOR, Material.WARPED_TRAPDOOR);
            put(Material.SPRUCE_TRAPDOOR, Material.WARPED_TRAPDOOR);
            put(Material.BIRCH_TRAPDOOR, Material.WARPED_TRAPDOOR);
            put(Material.JUNGLE_TRAPDOOR, Material.CRIMSON_TRAPDOOR);
            put(Material.ACACIA_TRAPDOOR, Material.CRIMSON_TRAPDOOR);
            put(Material.DARK_OAK_TRAPDOOR, Material.CRIMSON_TRAPDOOR);
            put(Material.INFESTED_STONE, Material.NETHERRACK);
            put(Material.INFESTED_COBBLESTONE, Material.NETHERRACK);
            put(Material.INFESTED_STONE_BRICKS, Material.NETHERRACK);
            put(Material.INFESTED_MOSSY_STONE_BRICKS, Material.NETHERRACK);
            put(Material.INFESTED_CRACKED_STONE_BRICKS, Material.NETHERRACK);
            put(Material.INFESTED_CHISELED_STONE_BRICKS, Material.NETHERRACK);
            put(Material.STONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICKS);
            put(Material.MOSSY_STONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICKS);
            put(Material.CRACKED_STONE_BRICKS, Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
            put(Material.CHISELED_STONE_BRICKS, Material.CHISELED_POLISHED_BLACKSTONE);
            put(Material.BROWN_MUSHROOM_BLOCK, Material.NETHERRACK);
            put(Material.RED_MUSHROOM_BLOCK, Material.NETHERRACK);
            put(Material.MUSHROOM_STEM, Material.NETHERRACK);
            put(Material.MELON, Material.NETHERRACK);
            put(Material.VINE, Material.WEEPING_VINES_PLANT);
            put(Material.OAK_FENCE_GATE, Material.WARPED_FENCE_GATE);
            put(Material.SPRUCE_FENCE_GATE, Material.WARPED_FENCE_GATE);
            put(Material.BIRCH_FENCE_GATE, Material.WARPED_FENCE_GATE);
            put(Material.JUNGLE_FENCE_GATE, Material.CRIMSON_FENCE_GATE);
            put(Material.ACACIA_FENCE_GATE, Material.CRIMSON_FENCE_GATE);
            put(Material.DARK_OAK_FENCE_GATE, Material.CRIMSON_FENCE_GATE);
            put(Material.BRICK_STAIRS, Material.NETHER_BRICK_STAIRS);
            put(Material.STONE_BRICK_STAIRS, Material.NETHER_BRICK_STAIRS);
            put(Material.MYCELIUM, Material.NETHERRACK);
            put(Material.SANDSTONE_STAIRS, Material.NETHER_BRICK_STAIRS);
            put(Material.EMERALD_ORE, Material.NETHER_GOLD_ORE);
            put(Material.SPRUCE_STAIRS, Material.WARPED_STAIRS);
            put(Material.BIRCH_STAIRS, Material.WARPED_STAIRS);
            put(Material.ACACIA_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.DARK_OAK_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.JUNGLE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.COBBLESTONE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.MOSSY_COBBLESTONE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.BRICK_WALL, Material.NETHER_BRICK_WALL);
            put(Material.PRISMARINE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.RED_SANDSTONE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.MOSSY_STONE_BRICK_WALL, Material.NETHER_BRICK_WALL);
            put(Material.GRANITE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.STONE_BRICK_WALL, Material.NETHER_BRICK_WALL);
            put(Material.ANDESITE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.RED_NETHER_BRICK_WALL, Material.NETHER_BRICK_WALL);
            put(Material.SANDSTONE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.DIORITE_WALL, Material.NETHER_BRICK_WALL);
            put(Material.STONE_BUTTON, Material.POLISHED_BLACKSTONE_BUTTON);
            put(Material.OAK_BUTTON, Material.WARPED_BUTTON);
            put(Material.SPRUCE_BUTTON, Material.WARPED_BUTTON);
            put(Material.BIRCH_BUTTON, Material.WARPED_BUTTON);
            put(Material.JUNGLE_BUTTON, Material.CRIMSON_BUTTON);
            put(Material.ACACIA_BUTTON, Material.CRIMSON_BUTTON);
            put(Material.DARK_OAK_BUTTON, Material.CRIMSON_BUTTON);
            put(Material.POLISHED_BLACKSTONE_BUTTON, Material.STONE_BUTTON);
            put(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.WARPED_PRESSURE_PLATE);
            put(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.WARPED_PRESSURE_PLATE);
            put(Material.CHISELED_QUARTZ_BLOCK, Material.CHISELED_STONE_BRICKS);
            put(Material.WHITE_TERRACOTTA, Material.NETHERRACK);
            put(Material.ORANGE_TERRACOTTA, Material.NETHERRACK);
            put(Material.MAGENTA_TERRACOTTA, Material.NETHERRACK);
            put(Material.LIGHT_BLUE_TERRACOTTA, Material.NETHERRACK);
            put(Material.YELLOW_TERRACOTTA, Material.NETHERRACK);
            put(Material.LIME_TERRACOTTA, Material.NETHERRACK);
            put(Material.PINK_TERRACOTTA, Material.NETHERRACK);
            put(Material.GRAY_TERRACOTTA, Material.NETHERRACK);
            put(Material.LIGHT_GRAY_TERRACOTTA, Material.NETHERRACK);
            put(Material.CYAN_TERRACOTTA, Material.NETHERRACK);
            put(Material.PURPLE_TERRACOTTA, Material.NETHERRACK);
            put(Material.BLUE_TERRACOTTA, Material.NETHERRACK);
            put(Material.BROWN_TERRACOTTA, Material.NETHERRACK);
            put(Material.GREEN_TERRACOTTA, Material.NETHERRACK);
            put(Material.RED_TERRACOTTA, Material.NETHERRACK);
            put(Material.BLACK_TERRACOTTA, Material.NETHERRACK);
            put(Material.IRON_TRAPDOOR, Material.CRIMSON_TRAPDOOR);
            put(Material.HAY_BLOCK, Material.NETHERRACK);
            put(Material.WHITE_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.ORANGE_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.MAGENTA_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.LIGHT_BLUE_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.YELLOW_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.LIME_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.PINK_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.GRAY_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.LIGHT_GRAY_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.CYAN_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.PURPLE_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.BLUE_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.BROWN_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.GREEN_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.RED_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.BLACK_CARPET, Material.CRIMSON_PRESSURE_PLATE);
            put(Material.TERRACOTTA, Material.NETHERRACK);
            put(Material.COAL_BLOCK, Material.QUARTZ_BLOCK);
            put(Material.PACKED_ICE, Material.NETHERRACK);
            put(Material.GRASS_PATH, Material.SOUL_SAND);
            put(Material.PRISMARINE, Material.NETHERRACK);
            put(Material.PRISMARINE_BRICKS, Material.NETHERRACK);
            put(Material.DARK_PRISMARINE, Material.NETHERRACK);
            put(Material.PRISMARINE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.PRISMARINE_BRICK_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.DARK_PRISMARINE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.RED_SANDSTONE, Material.SOUL_SAND);
            put(Material.CHISELED_RED_SANDSTONE, Material.SOUL_SAND);
            put(Material.CUT_RED_SANDSTONE, Material.SOUL_SAND);
            put(Material.RED_SANDSTONE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.RED_NETHER_BRICKS, Material.BRICKS);
            put(Material.BONE_BLOCK, Material.SNOW_BLOCK);
            put(Material.WHITE_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.ORANGE_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.MAGENTA_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.YELLOW_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.LIME_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.PINK_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.GRAY_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.CYAN_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.PURPLE_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.BLUE_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.BROWN_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.GREEN_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.RED_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.BLACK_GLAZED_TERRACOTTA, Material.NETHERRACK);
            put(Material.WHITE_CONCRETE, Material.NETHERRACK);
            put(Material.ORANGE_CONCRETE, Material.NETHERRACK);
            put(Material.MAGENTA_CONCRETE, Material.NETHERRACK);
            put(Material.LIGHT_BLUE_CONCRETE, Material.NETHERRACK);
            put(Material.YELLOW_CONCRETE, Material.NETHERRACK);
            put(Material.LIME_CONCRETE, Material.NETHERRACK);
            put(Material.PINK_CONCRETE, Material.NETHERRACK);
            put(Material.GRAY_CONCRETE, Material.NETHERRACK);
            put(Material.LIGHT_GRAY_CONCRETE, Material.NETHERRACK);
            put(Material.CYAN_CONCRETE, Material.NETHERRACK);
            put(Material.PURPLE_CONCRETE, Material.NETHERRACK);
            put(Material.BLUE_CONCRETE, Material.NETHERRACK);
            put(Material.BROWN_CONCRETE, Material.NETHERRACK);
            put(Material.GREEN_CONCRETE, Material.NETHERRACK);
            put(Material.RED_CONCRETE, Material.NETHERRACK);
            put(Material.BLACK_CONCRETE, Material.NETHERRACK);
            put(Material.WHITE_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.ORANGE_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.MAGENTA_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.LIGHT_BLUE_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.YELLOW_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.LIME_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.PINK_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.GRAY_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.LIGHT_GRAY_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.CYAN_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.PURPLE_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.BLUE_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.BROWN_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.GREEN_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.RED_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.BLACK_CONCRETE_POWDER, Material.NETHERRACK);
            put(Material.BLUE_ICE, Material.NETHERRACK);
            put(Material.POLISHED_GRANITE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.SMOOTH_RED_SANDSTONE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.MOSSY_STONE_BRICK_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.POLISHED_DIORITE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.MOSSY_COBBLESTONE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.STONE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.SMOOTH_SANDSTONE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.SMOOTH_QUARTZ_STAIRS, Material.STONE_STAIRS);
            put(Material.GRANITE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.ANDESITE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.RED_NETHER_BRICK_STAIRS, Material.STONE_STAIRS);
            put(Material.POLISHED_ANDESITE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.DIORITE_STAIRS, Material.CRIMSON_STAIRS);
            put(Material.POLISHED_GRANITE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.SMOOTH_RED_SANDSTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.MOSSY_STONE_BRICK_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.POLISHED_DIORITE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.MOSSY_COBBLESTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.SMOOTH_SANDSTONE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.SMOOTH_QUARTZ_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.GRANITE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.ANDESITE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.RED_NETHER_BRICK_SLAB, Material.STONE_BRICK_SLAB);
            put(Material.POLISHED_ANDESITE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.DIORITE_SLAB, Material.NETHER_BRICK_SLAB);
            put(Material.OAK_SIGN, Material.WARPED_SIGN);
            put(Material.SPRUCE_SIGN, Material.WARPED_SIGN);
            put(Material.BIRCH_SIGN, Material.WARPED_SIGN);
            put(Material.JUNGLE_SIGN, Material.CRIMSON_SIGN);
            put(Material.ACACIA_SIGN, Material.CRIMSON_SIGN);
            put(Material.DARK_OAK_SIGN, Material.CRIMSON_SIGN);
            put(Material.DRIED_KELP_BLOCK, Material.NETHERRACK);
            put(Material.SKELETON_SKULL, Material.WITHER_SKELETON_SKULL);
            put(Material.WITHER_SKELETON_SKULL, Material.SKELETON_SKULL);
            put(Material.BELL, Material.NETHERRACK);
            put(Material.LANTERN, Material.SOUL_LANTERN);
            put(Material.SOUL_LANTERN, Material.LANTERN);
            put(Material.CAMPFIRE, Material.SOUL_CAMPFIRE);
            put(Material.SOUL_CAMPFIRE, Material.CAMPFIRE);
            put(Material.SHROOMLIGHT, Material.NETHERITE_BLOCK);
            put(Material.BEE_NEST, Material.NETHERRACK);
            put(Material.BEEHIVE, Material.NETHERRACK);
            put(Material.HONEY_BLOCK, Material.NETHERRACK);
            put(Material.HONEYCOMB_BLOCK, Material.NETHERRACK);
            put(Material.ANCIENT_DEBRIS, Material.NETHERRACK);
            put(Material.WATER, Material.LAVA);
            put(Material.WALL_TORCH, Material.SOUL_WALL_TORCH);
            put(Material.SOUL_WALL_TORCH, Material.WALL_TORCH);
            put(Material.SKELETON_WALL_SKULL, Material.WITHER_SKELETON_WALL_SKULL);
            put(Material.WITHER_SKELETON_WALL_SKULL, Material.SKELETON_WALL_SKULL);
            put(Material.FROSTED_ICE, Material.NETHERRACK);
            put(Material.SEAGRASS, Material.LAVA);
            put(Material.TALL_SEAGRASS, Material.LAVA);
            put(Material.KELP, Material.LAVA);
            put(Material.KELP_PLANT, Material.LAVA);
            put(Material.END_STONE, Material.WATER);
        }});
    }
}
