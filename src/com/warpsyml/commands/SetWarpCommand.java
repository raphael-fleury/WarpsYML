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
			MessageService.sendFixedMessage("only-players-command", sender, label);
			return true;	
		}
		
		Player player = (Player)sender;
		
		if (args.length != 1) {
			MessageService.sendFixedMessage("correct-usage", sender, label);
			return true;
		}
		
		Warp warp = new Warp(args[0], player.getLocation());
		
		sender.sendMessage(writeYml(warp) ?
			MessageService.getFixedMessage("warp-set", sender, label, args[0]) :
			MessageService.getFixedMessage("setting-warp-error", sender, label, args[0])
		);
		
		return true;
    }
	
	public static boolean writeYml(Warp warp) {
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
