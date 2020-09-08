package de.zpenguin.polymancy.compat;

import java.util.ArrayList;

import de.zpenguin.polymancy.compat.bloodmagic.BloodMagicCompat;
import de.zpenguin.polymancy.compat.botania.BotaniaCompat;
import de.zpenguin.polymancy.compat.embers.EmbersCompat;
import de.zpenguin.polymancy.main.ConfigHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;

public class PolyCompat {

	public static boolean botania = Loader.isModLoaded("botania") && ConfigHandler.bot;
	public static boolean embers = Loader.isModLoaded("embers") && ConfigHandler.embers;
	public static boolean bloodMagic = Loader.isModLoaded("bloodmagic") && ConfigHandler.blood;
	public static boolean astralSorcery = Loader.isModLoaded("astralsorcery") && ConfigHandler.astral;
	public static boolean projecte = Loader.isModLoaded("projecte") && ConfigHandler.projecte;

	private static ArrayList<IPolyCompat> compat = new ArrayList<>();

	public static void preInit() {
		if(botania)
			compat.add(new BotaniaCompat());
		if(bloodMagic)
			compat.add(new BloodMagicCompat());
		if(embers)
			compat.add(new EmbersCompat());

		for(IPolyCompat c: compat)
			c.preInit();
	}

	public static void initRecipes() {
		for(IPolyCompat c: compat)
			c.initRecipes();
	}

	public static void initResearch() {
		for(IPolyCompat c: compat)
			c.initResearch();
	}


    public static ItemStack getItem(String name, int meta) {
    	return getItem(name, 1, meta);
    }

	public static ItemStack getItem(String name, int amount, int meta) {
    	return GameRegistry.makeItemStack(name, meta, amount, null);
	}

    public static ResearchCategory getCategory(String cat) {
        return ResearchCategories.getResearchCategory(cat);
    }

	public static interface IPolyCompat {

		public void preInit();

		public void initRecipes();

		public void initResearch();

	}

}

