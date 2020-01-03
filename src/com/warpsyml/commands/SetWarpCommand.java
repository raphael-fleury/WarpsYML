package com.warpsyml.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.warpsyml.entities.Warp;
import com.warpsyml.services.MessageService;
import com.warpsyml.services.WarpService;

public class SetWarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			MessageService.consoleError("Only players can execute this command!");
			return true;	
		}
		
		Player player = (Player)sender;
		
		if (args.length != 1) {
			MessageService.sendError(sender, "Correct usage: /setwarp <name>");
			return true;
		}
		
		Warp warp = new Warp(args[0], player.getLocation());
		
		sender.sendMessage(WriteYml(warp) ?
			MessageService.success("Warp " + args[0] + " has been set!") :
			MessageService.error("Error on setting warp " + args[0] + "!")
		);
		
		return true;
    }
	
	public static boolean WriteYml(Warp warp) {
		YamlConfiguration config = WarpService.warpYmlFile(warp.getName() + ".yml");
		config.set("world", warp.getLocation().getWorld().getName());
		config.set("x", warp.getLocation().getBlockX());
		config.set("y", warp.getLocation().getBlockY());
		config.set("z", warp.getLocation().getBlockZ());
		config.set("yaw", warp.getLocation().getYaw());
		config.set("pitch", warp.getLocation().getPitch());
		
		try {
            config.save(WarpService.warpFile(warp.getName()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}

}
