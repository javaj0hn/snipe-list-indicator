package com.snipelist;

import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;
import net.runelite.client.util.Text;


import javax.inject.Inject;
import java.awt.*;
import java.util.Objects;

public class SnipeListOverlay extends Overlay {
    private final Client client;
    final SnipeListPlugin plugin;
    private final SnipeListConfig config;
    private final ModelOutlineRenderer outlineRenderer;


    @Inject
    public SnipeListOverlay(Client client, SnipeListPlugin plugin, SnipeListConfig config, ModelOutlineRenderer outlineRenderer) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.outlineRenderer = outlineRenderer;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    public Dimension render(Graphics2D graphics) {

        // get names
        String[] rsns = config.snipeListNames().split(",");

        for(Player player : client.getPlayers()) {
            for (String rsn : rsns) {
                if (Objects.equals(player.getName(), rsn)) {

                    final String name = Text.sanitize(rsn);
                    final int zOffset = player.getLogicalHeight() + 40;

                    Point textLocation = player.getCanvasTextLocation(graphics, name, zOffset);

                    textLocation = new Point(textLocation.getX() + 10, textLocation.getY());

                    final Polygon poly = player.getCanvasTilePoly();
                    if (poly != null)
                    {
                        OverlayUtil.renderPolygon(graphics, poly, config.outlineColor());
                    }
                    OverlayUtil.renderTextLocation(graphics, textLocation, name, config.outlineColor());
                }
            }
        }
        return null;
    }
}
