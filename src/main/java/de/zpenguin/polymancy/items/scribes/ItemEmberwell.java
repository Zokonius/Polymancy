package de.zpenguin.polymancy.items.scribes;

import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.items.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import teamroots.embers.util.EmberInventoryUtil;
import thaumcraft.api.items.IScribeTools;

public class ItemEmberwell extends ItemBase implements IScribeTools {

	public ItemEmberwell(String name) {
		super(name);
		this.setMaxDamage(10);
		this.setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int slot, boolean isSelected) {
		if(PolyCompat.embers && stack.getItemDamage() > 0 && entity.ticksExisted % 5 == 0) {
			if(!(entity instanceof EntityPlayer))
				return;

			EntityPlayer player = (EntityPlayer) entity;
			if(EmberInventoryUtil.getEmberTotal(player) >= 5) {
				EmberInventoryUtil.removeEmber(player, 5);
				stack.setItemDamage(stack.getItemDamage() - 1);
			}
		}

	}

}
