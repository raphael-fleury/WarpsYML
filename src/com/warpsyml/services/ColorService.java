package com.warpsyml.services;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.warpsyml.entities.Colors;

public class ColorService {

	public static final Colors DEFAULT = new Colors(ChatColor.RED, ChatColor.GREEN, ChatColor.WHITE);
	
	public static ChatColor stringToColor(String text, ChatColor def) {
		try {
            ChatColor color = ChatColor.valueOf(text);
            return color;
        } catch (java.lang.IllegalArgumentException ex) {
        	MessageService.ConsoleLog("[WarpsYML] " + MessageService.error("Invalid color: " + text));
            return def;
        }
	}
	
	public static Colors loadColors(String error, String success, String neutral) {
		return new Colors(
			stringToColor(error, DEFAULT.error),
			stringToColor(success, DEFAULT.success),
			stringToColor(neutral, DEFAULT.neutral)
		);
	}
	
	public static Colors loadColors(YamlConfiguration config) {
		
		if (!config.contains("colors")) {
			return DEFAULT;
		}
		
		if (!config.contains("colors.error") || !config.contains("colors.success") || !config.contains("colors.neutral")) {
			return DEFAULT;
		}
		
		return loadColors(
			config.getString("colors.error"),
			config.getString("colors.success"),
			config.getString("colors.neutral")
		);
	}
	
	public static Colors loadColors(FileConfiguration fileConfig) {
		YamlConfiguration yml = (YamlConfiguration)fileConfig;
		
		return loadColors(yml);
	}
}
