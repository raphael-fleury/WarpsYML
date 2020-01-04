package com.warpsyml.services;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.warpsyml.WarpsYML;

import net.md_5.bungee.api.ChatColor;

public class MessageService {
	
	public static File messagesFile() {
		return new File(WarpsYML.plugin.getDataFolder(), "messages.yml");
	}
	
	public static YamlConfiguration messagesYml() {
		return YamlConfiguration.loadConfiguration(messagesFile());
	}
	
	public static String getMessage(String key) {
		if(!messagesYml().contains(key)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Key " + key + "at messages.yml not found");
			return null;
		}

		return messagesYml().getString(key);
	}
	
	public static String getFixedMessage(String key, CommandSender sender, String label) {
		return replaceTags(getMessage(key), sender, label);
	}
	
	public static String getFixedMessage(String key, CommandSender sender, String label, String warpNameOrList) {
		return replaceTags(getMessage(key), sender, label, warpNameOrList);
	}
	
	public static void consoleLog(String key) {
		Bukkit.getConsoleSender().sendMessage(getMessage(key));
	}
	
	
	public static void sendMessage(String key, CommandSender sender) {
		sender.sendMessage(getMessage(key));
	}
	
	public static void sendFixedMessage(String key, CommandSender sender, String label) {
		sender.sendMessage(getFixedMessage(key, sender, label));
	}
	
	public static void sendFixedMessage(String key, CommandSender sender, String label, String warpNameOrList) {
		sender.sendMessage(getFixedMessage(key, sender, label, warpNameOrList));
	}
	
	public static String replaceWarpName(String text, String warpName) {
		return text.replaceAll("{warpname}", warpName).replaceAll("{warplist}", warpName);
	}
	
	public static String replaceTags(String text, CommandSender sender, String label) {
		String string = text.replaceAll("{playername}", 
			(sender instanceof Player) ? sender.getName() : "Console");
		string.replaceAll("{commandusage}", label);
		return string;
	}
	
	public static String replaceTags(String text, CommandSender sender, String label, String warpNameOrList) {
		String string = replaceTags(text, sender, label);
		string.replaceAll("{warpname}", warpNameOrList);
		string.replaceAll("{warplist}", warpNameOrList);
		return string;
	}
}
