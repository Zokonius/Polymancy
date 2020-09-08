package de.zpenguin.polymancy.wands.update;

import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.main.ConfigHandler;
import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import moze_intel.projecte.gameObjs.items.ItemPE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.RechargeHelper;

public class EMCUpdate implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack wand, EntityPlayer player) {
		if(PolyCompat.projecte && ConfigHandler.crossWand && player.ticksExisted % 20 == 0 && inHotbar(wand, player)) {
			int cost = ConfigHandler.emcVis;
			cost = Math.max(cost, 0);

			if(RechargeHelper.getChargePercentage(wand, player) < 1F)
				if(ItemPE.consumeFuel(player, wand, cost, true))
					RechargeHelper.rechargeItemBlindly(wand, player, 1);
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
