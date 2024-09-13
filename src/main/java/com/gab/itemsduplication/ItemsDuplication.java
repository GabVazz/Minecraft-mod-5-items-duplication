package com.gab.itemsduplication;

import com.gab.itemsduplication.moditems.ModItems;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemsDuplication implements ModInitializer {
	public static final String MOD_ID = "items-duplication";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registraModItems();
	}
}