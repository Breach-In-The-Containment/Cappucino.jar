package com.breachinthecontainment.cappucino;

import com.breachinthecontainment.cappucino.events.AnimatronicSpawnEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CorruptionManager {

    private long lastEventTime = 0;
    private static final long MIN_INTERVAL = 6000L; // ~5 min in ticks (1000 ticks = 1 min)

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (server == null) return;

        World world = server.getEntityWorld();
        long time = world.getTotalWorldTime();

        if (time - lastEventTime > MIN_INTERVAL) {
            if (!world.playerEntities.isEmpty()) {
                EntityPlayer player = world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
                int eventId = world.rand.nextInt(1); // 0 = animatronic for now

                switch (eventId) {
                    case 0:
                        AnimatronicSpawnEvent.trigger(player);
                        break;
                }

                lastEventTime = time;
            }
        }
    }
}
