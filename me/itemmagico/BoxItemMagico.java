package me.itemmagico;

import me.itemmagico.commands.ItemsCommand;
import me.itemmagico.listeners.ItemsEvent;
import me.itemmagico.managers.Items;
import me.itemmagico.managers.ItemsDao;
import me.itemmagico.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;

public class BoxItemMagico extends JavaPlugin {

    public static BoxItemMagico Instance;
    public static ConfigManager Config;

    private static ItemsDao itemsDao = new ItemsDao();

    public void onEnable() {

        Instance = this;
        registerYaml();
        registerCommands();
        registerEvents();
        registerItems();
        sendMessage();

    }

    private void registerCommands() {

        getCommand("itemmagico").setExecutor(new ItemsCommand());

    }

    private void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new ItemsEvent(), this);

    }

    private void registerYaml() {

        Config = new ConfigManager();
        saveDefaultConfig();
        Config.loadConfig();

    }

    private void sendMessage() {

        Bukkit.getConsoleSender().sendMessage("§5[BoxItemMagico] §fCriado por §b[Stompado]");
        Bukkit.getConsoleSender().sendMessage("§5[BoxItemMagico] §aO plugin §5BoxItemMagico §afoi iniciado com sucesso.");

    }

    private void registerItems() {

        ConfigurationSection section = getConfig().getConfigurationSection("Items");
        for (String path : section.getKeys(false)) {

            ConfigurationSection key = getConfig().getConfigurationSection("Items." + path);

            String name = key.getString("Nome").replace("&", "§");
            Material material = Material.valueOf(key.getString("Material").split(":")[0]);
            int data = Integer.parseInt(key.getString("Material").split(":")[1]);

            List<String> lore = key.getStringList("Lore");
            lore = lore.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());
            List<String> commands = key.getStringList("Comandos");

            boolean isTitle = key.getBoolean("Titulos.Ativar");
            String title = key.getString("Titulos.Titulo").replace("&", "§");
            String subTitle = key.getString("Titulos.SubTitulo").replace("&", "§");

            boolean isSound = key.getBoolean("Sons.Ativar");
            Sound sound = Sound.valueOf(key.getString("Sons.Som"));

            boolean isActionBar = key.getBoolean("Mensagens.ActionBar");
            String message = key.getString("Mensagens.Mensagem").replace("&", "§");

            ItemStack item = new ItemBuilder(material, 1, data).setName(name).setLore(lore).addGlow(true).build();

            Items items = new Items(item, commands, isTitle, title, subTitle, isSound, sound, isActionBar, message);
            ItemsDao.addItems(path, items);

        }
    }

    public static ItemsDao getItemsDao() {
        return itemsDao;
    }
}