package de.zpenguin.polymancy.wands.update;

import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.main.ConfigHandler;
import de.zpenguin.polymancy.wands.PolyWands;
import de.zpenguin.thaumicwands.api.item.wand.IWand;
import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.RechargeHelper;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManaUpdate implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack wand, EntityPlayer player) {
		if(PolyCompat.botania && ConfigHandler.crossWand && player.ticksExisted % 20 == 0 && inHotbar(wand, player)) {
			int cost = ConfigHandler.manaVis;
			IWandCap cap = ((IWand)wand.getItem()).getCap(wand);
			if(cap == PolyWands.capElementium || cap == PolyWands.capManasteel)
				cost -= 200;

			cost = Math.max(cost, 0);

			if(RechargeHelper.getChargePercentage(wand, player) < 1F)
				if(ManaItemHandler.requestManaExact(wand, player, cost, true))
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
