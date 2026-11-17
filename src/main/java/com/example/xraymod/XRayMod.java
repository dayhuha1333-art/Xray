package com.example.xraymod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod(modid = XRayMod.MODID, name = XRayMod.NAME, version = XRayMod.VERSION,
     clientSideOnly = true, acceptedMinecraftVersions = "[1.12.2]")
public class XRayMod {
    public static final String MODID = "xraymod";
    public static final String NAME = "XRay Mod";
    public static final String VERSION = "1.0.0";

    public static boolean enabled = false;
    private static KeyBinding toggleKey;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        toggleKey = new KeyBinding("key.xraymod.toggle", Keyboard.KEY_X, "key.categories.xraymod");
        ClientRegistry.registerKeyBinding(toggleKey);
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Side.CLIENT)
    public static class KeyHandler {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END && toggleKey != null) {
                if (toggleKey.isPressed()) {
                    enabled = !enabled;
                    System.out.println("[XRayMod] X-Ray: " + (enabled ? "ON" : "OFF"));
                }
            }
        }
    }
}
