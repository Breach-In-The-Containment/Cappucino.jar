package com.breachinthecontainment.cappucino;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Cappucino.MODID, name = "Cappucino", version = "1.0")
public class Cappucino {

    public static final String MODID = "cappucino";

    @Mod.Instance
    public static Cappucino instance;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new CorruptionManager());
    }
}