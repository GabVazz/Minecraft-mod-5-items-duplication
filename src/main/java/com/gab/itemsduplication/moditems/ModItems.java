package com.gab.itemsduplication.moditems;

import com.gab.itemsduplication.ItemsDuplication;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item DUPLICATOR_ITEM = registraItem("duplicator", new DuplicatorItem(new FabricItemSettings().rarity(Rarity.RARE).maxDamage(50).group(ItemGroup.MISC)));

    private static Item registraItem(String nome, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ItemsDuplication.MOD_ID, nome), item);
    }

    //registra tutti gli item definiti sopra in game
    public static void registraModItems() {
        System.out.println("Sto registrando i mod items per il seguente ID: " + ItemsDuplication.MOD_ID);
    }
}
