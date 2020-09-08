package de.zpenguin.polymancy.items.scribes;

import java.util.List;

import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.iface.IBindable;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.items.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import thaumcraft.api.items.IScribeTools;

public class ItemBloodwell extends ItemBase implements IScribeTools, IBindable {

	public ItemBloodwell(String name) {
		super(name);
		this.setMaxDamage(5);
		this.setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if(PolyCompat.bloodMagic && stack.getItemDamage() > 0 && stack.hasTagCompound()) {
			if(!(entity instanceof EntityPlayer))
				return;

			EntityPlayer player = (EntityPlayer) entity;

			if(getBinding(stack) != null && NetworkHelper.getSoulNetwork(getBinding(stack)).syphon(SoulTicket.item(stack, 25)) != 0)
				stack.setItemDamage(stack.getItemDamage()-1);
			else if(player.getHealth() >= 6 && NetworkHelper.getSoulNetwork(player).syphonAndDamage(player, SoulTicket.item(stack, player.world, player, 100)).isSuccess())
				stack.setItemDamage(stack.getItemDamage()-1);

		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(!stack.hasTagCompound())
			return;

		if(PolyCompat.bloodMagic) {
			Binding binding = getBinding(stack);
			if(binding != null)
				tooltip.add(new TextComponentTranslation("tooltip.bloodmagic.currentOwner", binding.getOwnerName()).getFormattedText());
		}

	}

	@Override
	public void setDamage(ItemStack stack, int damage) {
		if(PolyCompat.bloodMagic) {
			Binding binding = getBinding(stack);
			if(binding != null && damage > 0)
				if(NetworkHelper.getSoulNetwork(binding).syphon(SoulTicket.item(stack, 25)) != 0) {
					super.setDamage(stack, 0);
					return;
				}
		}

		super.setDamage(stack, damage);
	}

}
