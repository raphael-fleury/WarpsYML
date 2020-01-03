package com.warpsyml.services;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.warpsyml.entities.ColoredText;
import com.warpsyml.entities.Colors;

public class MessageService {

	public static Colors colors = ColorService.DEFAULT;
	
	public static String success(String msg) {
		return colors.success + msg;
	}
	
	public static String error(String msg) {
		return colors.error + msg;
	}
	
	public static void consoleLog(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
	public static void consoleSuccess(String msg) {
		consoleLog(success(msg));
	}
	
	public static void consoleError(String msg) {
		consoleLog(error(msg));
	}
	
	public static void sendSuccess(CommandSender sender, String msg) {
		sender.sendMessage(success(msg));
	}
	
	public static void sendError(CommandSender sender, String msg) {
		sender.sendMessage(error(msg));
	}
	
	public static void sendColoredMessage(CommandSender sender, ColoredText text) {
		sender.sendMessage(text.ToString());
	}
}
