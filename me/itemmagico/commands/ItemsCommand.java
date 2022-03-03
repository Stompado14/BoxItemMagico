package me.itemmagico.commands;

import me.itemmagico.BoxItemMagico;
import me.itemmagico.ConfigManager;
import me.itemmagico.managers.Items;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemsCommand implements CommandExecutor {

    ConfigManager Config = BoxItemMagico.Config;
    String prefix = Config.Prefix;

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lb, String[] a) {

        if (!s.hasPermission("boxitemmagico.itemmagico")) {
            s.sendMessage("§cVocê não possui permissão para esse comando.");
            return true;
        }

        if (a.length > 0) {

            if (a.length < 3) {
                s.sendMessage(Config.usageCommand.replace("@prefixo", prefix));
                return true;
            }

            Player t = Bukkit.getPlayer(a[0]);
            if (t == null) {
                s.sendMessage(Config.playerNotFound.replace("@prefixo", prefix));
                return true;
            }

            Items items = BoxItemMagico.getItemsDao().getItems().get(a[1]);
            if (items == null) {
                s.sendMessage(Config.itemInexist.replace("@prefixo", prefix));
                return true;
            }

            int amount = 0;

            try {

                amount = Integer.parseInt(a[2]);

            } catch (Exception e) {
                s.sendMessage(Config.numberInvalid.replace("@prefixo", prefix));
                return true;
            }

            if (amount <= 0) {
                s.sendMessage(Config.lessThan0.replace("@prefixo", prefix));
                return true;
            }

            ItemStack item = items.getItem();
            item.setAmount(amount);

            t.getInventory().addItem(item);

            s.sendMessage(Config.itemSend.replace("@prefixo", prefix).replace("{jogador}", t.getName())
                    .replace("{item}", item.getItemMeta().getDisplayName())
                        .replace("{quantia}", "" + amount));

        } else {

            s.sendMessage(Config.usageCommand.replace("@prefixo", prefix));

        }

        return false;
    }
}
