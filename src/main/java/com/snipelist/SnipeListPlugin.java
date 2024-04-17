package com.snipelist;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
        name = "Snipe List Indicator"
)
public class SnipeListPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private SnipeListConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    SnipeListOverlay overlay;

    @Override
    protected void startUp() throws Exception
    {
        log.info("Snipe List started!");
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        log.info("Snipe List stopped!");
    }

    @Provides
    SnipeListConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(SnipeListConfig.class);
    }
}
