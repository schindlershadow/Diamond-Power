package com.schindlershadow.diamondpower;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DiamondPower.MODID, version = DiamondPower.VERSION)
public class DiamondPower 
{
    public static final String MODID = "diamondpower";
    public static final String VERSION = "1.0";
    
    public static Item chest; 
    public static Item legs; 
    public static Item boots; 
    public static Item helmet;
    
    @Instance("DiamondPower")
    public static DiamondPower instance;
    
    public DiamondPower() {
		// TODO Auto-generated constructor stub
	}
    
    @SidedProxy(clientSide="com.schindlershadow.diamondpower.ClientProxy", serverSide="com.schindlershadow.diamondpower.CommonProxy")
    public static CommonProxy proxy; 
    
    @EventHandler 
    public void load(FMLInitializationEvent e) { } 
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	
    }
        
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	
    }
}
