package com.example.xraymod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = XRayMod.MODID, name = XRayMod.NAME, version = XRayMod.VERSION,
     clientSideOnly = true, acceptedMinecraftVersions = "[1.12.2]")
public class XRayMod {
    public static final String MODID = "xraymod";
    public static final String NAME = "XRay Mod";
    public static final String VERSION = "1.0.0";

    // X-Ray 开关状态，Mixin 类会读取这个静态变量
    public static boolean enabled = false;

    private static KeyBinding toggleKey;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // 注册按键 X
        toggleKey = new KeyBinding("key.xraymod.toggle", Keyboard.KEY_X, "key.categories.xraymod");
        ClientRegistry.registerKeyBinding(toggleKey);
    }

    @Mod.EventBusSubscriber(modid = MODID, value = net.minecraftforge.fml.relauncher.Side.CLIENT)
    public static class KeyHandler {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END && toggleKey != null) {
                // 使用 isPressed 确保只触发一次，而不是按住持续触发
                if (toggleKey.isPressed()) {
                    enabled = !enabled;
                    // 可选：打印日志，方便验证
                    System.out.println("[XRayMod] X-Ray: " + (enabled ? "ON" : "OFF"));
                }
            }
        }
    }
}
