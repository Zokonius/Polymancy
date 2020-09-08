package de.zpenguin.polymancy.items;

import java.util.ArrayList;

import de.zpenguin.polymancy.items.scribes.ItemPrimewell;
import de.zpenguin.polymancy.main.Polymancy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;

public class PolyItems {

	public static final ArrayList<Item> ITEMS = new ArrayList<>();


	public static final String[] caps = {
			"manasteel_inert",
			"manasteel",
			"elementium_inert",
			"elementium",
			"terrasteel",
			"dawnstone_inert",
			"dawnstone",
			"alchemical",

	};

	public static final String[] rods = {
			"infernal",
			"chorus",
			"livingwood_inert",
			"livingwood",
			"dreamwood_inert",
			"dreamwood",
			"archaic_inert",
			"archaic",
			"blood_inert",
			"blood",

	};

	public static Item itemWandCap = new ItemBaseMeta("item_wand_cap", caps);
	public static Item itemWandRod = new ItemBaseMeta("item_wand_rod", rods);

	public static Item itemPrimewell = new ItemPrimewell("item_primewell");

	public static void registerItems(Register<Item> r) {
		ITEMS.forEach((it) -> registerItem(r, it));
	}

	public static void registerRenders(ModelRegistryEvent e) {
		ITEMS.forEach((it) -> registerRender(it));
	}

	private static void registerItem(Register<Item> r, Item it) {
		r.getRegistry().register(it);
	}

	private static void registerRender(Item item) {

		if(item instanceof ItemBaseMeta) {
			ItemBaseMeta it = (ItemBaseMeta) item;
			for(int i=0; i!=it.getVariants().length; i++)
				ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(Polymancy.modID + ":items/" + it.getBaseName(), it.getVariants()[i]));
		}

		else
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}

}
