package me.itemmagico.listeners;

import me.itemmagico.managers.Items;
import me.itemmagico.managers.ItemsDao;
import me.itemmagico.utils.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemsEvent implements Listener {

    @EventHandler
    void onItemsEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();

        if (item == null || item.getType() == Material.AIR) return;

        Items items = ItemsDao.findByItems(item);
        if (items == null) return;

        if (item.getAmount() <= 1)
            p.setItemInHand(new ItemStack(Material.AIR));
        else
            item.setAmount(item.getAmount() - 1);

        if (items.isSound())
            p.playSound(p.getLocation(), items.getSound(), 1, 1);

        if (items.isTitle())
            p.sendTitle(items.getTitle(), items.getSubTitle());

        for (String commands : items.getCommands())
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("{jogador}", p.getName()));

        if (items.isActionBar())
            ActionBarAPI.sendActionBar(items.getMessage(), p);
        else
            p.sendMessage(items.getMessage());

    }
}