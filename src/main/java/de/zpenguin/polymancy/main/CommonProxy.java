package de.zpenguin.polymancy.main;

import de.zpenguin.polymancy.compat.PolyCompat;
import de.zpenguin.polymancy.wands.PolyWands;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    	PolyWands.registerWandParts();
    	PolyCompat.preInit();
    }

    public void init(FMLInitializationEvent e) {
    	PolyResearch.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
