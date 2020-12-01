package com.faendir.minecraft.stackablestew;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SoupItem;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class StewEvent {
    public StewEvent() {
    }

    @SubscribeEvent
    public void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        ItemStack itemStack = event.getItem();
        Item item = itemStack.getItem();
        if (item instanceof SoupItem || item instanceof SuspiciousStewItem) {
            if (itemStack.getMaxStackSize() > 1) {
                LivingEntity entity = event.getEntityLiving();
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    if (!player.abilities.isCreativeMode) {
                        player.addItemStackToInventory(event.getResultStack());
                        if (itemStack.getCount() > 1) {
                            itemStack.shrink(1);
                            event.setResultStack(itemStack);
                        } else {
                            event.setResultStack(ItemStack.EMPTY);
                        }
                    }
                }
            }
        }
    }
}
