package com.snipelist;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("snipeList")
public interface SnipeListConfig extends Config
{
    @ConfigItem(
            keyName = "snipeListColor",
            name = "Snipe List Color",
            description = "The color to highlight users on your snipe list."
    )
    default Color outlineColor()
    {
        return new Color(0, 0, 255, 100);
    }

    @ConfigItem(
            keyName = "snipeListNames",
            name = "Snipe List RSN(s)",
            description = "A list of RSNs to highlight when seen."
    )
    default String snipeListNames() {
        return "";
    }
}
