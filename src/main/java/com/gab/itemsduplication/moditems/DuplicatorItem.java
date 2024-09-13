package com.gab.itemsduplication.moditems;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DuplicatorItem extends Item {

    public DuplicatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        Direction facing = context.getSide();

        // Ottieni le entità vicine al blocco colpito
        Vec3d hitVec = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        double hitRadius = 1.0;
        for (Entity entity : world.getEntitiesByClass(ItemFrameEntity.class, new net.minecraft.util.math.Box(hitVec.subtract(hitRadius, hitRadius, hitRadius), hitVec.add(hitRadius, hitRadius, hitRadius)), e -> true)) {
            if (entity instanceof ItemFrameEntity itemFrame) {

                // Ottieni l'item nell'item frame
                ItemStack itemInFrame = itemFrame.getHeldItemStack();

                if (!itemInFrame.isEmpty()) {
                    ItemStack duplicatedItem;

                    // Se l'item è staccabile, duplica un intero stack
                    if (itemInFrame.getMaxCount() > 1) {
                        duplicatedItem = new ItemStack(itemInFrame.getItem(), itemInFrame.getMaxCount());
                    } else {
                        // Se l'item non è staccabile, duplica solo una copia
                        duplicatedItem = itemInFrame.copy();
                    }

                    // Aggiungi l'item duplicato all'inventario del giocatore
                    if (!player.getInventory().insertStack(duplicatedItem)) {
                        player.dropItem(duplicatedItem, false);
                    }

                    // Riproduci un suono di successo
                    world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, 1.0F);

                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("Farma di meno!!").styled(style -> style.withColor(Formatting.RED).withItalic(true)));
        tooltip.add(new LiteralText("Non perdere mai questa torcia!").styled(style -> style.withColor(Formatting.RED).withItalic(true)));
    }
}
