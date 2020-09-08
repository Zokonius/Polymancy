package de.zpenguin.polymancy.main;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static Configuration config;

	public static boolean crossMod = true;
	public static boolean crossWand = true;

	public static boolean astral = true;
	public static boolean bot = true;
	public static boolean blood = true;
	public static boolean embers = true;
	public static boolean projecte = true;

	public static int bloodVis = 500;
	public static int emberVis = 7;
	public static int emcVis = 2000;
	public static int manaVis = 800;


	public static void loadConfig(File f) {
		config = new Configuration(f);

		config.load();
		load();

	}

	public static void load() {

       crossMod = config.get("compatibility", "Cross-Mod Interaction", crossMod, "Disable to keep mods segregated.").getBoolean(true);
       crossWand = config.get("compatibility", "Conversion Wands", crossWand, "Disable to remove all conversion wands.").getBoolean(true);

       astral = config.get("compatibility", "AstralSorcery Interaction", astral,"").getBoolean(true);
       bot = config.get("compatibility", "Botania Interaction", bot,"").getBoolean(true);
       blood = config.get("compatibility", "Blood Magic Interaction", blood,"").getBoolean(true);
       embers = config.get("compatibility", "Embers Interaction", embers,"").getBoolean(true);
       projecte = config.get("compatibility", "Project E Interaction", projecte,"").getBoolean(true);

       bloodVis = config.get("vis_converter", "LP to Vis", bloodVis, "How much 1 Vis is worth").getInt(bloodVis);
       emberVis = config.get("vis_converter", "Ember to Vis", emberVis, "How much 1 Vis is worth").getInt(emberVis);
       emcVis   = config.get("vis_converter", "EMC to Vis", emcVis, "How much 1 Vis is worth").getInt(emcVis);
       manaVis  = config.get("vis_converter", "Mana to Vis", manaVis,"How much 1 Vis is worth").getInt(manaVis);

       config.save();

	}

}
