package de.zpenguin.polymancy.wands.update;

import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldProviderHell;
import thaumcraft.api.items.RechargeHelper;

public class InfernalUpdate implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack wand, EntityPlayer player) {
		if(player.ticksExisted % 100 == 0 && player.world.provider instanceof WorldProviderHell && inHotbar(wand, player))
			if(RechargeHelper.getChargePercentage(wand, player) < 0.2F)
				RechargeHelper.rechargeItemBlindly(wand, player, 1);

		if(player.isBurning())
			player.extinguish();

		if(player.isPotionActive(MobEffects.WITHER))
			player.removePotionEffect(MobEffects.WITHER);

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
