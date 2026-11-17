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

    @Inject(method = "func_149686_d", at = @At("HEAD"), cancellable = true)
    private void onIsOpaqueCube(IBlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!XRayMod.enabled) return;

        Block block = state.getBlock();

        if (block == Blocks.COAL_ORE || block == Blocks.IRON_ORE || block == Blocks.GOLD_ORE ||
            block == Blocks.DIAMOND_ORE || block == Blocks.REDSTONE_ORE || block == Blocks.LAPIS_ORE ||
            block == Blocks.EMERALD_ORE || block == Blocks.QUARTZ_ORE) {
            cir.setReturnValue(true);
            return;
        }

        if (block == Blocks.PLANKS) {
            cir.setReturnValue(true);
            return;
        }

        if (block == Blocks.COBBLESTONE) {
            cir.setReturnValue(true);
            return;
        }

        cir.setReturnValue(false);
    }
}
