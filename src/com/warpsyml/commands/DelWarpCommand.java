package com.warpsyml.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.warpsyml.entities.CustomCommand;
import com.warpsyml.entities.CustomWarpCommand;
import com.warpsyml.services.WarpService;

public class DelWarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CustomCommand cmd = new CustomCommand(sender, label);
		
		if (args.length != 1) {		
			cmd.sendMessage("correct-usage");
			return true;
		}
		
		File file = WarpService.getFile(args[0]);
		try {
			cmd = new CustomWarpCommand(sender, label, args[0]);
		}
		catch (Exception e) {
			cmd.sendMessage("warp-not-set");
			return true;
		}
		
		if (!file.exists())
			cmd.sendMessage("warp-not-set");
		else {
			if (file.delete())
				cmd.sendMessage("warp-deleted");
			else
				cmd.sendMessage("deleting-warp-error");
		}
		
		return true;
    }
}
