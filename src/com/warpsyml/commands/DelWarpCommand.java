package com.warpsyml.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.warpsyml.services.MessageService;
import com.warpsyml.services.WarpService;

public class DelWarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length != 1) {
			MessageService.sendError(sender, "Correct usage: /setwarp <name>");
			return true;
		}
		
		File file = WarpService.warpFile(args[0]);
		
		if (!file.exists())
			MessageService.sendError(sender, "Warp " + args[0] + " hasn't been set yet.");
		else {
			if (file.delete())
				MessageService.sendSuccess(sender, "Warp " + args[0] + " deleted successfully!");
			else
				MessageService.sendError(sender, "Could not delete warp " + args[0] + "!");
		}
		
		return true;
    }
}
