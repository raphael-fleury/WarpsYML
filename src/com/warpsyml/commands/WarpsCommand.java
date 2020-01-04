package com.warpsyml.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.warpsyml.services.MessageService;
import com.warpsyml.services.WarpService;

public class WarpsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<String> warpList = WarpService.warpList();
		
		if(warpList.isEmpty()) {
			MessageService.sendFixedMessage("no-warps-set", sender, label);
			return true;
		}
		
		String message = "Warps: ";
		for(int i = 0; i < warpList.size(); i++) {
			message += warpList.get(i);
			if (i < warpList.size() - 1)
				message += ", ";
		}
		sender.sendMessage(message);
		return true;
    }
}
