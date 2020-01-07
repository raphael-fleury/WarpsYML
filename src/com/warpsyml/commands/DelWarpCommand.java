package com.warpsyml.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.warpsyml.entities.CustomCommand;
import com.warpsyml.entities.CustomWarpCommand;
import com.warpsyml.services.WarpService;

public class DelWarpCommand implements CommandExecutor {

	private String usage = "/delwarp <warp>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CustomCommand cmd = new CustomCommand(sender, usage);
		
		if (args.length != 1) {		
			cmd.sendMessage("correct-usage");
			return true;
		}
		
		File file = WarpService.getFile(args[0]);
		try {
			cmd = new CustomWarpCommand(sender, usage, args[0]);
		}
		catch (Exception e) {
			cmd.sendMessage("warp-not-set", args[0]);
			return true;
		}
		
		if (file.delete())
			cmd.sendMessage("warp-deleted");
		else
			cmd.sendMessage("deleting-warp-error");
		
		return true;
    }
}
