package com.warpsyml.services;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import com.warpsyml.WarpsYML;

import net.md_5.bungee.api.ChatColor;

public class MessageService {
	
	public static File getFile() {
		return new File(WarpsYML.plugin.getDataFolder(), "messages.yml");
	}
	
	public static YamlConfiguration getYml() {
		return YamlConfiguration.loadConfiguration(getFile());
	}
	
	public static String getMessage(String key) {
		if(!getYml().contains(key)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Key " + key + "at messages.yml not found");
			return null;
		}

		return getYml().getString(key);
	}
	
	public static void consoleLog(String key) {
		Bukkit.getConsoleSender().sendMessage(getMessage(key));
	}
	
	
	public static void sendMessage(String key, CommandSender sender) {
		sender.sendMessage(getMessage(key));
	}

}
