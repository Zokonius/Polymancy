package de.zpenguin.polymancy.wands;

import de.zpenguin.polymancy.main.Polymancy;
import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;

public class PolyWandCap implements IWandCap {

	int craftCost;
	float discount;

	String tag;
	String research;
	AspectList aspectDiscount;
	ResourceLocation texture;
	ItemStack item;

	public PolyWandCap(String tag, float discount, ItemStack item, int craftCost, String research) {
		this(tag, discount, new AspectList(), item, craftCost, research);
	}

	public PolyWandCap(String tag, float discount, ItemStack item, int craftCost) {
		this(tag, discount, new AspectList(), item, craftCost, "CAP_" + tag.toUpperCase());
	}

	public PolyWandCap(String tag, float discount, AspectList aspectDiscount, ItemStack item, int craftCost) {
		this(tag, discount, aspectDiscount, item, craftCost, "CAP_" + tag.toUpperCase());
	}

	public PolyWandCap(String tag, float discount, AspectList aspectDiscount, ItemStack item, int craftCost, String research) {
		this.tag = tag;
		this.discount = discount;
		this.item = item;
		this.craftCost = craftCost;
		this.aspectDiscount = aspectDiscount;
		this.research = research;

		PolyWands.caps.add(this);

	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(Polymancy.modID, "textures/models/wand_cap_" + tag + ".png");
	}

	public int getCraftCost() {
		return craftCost;
	}

	@Override
	public String getRequiredResearch() {
		return research;
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
	public float getDiscount() {
		return discount;
	}

	@Override
	public AspectList getAspectDiscount() {
		return aspectDiscount;
	}

}
