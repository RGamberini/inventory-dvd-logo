package com.inventorydvdlogo;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class InventoryDVDLogoPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(InventoryDVDLogoPlugin.class);
		RuneLite.main(args);
	}
}