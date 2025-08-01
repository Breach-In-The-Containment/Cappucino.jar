package com.breachinthecontainment.cappucino.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

public class AnimatronicSpawnEvent {

    private static final String[] ANIMATRONIC_CLASSES = {
            "com.killercreeper55.fnafmod.entity.custom.EntityFreddyFazbear",
            "com.killercreeper55.fnafmod.entity.custom.EntityBonnie",
            "com.killercreeper55.fnafmod.entity.custom.EntityChica",
            "com.killercreeper55.fnafmod.entity.custom.EntityFoxy",
            "com.killercreeper55.fnafmod.entity.custom.EntityGoldenFreddy"
    };

    public static void trigger(EntityPlayer player) {
        if (!Loader.isModLoaded("fnafmod")) return;

        try {
            World world = player.world;
            if (world.isRemote) return;

            String className = ANIMATRONIC_CLASSES[world.rand.nextInt(ANIMATRONIC_CLASSES.length)];
            Class<?> clazz = Class.forName(className);
            Entity anim = (Entity) clazz.getConstructor(World.class).newInstance(world);

            BlockPos pos = player.getPosition();
            double x = pos.getX() + world.rand.nextInt(8) - 4;
            double y = pos.getY();
            double z = pos.getZ() + world.rand.nextInt(8) - 4;

            anim.setPosition(x, y, z);
            world.spawnEntity(anim);

        } catch (Exception e) {
            System.err.println("Failed to spawn animatronic");
            e.printStackTrace();
        }
    }
}
