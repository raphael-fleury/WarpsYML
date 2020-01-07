package com.warpsyml.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.warpsyml.entities.CustomCommand;
import com.warpsyml.services.WarpService;

public class WarpsCommand implements CommandExecutor {
	
	private String usage = "/warps";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CustomCommand cmd = new CustomCommand(sender, usage);
		
		if(WarpService.noWarps()) {
			cmd.sendMessage("no-warps-set");
			return true;
		}
		
		cmd.sendMessage("warp-list");
		return true;
    }
}
