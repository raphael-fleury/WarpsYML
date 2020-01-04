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
			MessageService.sendFixedMessage("only-players-command", sender, label);
			return true;	
		}
		
		Player player = (Player)sender;
		
		if (args.length != 1) {
			MessageService.sendFixedMessage("correct-usage", sender, label);
			return true;
		}
		
		Warp warp = WarpService.readYml(args[0]);
				
		if (warp == null) {
			MessageService.sendFixedMessage("warp-not-set", sender, label);
			return true;
		}
		
		player.teleport(warp.getLocation());
		MessageService.sendFixedMessage("warp-teleported", sender, label);
		return true;
    }
}
