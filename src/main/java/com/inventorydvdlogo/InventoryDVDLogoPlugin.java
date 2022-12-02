package com.inventorydvdlogo;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.BeforeRender;
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
    @Inject
    private OverlayManager overlayManager;
    private Overlay inventory;
    private DVDLogo dvdLogo;
    private final int wallDistance = 1;

    @Override
    protected void startUp() throws IllegalAccessException {
        try {
            Field overlaysField = OverlayManager.class.getDeclaredField("overlays");
            overlaysField.setAccessible(true);

            inventory = ((List<Overlay>) overlaysField.get(overlayManager)).stream()
                    .filter(overlay ->
                            overlay.getName().equals("RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_PARENT")
                    )
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("STARTUP FAILED CANNOT FIND INVENTORY"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        dvdLogo = new DVDLogo();
    }

    @Subscribe
    public void onBeforeRender(BeforeRender beforeRender) {
        Rectangle bounds = inventory.getBounds();
        if (bounds.getMinX() < wallDistance || client.getViewportWidth() - bounds.getMaxX()  < wallDistance)
            dvdLogo.flip(false);
        else if (bounds.getMinY() < wallDistance || client.getViewportHeight() - bounds.getMaxY() < wallDistance)
            dvdLogo.flip(true);
        dvdLogo.travel();
        inventory.setPreferredLocation(new Point((int) dvdLogo.x, (int) dvdLogo.y));
    }

}
