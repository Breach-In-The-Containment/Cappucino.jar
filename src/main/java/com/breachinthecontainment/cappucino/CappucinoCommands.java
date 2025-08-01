package com.breachinthecontainment.cappucino;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CappucinoCommands {

    public static void register(FMLServerStartingEvent event) {
        event.registerServerCommand(new MusicBoxCommand("musicbox1", "musicbox_1"));
        event.registerServerCommand(new MusicBoxCommand("musicbox2", "musicbox_2"));
        event.registerServerCommand(new MusicBoxCommand("musicbox3", "musicbox_3"));
        event.registerServerCommand(new CorruptionLevelCommand());
    }

    private static class MusicBoxCommand extends CommandBase {
        private final String name;
        private final String soundId;

        public MusicBoxCommand(String name, String soundId) {
            this.name = name;
            this.soundId = soundId;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/" + name;
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
            if (sender instanceof EntityPlayerMP) {
                EntityPlayerMP player = (EntityPlayerMP) sender;
                SoundEvent sound = SoundEvent.REGISTRY.getObject(new ResourceLocation("cappucino", soundId));

                if (sound != null) {
                    player.world.playSound(null, player.getPosition(), sound, SoundCategory.MUSIC, 1.0f, 1.0f);
                } else {
                    sender.sendMessage(new TextComponentString("❌ Sound not found: " + soundId));
                }
            } else {
                sender.sendMessage(new TextComponentString("⚠ Only players can use this command."));
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 0;
        }
    }

    private static class CorruptionLevelCommand extends CommandBase {

        @Override
        public String getName() {
            return "corruptionlevel";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/corruptionlevel";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
            if (sender instanceof EntityPlayerMP) {
                // Placeholder corruption level — replace with real logic later
                int corruption = 17;

                sender.sendMessage(new TextComponentString("☣ Your corruption level is: " + corruption));
            } else {
                sender.sendMessage(new TextComponentString("⚠ Only players can use this command."));
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 0;
        }
    }
}
