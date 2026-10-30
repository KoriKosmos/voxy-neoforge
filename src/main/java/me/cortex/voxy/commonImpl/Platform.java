package me.cortex.voxy.commonImpl;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModFileInfo;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Path;

//NeoForge replacements for the FabricLoader calls used throughout voxy.
// LoadingModList is used instead of ModList since mixin plugins and mixin static initializers
// run before ModList is constructed
public final class Platform {
    public static final String MOD_ID = "voxy";

    private Platform() {}

    public static boolean isModLoaded(String modId) {
        var list = LoadingModList.get();
        return list != null && list.getModFileById(modId) != null;
    }

    public static boolean isDedicatedServer() {
        return FMLLoader.getDist() == Dist.DEDICATED_SERVER;
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static ModFileInfo getVoxyModFile() {
        var list = LoadingModList.get();
        return list == null ? null : list.getModFileById(MOD_ID);
    }

    public static IModInfo getVoxyModInfo() {
        var file = getVoxyModFile();
        if (file == null) {
            return null;
        }
        for (var mod : file.getMods()) {
            if (mod.getModId().equals(MOD_ID)) {
                return mod;
            }
        }
        return null;
    }

    //Root of the voxy mod file, used for class discovery
    public static Path getVoxyRootPath() {
        var file = getVoxyModFile();
        return file == null ? null : file.getFile().getSecureJar().getRootPath();
    }
}
