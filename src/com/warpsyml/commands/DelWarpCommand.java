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
			MessageService.sendFixedMessage("correct-usage", sender, label);
			return true;
		}
		
		File file = WarpService.warpFile(args[0]);
		
		if (!file.exists())
			MessageService.sendFixedMessage("warp-not-set", sender, label);
		else {
			if (file.delete())
				MessageService.sendFixedMessage("warp-deleted", sender, label);
			else
				MessageService.sendFixedMessage("deleting-warp-error", sender, label);
		}
		
		return true;
    }
}
