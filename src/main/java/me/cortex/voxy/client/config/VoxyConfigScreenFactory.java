package me.cortex.voxy.client.config;

import me.cortex.voxy.commonImpl.VoxyCommon;
import net.caffeinemc.mods.sodium.client.config.ConfigManager;
import net.caffeinemc.mods.sodium.client.config.structure.OptionPage;
import net.caffeinemc.mods.sodium.client.gui.VideoSettingsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

//NeoForge replacement for the ModMenu integration
public class VoxyConfigScreenFactory implements IConfigScreenFactory {
    @Override
    public Screen createScreen(ModContainer container, Screen parent) {
        if (VoxyCommon.isAvailable()) {
            var page = ConfigManager.CONFIG.getModOptions().stream()
                    .filter(a->a.configId().equals("voxy"))
                    .findFirst()
                    .flatMap(a->a.pages().stream().filter(OptionPage.class::isInstance).map(OptionPage.class::cast).findFirst());
            if (page.isPresent()) {
                return VideoSettingsScreen.createScreen(parent, page.get());
            }
        }
        return VideoSettingsScreen.createScreen(parent);
    }
}
