package de.zpenguin.polymancy.main;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Polymancy.modID, name=Polymancy.modName, version=Polymancy.version, dependencies= Polymancy.dependencies)
public class Polymancy {

	public static final String modID = "polymancy";
	public static final String modName = "Polymancy";
	public static final String version = "1.0.1";
	public static final String dependencies = "required-after:thaumcraft;" +
											  "required-after:thaumicwands;"+
											  "after:botania;"+
											  "after:embers;" +
											  "after:bloodmagic";

	@Instance
	public static Polymancy instance;

	@SidedProxy(clientSide="de.zpenguin.polymancy.client.ClientProxy", serverSide="de.zpenguin.polymancy.main.CommonProxy")
	public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	ConfigHandler.loadConfig(e.getSuggestedConfigurationFile());
    	proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
    }

}
