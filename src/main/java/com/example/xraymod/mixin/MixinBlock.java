package com.example.xraymod.mixin;

import com.example.xraymod.XRayMod;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {

    @Inject(method = "isOpaqueCube", at = @At("HEAD"), cancellable = true)
    private void onIsOpaqueCube(IBlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!XRayMod.enabled) return; // 如果 X-Ray 未开启，走原版逻辑

        Block block = state.getBlock();

        // 判断是否是目标方块（需要可见的）
        // 1. 所有矿石
        if (block == Blocks.COAL_ORE || block == Blocks.IRON_ORE || block == Blocks.GOLD_ORE ||
            block == Blocks.DIAMOND_ORE || block == Blocks.REDSTONE_ORE || block == Blocks.LAPIS_ORE ||
            block == Blocks.EMERALD_ORE || block == Blocks.QUARTZ_ORE) {
            cir.setReturnValue(true); // 不透明，正常渲染
            return;
        }

        // 2. 木板（所有 meta 0-5）
        if (block == Blocks.PLANKS) {
            cir.setReturnValue(true);
            return;
        }

        // 3. 圆石
        if (block == Blocks.COBBLESTONE) {
            cir.setReturnValue(true);
            return;
        }

        // 其他所有方块（石头、泥土、花岗岩等） -> 透明，不渲染
        // 返回 false 让游戏认为该方块不阻挡视线，从而使相邻方块的面不被剔除
        cir.setReturnValue(false);
    }
}
