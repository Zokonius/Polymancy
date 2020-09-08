package de.zpenguin.polymancy.wands.update;

import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldProviderEnd;
import thaumcraft.api.items.RechargeHelper;

public class ChorusUpdate implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack wand, EntityPlayer player) {
		if(player.ticksExisted % 100 == 0 && player.world.provider instanceof WorldProviderEnd)
			if(RechargeHelper.getChargePercentage(wand, player) < 0.2F)
				RechargeHelper.rechargeItemBlindly(wand, player, 1);

		if(player.isPotionActive(MobEffects.LEVITATION)) {
			player.removePotionEffect(MobEffects.LEVITATION);
		}

	}

}
