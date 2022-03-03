package me.itemmagico.managers;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemsDao {

    private static Map<String, Items> itemsMap = new HashMap<>();

    public static void addItems(String name, Items items) {
        itemsMap.put(name, items);
    }

    public Map<String, Items> getItems() {
        return itemsMap;

    }

    public static Items findByItems(ItemStack item) {
        return itemsMap.entrySet().stream().map(Map.Entry::getValue).filter(items -> items.getItem().isSimilar(item)).findFirst().orElse(null);
    }
}