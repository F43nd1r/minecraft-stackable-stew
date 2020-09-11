package com.faendir.minecraft.villagernames;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

import static cpw.mods.modlauncher.api.INameMappingService.Domain.FIELD;
import static net.minecraftforge.fml.common.ObfuscationReflectionHelper.remapName;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("stackable-stew")
public class StackableStews
{
    public StackableStews() {
        MinecraftForge.EVENT_BUS.register(new StewEvent());
        setStewStackSize();
    }

    private void setStewStackSize() {
        try {
            Field maxStackSizeField = Item.class.getDeclaredField(remapName(FIELD, "field_77777_bU"));
            maxStackSizeField.setAccessible(true);
            maxStackSizeField.set(Items.MUSHROOM_STEW, 16);
            maxStackSizeField.set(Items.RABBIT_STEW, 16);
            maxStackSizeField.set(Items.BEETROOT_SOUP, 16);
            maxStackSizeField.set(Items.SUSPICIOUS_STEW, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
