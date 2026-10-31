package me.cortex.voxy.client.mixin;

import me.cortex.voxy.commonImpl.Platform;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientVoxyMixinPlugin implements IMixinConfigPlugin {
    private static boolean valkyrienSkiesInstalled;
    private static boolean irisInstalled;

    @Override
    public void onLoad(String mixinPackage) {
        valkyrienSkiesInstalled = Platform.isModLoaded("valkyrienskies");
        irisInstalled = Platform.isModLoaded("iris");
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        //Iris is optional, dont try to apply its mixins when the target classes dont exist
        if (mixinClassName.startsWith("me.cortex.voxy.client.mixin.iris.")) {
            return irisInstalled;
        }
        return true;
    }

    @Override public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();
        if (valkyrienSkiesInstalled) {
            mixins.add("sodium.MixinSodiumWorldRendererVS");
        } else {
            mixins.add("sodium.MixinDefaultChunkRenderer");
        }

        return mixins;
    }

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}