package com.example.xraymod;

import zone.rong.mixinbooter.IMixinLoader;
import java.util.Collections;
import java.util.List;

public class MixinLoader implements IMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.xraymod.json");
    }
}
