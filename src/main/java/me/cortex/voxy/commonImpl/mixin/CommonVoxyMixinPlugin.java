package me.cortex.voxy.commonImpl.mixin;

import me.cortex.voxy.commonImpl.Platform;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class CommonVoxyMixinPlugin implements IMixinConfigPlugin {
    private static boolean chunkyInstalled;

    @Override
    public void onLoad(String mixinPackage) {
        chunkyInstalled = Platform.isModLoaded("chunky");
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        //Chunky is optional, only apply its mixins when it is present
        if (mixinClassName.startsWith("me.cortex.voxy.commonImpl.mixin.chunky.")) {
            return chunkyInstalled;
        }
        return true;
    }

    @Override public List<String> getMixins() { return null; }

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
