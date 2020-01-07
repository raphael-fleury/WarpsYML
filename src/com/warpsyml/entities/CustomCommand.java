package com.warpsyml.entities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.warpsyml.services.MessageService;
import com.warpsyml.services.WarpService;

public class CustomCommand {
	
	protected CommandSender sender;
	protected String usage;
	
	public CustomCommand(CommandSender sender, String usage) {
		this.sender = sender;
		this.usage = usage;
	}
	
	public String getMessage(String key) {
		String string = MessageService.getMessage(key);
		string = string.replaceAll("%warplist%", WarpService.getListToString());
		string = string.replaceAll("%commandusage%", usage);
		string = string.replaceAll("%playername%", (sender instanceof Player) ?
				sender.getName() : "Console");
		return string;
	}
	
	public void sendMessage(String key) {
		sender.sendMessage(getMessage(key));
	}
	
	public void sendMessage(String key, String warpName) {
		sender.sendMessage(getMessage(key).replaceAll("%warpname%", warpName));
	}
}
