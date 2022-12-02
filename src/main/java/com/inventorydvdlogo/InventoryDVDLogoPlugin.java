package com.inventorydvdlogo;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayManager;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@PluginDescriptor(
        name = "Inventory DVD Logo"
)
public class InventoryDVDLogoPlugin extends Plugin {
    @Inject
    private Client client;
    private DVDLogo dvdLogo;
    private final int wallDistance = 1;


    @Override
    protected void startUp() throws IllegalAccessException {
        dvdLogo = new DVDLogo();
    }

    @Subscribe
    public void onBeforeRender(BeforeRender beforeRender) {
        Widget inventory = client.getWidget(WidgetInfo.TO_GROUP(10747998), 0);
        if (inventory == null) return;
        dvdLogo.travel();
        inventory.setForcedPosition((int) dvdLogo.x, (int) dvdLogo.y);
        Rectangle bounds = inventory.getBounds();
        if (bounds.getMinX() < wallDistance || client.getViewportWidth() - bounds.getMaxX()  < wallDistance)
            dvdLogo.flip(false);
        else if (bounds.getMinY() < wallDistance || client.getViewportHeight() - bounds.getMaxY() < wallDistance)
            dvdLogo.flip(true);

    }
}
