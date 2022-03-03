package me.itemmagico.managers;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Items {

    private ItemStack item;
    private List<String> commands;
    private boolean isTitle;
    private String title;
    private String subTitle;
    private boolean isSound;
    private Sound sound;
    private boolean isActionBar;
    private String message;

    public Items(ItemStack item, List<String> commands, boolean isTitle, String title, String subTitle, boolean isSound, Sound sound, boolean isActionBar, String message) {
        this.item = item;
        this.commands = commands;
        this.isTitle = isTitle;
        this.title = title;
        this.subTitle = subTitle;
        this.isSound = isSound;
        this.sound = sound;
        this.isActionBar = isActionBar;
        this.message = message;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<String> getCommands() {
        return commands;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public boolean isSound() {
        return isSound;
    }

    public Sound getSound() {
        return sound;
    }

    public boolean isActionBar() {
        return isActionBar;
    }

    public String getMessage() {
        return message;
    }
}
