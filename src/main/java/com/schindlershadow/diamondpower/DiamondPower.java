package com.schindlershadow.diamondpower;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DiamondPower.MODID, version = DiamondPower.VERSION)
public class DiamondPower 
{
    public static final String MODID = "diamondpower";
    public static final String VERSION = "1.0";
    
    @Instance("DiamondPower")
    public static DiamondPower instance;
    
    public DiamondPower() {
		// TODO Auto-generated constructor stub
	}
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	
    }
        
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	
    }
}
