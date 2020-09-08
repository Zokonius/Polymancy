package de.zpenguin.polymancy.main;

import de.zpenguin.polymancy.items.PolyItems;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Polymancy.modID)
public class Registrar {

	@SubscribeEvent
	public static void registerItems(Register<Item> r) {
		PolyItems.registerItems(r);
	}

	@SubscribeEvent
	public static void registerRecipes(Register<IRecipe> r) {
		PolyRecipes.registerRecipes(r);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e) {
		PolyItems.registerRenders(e);
	}

}
