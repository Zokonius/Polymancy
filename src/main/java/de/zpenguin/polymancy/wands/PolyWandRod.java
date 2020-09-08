package de.zpenguin.polymancy.wands;

import de.zpenguin.polymancy.main.Polymancy;
import de.zpenguin.thaumicwands.api.item.wand.IWandRod;
import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PolyWandRod implements IWandRod {

	int craftCost;
	int capacity;

	String tag;
	String research;
	ItemStack item;
	IWandUpdate update;


	public PolyWandRod(String tag, int capacity, ItemStack item, int craftCost) {
		this(tag, capacity, item, craftCost, null, "ROD_" + tag.toUpperCase());
	}

	public PolyWandRod(String tag, int capacity, ItemStack item, int craftCost, IWandUpdate update) {
		this(tag, capacity, item, craftCost, update, "ROD_" + tag.toUpperCase());
	}

	public PolyWandRod(String tag, int capacity, ItemStack item, int craftCost, String research) {
		this(tag, capacity, item, craftCost, null, research);
	}

	public PolyWandRod(String tag, int capacity, ItemStack item, int craftCost, IWandUpdate update, String research) {
		this.tag = tag;
		this.capacity = capacity;
		this.item = item;
		this.craftCost = craftCost;
		this.update = update;
		this.research = research;
		PolyWands.rods.add(this);

	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Polymancy.modID, "textures/models/wand_rod_" + tag + ".png");
	}

	@Override
	public ItemStack getItemStack() {
		return item;
	}

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public String getRequiredResearch() {
		return research;
	}

	@Override
	public int getCraftCost() {
		return craftCost;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public IWandUpdate getUpdate() {
		return update;
	}

}
