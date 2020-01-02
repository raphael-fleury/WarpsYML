package com.warpsyml.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.warpsyml.entities.Warp;
import com.warpsyml.services.MessageService;
import com.warpsyml.services.WarpService;

public class WarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			MessageService.ConsoleError("Only players can execute this command!");
			return false;			
		}
		
		Player player = (Player)sender;
		
		if (args.length != 1) {
			MessageService.sendError(sender, "Correct usage: /warp <name>");
			return false;
		}
		
		Warp warp = WarpService.ReadYml(args[0]);
				
		if (warp == null) {
			MessageService.sendError(sender, "Warp " + args[0] + " hasn't been set yet.");
			return false;
		}
		
		player.teleport(warp.getLocation());
		MessageService.sendSuccess(sender, "Teleported to: " + warp.getName());
		return true;
    }
}
