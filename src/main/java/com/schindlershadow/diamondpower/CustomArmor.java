package com.schindlershadow.diamondpower;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemArmor;

public class CustomArmor extends ItemArmor {

	public CustomArmor(ArmorMaterial material, int par2, int par3) {
		super(material, par2, par3);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		String itemName = getUnlocalizedName().substring(
				getUnlocalizedName().lastIndexOf(".") + 1);
		this.itemIcon = par1IconRegister.registerIcon(itemName);
	}

}
