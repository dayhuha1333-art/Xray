package com.example.xraymod.mixin;

import com.example.xraymod.XRayMod;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {

    @Inject(method = "getRenderType(Lnet/minecraft/block/state/IBlockState;)Lnet/minecraft/util/EnumBlockRenderType;", at = @At("HEAD"), cancellable = true)
    private void onGetRenderType(IBlockState state, CallbackInfoReturnable<EnumBlockRenderType> cir) {
        System.out.println("[XRayMod] Mixin hit: " + state.getBlock().getRegistryName());
        
        if (!XRayMod.enabled) return;

        Block block = state.getBlock();

        // Если это целевой блок — рендерим как обычно
        if (block == Blocks.COAL_ORE || block == Blocks.IRON_ORE || block == Blocks.GOLD_ORE ||
            block == Blocks.DIAMOND_ORE || block == Blocks.REDSTONE_ORE || block == Blocks.LAPIS_ORE ||
            block == Blocks.EMERALD_ORE || block == Blocks.QUARTZ_ORE ||
            block == Blocks.PLANKS || block == Blocks.COBBLESTONE) {
            return;
        }

        // Иначе — делаем невидимым
        cir.setReturnValue(EnumBlockRenderType.INVISIBLE);
    }
}
