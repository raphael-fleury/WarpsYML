package com.warpsyml.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.warpsyml.entities.CustomCommand;
import com.warpsyml.entities.CustomWarpCommand;
import com.warpsyml.entities.Warp;
import com.warpsyml.services.WarpService;

public class WarpCommand implements CommandExecutor {

	private String usage = "/warp <warp>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CustomCommand cmd = new CustomCommand(sender, usage);
		
		if (!(sender instanceof Player)) {
			cmd.sendMessage("only-players-command");
			return true;	
		}
		
		Player player = (Player)sender;
		
		if (args.length != 1) {
			cmd.sendMessage("correct-usage");
			return true;
		}
		
		Warp warp = WarpService.getWarp(args[0]);
		cmd = new CustomWarpCommand(sender, usage, warp);
				
		if (warp == null) {
			cmd.sendMessage("warp-not-set");
			return true;
		}
		
		player.teleport(warp.getLocation());
		cmd.sendMessage("warp-teleported");
		return true;
    }
}
