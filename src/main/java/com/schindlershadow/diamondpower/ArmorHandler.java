package com.schindlershadow.diamondpower;

import java.util.List;

import cofh.api.energy.IEnergyStorage;
import cofh.lib.util.helpers.EnergyHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ISpecialArmor;

public class ArmorHandler extends ItemArmor implements IEnergyStorage, ISpecialArmor {
	@SideOnly(Side.CLIENT)
	protected IIcon Icon;
	protected IIcon texture;
	protected int energy = 0;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	protected int Tick = 0;
	protected int energyPerDamage = 1;
	protected double absorbRatio = 0.88D;
	
	
	//0 is Boots
    //1 is Legs
    //2 is Chest
    //3 is Helm

	public ArmorHandler(ArmorMaterial material, int par2, int par3, int capacity) {
		super(material, par2, par3);
		this.capacity = capacity;
		this.setMaxDamage(capacity);
		maxReceive = capacity;
		maxExtract = capacity;
		//energy = 100;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.epic;
	}


	public ArmorHandler readFromNBT(NBTTagCompound nbt) {

		this.energy = nbt.getInteger("Energy");

		if (energy > capacity) {
			energy = capacity;
		}
		return this;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		if (energy < 0) {
			energy = 0;
		}
		nbt.setInteger("Energy", energy);
		return nbt;
	}

	
	
	@SideOnly(Side.CLIENT)
	public java.lang.String getArmorTexture(ItemStack stack,
            Entity entity,
            int slot,
            java.lang.String type){
		String layer = "1";
		if(type == null) {
			type = "";
		}
		if(slot == 2) {
            layer="2";
		}
		return DiamondPower.MODID + ":textures/models/armor/diamond_layer_" + layer + ".png";
	}
	
	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		
		armor.setItemDamage(getCapacity() - getEnergyStored());
		if(player.isSneaking()){
			receiveEnergy(1,false);
		}
		//armor.setItemDamage(-getEnergyStored());
		
	}
	
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
    	list.add("Energy: " + energy + "/" + capacity + " RF");
    }
    
    protected int getBaseAbsorption() {

        return 20;
    }
    
    protected int getAbsorptionRatio() {

        switch (armorType) {
            case 0:
                return 15;
            case 1:
                return 40;
            case 2:
                return 30;
            case 3:
                return 15;
        }
        return 0;
    }
	
	public void setCapacity(int capacity) {

		this.capacity = capacity;

		if (energy > capacity) {
			energy = capacity;
		}
	}
	
	public int getCapacity(){
		return capacity; 
	}

	public void setMaxTransfer(int maxTransfer) {

		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive) {

		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract) {

		this.maxExtract = maxExtract;
	}

	public int getMaxReceive() {

		return maxReceive;
	}

	public int getMaxExtract() {

		return maxExtract;
	}

	/**
	 * This function is included to allow for server -> client sync. Do not call this externally to the containing Tile Entity, as not all IEnergyHandlers are
	 * guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void setEnergyStored(int energy) {

		this.energy = energy;

		if (this.energy > capacity) {
			this.energy = capacity;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/**
	 * This function is included to allow the containing tile to directly and efficiently modify the energy contained in the EnergyStorage. Do not rely on this
	 * externally, as not all IEnergyHandlers are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void modifyEnergyStored(int energy) {

		this.energy += energy;

		if (this.energy > capacity) {
			this.energy = capacity;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
	}
	
	/* IEnergyStorage */
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {

		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {

		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {

		return energy;
	}

	@Override
	public int getMaxEnergyStored() {

		return capacity;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, absorbRatio, 16);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		if (getEnergyStored() >= energyPerDamage) {
            return Math.min(getBaseAbsorption(), 20) * getAbsorptionRatio() / 100;
        }
        return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		extractEnergy(damage, false);
		
	}




	
		
		
		
	
 

}
