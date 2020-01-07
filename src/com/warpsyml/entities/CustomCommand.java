package com.warpsyml.entities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.warpsyml.services.MessageService;
import com.warpsyml.services.WarpService;

public class CustomCommand {
	
	protected CommandSender sender;
	protected String label;
	
	public CustomCommand(CommandSender sender, String label) {
		this.sender = sender;
		this.label = label;
	}
	
	public String getMessage(String key) {
		String string = MessageService.getMessage(key);
		string = string.replaceAll("%warplist%", WarpService.getListToString());
		string = string.replaceAll("%commandusage%", label);
		string = string.replaceAll("%playername%", (sender instanceof Player) ?
				sender.getName() : "Console");
		return string;
	}
	
	public void sendMessage(String key) {
		sender.sendMessage(getMessage(key));
	}
}
