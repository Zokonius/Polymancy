package de.zpenguin.polymancy.wands.update;

import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.main.ConfigHandler;
import de.zpenguin.polymancy.wands.PolyWands;
import de.zpenguin.thaumicwands.api.item.wand.IWand;
import de.zpenguin.thaumicwands.api.item.wand.IWandCap;
import de.zpenguin.thaumicwands.api.item.wand.IWandUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.RechargeHelper;

public class BloodUpdate implements IWandUpdate {

	@Override
	public void onUpdate(ItemStack wand, EntityPlayer player) {
		if(PolyCompat.bloodMagic && ConfigHandler.crossWand && player.ticksExisted % 20 == 0) {

			int cost = ConfigHandler.bloodVis;
			IWandCap cap = ((IWand)wand.getItem()).getCap(wand);
			if(cap == PolyWands.capAlchemical)
				cost -= 200;

			cost = Math.max(cost, 0);

			if(RechargeHelper.getChargePercentage(wand, player) < 1F) {
				if(NetworkHelper.getSoulNetwork(player).syphon(SoulTicket.item(wand, player.world, player, cost)) != 0)
					RechargeHelper.rechargeItemBlindly(wand, player, 1);
				else if(player.getHealth() >= 6 && NetworkHelper.getSoulNetwork(player).syphonAndDamage(player, SoulTicket.item(wand, player.world, player, cost)).isSuccess())
					RechargeHelper.rechargeItemBlindly(wand, player, 1);
				}
		}
	}

}
