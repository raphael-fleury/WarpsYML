package com.warpsyml.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.warpsyml.Main;

public class WarpsCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(Main.warps.isEmpty()) {
			sender.sendMessage("No warp has been set.");
		}
		
		String message = "Warps: ";
		for(int i = 0; i < Main.warps.size(); i++) {
			message += Main.warps.get(i).getName() + " ";
		}
		sender.sendMessage(message);
		return true;
    }
}
