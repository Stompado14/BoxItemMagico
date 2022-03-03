package me.itemmagico;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    public String Prefix;

    public String itemSend;
    public String usageCommand;
    public String playerNotFound;
    public String itemInexist;
    public String numberInvalid;
    public String lessThan0;

    public void loadConfig() {

        FileConfiguration config = BoxItemMagico.Instance.getConfig();

        Prefix = config.getString("Prefixo").replace("&", "§");

        itemSend = config.getString("ItemEnviado").replace("&", "§");
        usageCommand = config.getString("ComoUsar").replace("&", "§");
        playerNotFound = config.getString("JogadorNaoEncontrado").replace("&", "§");
        itemInexist = config.getString("ItemInexistente").replace("&", "§");
        numberInvalid = config.getString("NumeroInvalido").replace("&", "§");
        lessThan0 = config.getString("NumeroMenorQue0").replace("&", "§");
    }
}