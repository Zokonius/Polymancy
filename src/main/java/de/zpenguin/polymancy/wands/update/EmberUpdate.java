package de.zpenguin.polymancy.wands.update;

import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.main.ConfigHandler;
import de.zpenguin.polymancy.wands.PolyWands;
import de.zpenguin.thaumicwands.api.item.wand.IWand;
import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import teamroots.embers.util.EmberInventoryUtil;
import thaumcraft.api.items.RechargeHelper;

public class EmberUpdate implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack wand, EntityPlayer player) {
		if(PolyCompat.embers && ConfigHandler.crossWand && player.ticksExisted % 20 == 0 && inHotbar(wand, player)) {
			int cost = ConfigHandler.emberVis;
			IWandCap cap = ((IWand)wand.getItem()).getCap(wand);
			if(cap == PolyWands.capDawnstone)
				cost -= 2;

			cost = Math.max(cost, 0);

			if(RechargeHelper.getChargePercentage(wand, player) < 1F)
				if(EmberInventoryUtil.getEmberTotal(player) >= cost) {
					EmberInventoryUtil.removeEmber(player, cost);
					RechargeHelper.rechargeItemBlindly(wand, player, 1);
				}
		}
	}

	private boolean inHotbar(ItemStack wand, EntityPlayer player) {
		for(int i=0; i!=9;i++)
			if(player.inventory.getStackInSlot(i).equals(wand))
				return true;
		if(player.getHeldItemOffhand().equals(wand))
			return true;

		return false;
	}



}
