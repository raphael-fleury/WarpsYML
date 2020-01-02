package com.warpsyml.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.warpsyml.entities.Warp;
import com.warpsyml.services.WarpService;

public class SetWarpCommand implements CommandExecutor {

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
		
		Warp warp = new Warp(args[0], player.getLocation());
		WriteYml(warp);
		return true;
    }
	
	public static void WriteYml(Warp warp) {
		YamlConfiguration config = WarpService.warpYmlFile(warp.getName());
		config.set("x", warp.getLocation().getBlockX());
		config.set("y", warp.getLocation().getBlockY());
		config.set("z", warp.getLocation().getBlockZ());
		config.set("yaw", warp.getLocation().getYaw());
		config.set("pitch", warp.getLocation().getPitch());
		
		try {
            config.save(WarpService.warpFile(warp.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
