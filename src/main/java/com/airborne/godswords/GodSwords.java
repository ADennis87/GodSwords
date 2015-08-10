package com.airborne.godswords;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

import com.airborne.godswords.client.ClientProxy;

@Mod(modid = GodSwords.modid, name = "God Swords", version = GodSwords.version)

public class GodSwords {

	@Instance(GodSwords.modid)
	public static GodSwords instance;
	
	@SidedProxy(clientSide="com.airborne.godswords.client.ClientProxy", serverSide="com.airborne.godswords.CommonProxy")
	public static CommonProxy proxy;
	
	public static final String modid = "godswords";
	
	public static final String version = "1.0";
	
	public static CreativeTabs tabGodSwords = new CreativeTabGodSwords(CreativeTabs.getNextID(),"tabGodSwords");
	
	//public static Logger GsLogger = Logger.getLogger("God Swords");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){		
		//GsLogger.setParent(FMLLog.getLogger());
		
		Registry.preInit();
	}
	@EventHandler
	public void Init(FMLInitializationEvent event){
		proxy.registerRenderThings();
		MinecraftForge.EVENT_BUS.register(new EventHandlerGS());
		FMLCommonHandler.instance().bus().register(new EventHandlerGS());
		Registry.Init();
	}
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event){		
		Registry.postInit();
	}
}
