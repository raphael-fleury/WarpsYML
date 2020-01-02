package com.warpsyml.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.warpsyml.entities.Warp;
import com.warpsyml.services.WarpService;

public class WarpsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Warp[] warpList = WarpService.warpList();
		
		if(warpList.length < 1) {
			sender.sendMessage("No warp has been set.");
			return true;
		}
		
		String message = "Warps: ";
		for(int i = 0; i < warpList.length; i++) {
			message += warpList[i].getName() + " ";
		}
		sender.sendMessage(message);
		return true;
    }
}
