package com.warpsyml.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.warpsyml.entities.Warp;
import com.warpsyml.services.WarpService;

public class WarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command!");
			return false;			
		}
		
		Player player = (Player)sender;
		
		if (args.length != 1) {
			sender.sendMessage("Correct usage: /setwarp <name>");
			return false;
		}
		
		Warp warp = WarpService.ReadYml(args[0]);
				
		if (warp == null) {
			sender.sendMessage("Warp " + args[0] + " hasn't been set yet.");
			return false;
		}
		
		player.teleport(warp.getLocation());
		sender.sendMessage("Teleported to: " + warp.getName());
		return true;
    }
}
