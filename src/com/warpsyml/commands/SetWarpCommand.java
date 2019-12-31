package com.warpsyml.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.warpsyml.Main;
import com.warpsyml.entities.Warp;

public class SetWarpCommand implements CommandExecutor {

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
		Main.warps.add(warp);
		WriteYml(warp, YamlConfiguration.loadConfiguration(new File(Main.plugin.getDataFolder(), "warps.yml")));
		return true;
    }
	
	public static void WriteYml(Warp warp, YamlConfiguration config) {
		config.set(warp.getName() + ".location.world", warp.getLocation().getWorld().getName());
		config.set(warp.getName() + ".location.x", warp.getLocation().getX());
		config.set(warp.getName() + ".location.y", warp.getLocation().getY());
		config.set(warp.getName() + ".location.z", warp.getLocation().getZ());
		config.set(warp.getName() + ".location.yaw", warp.getLocation().getYaw());
		config.set(warp.getName() + ".location.pitch", warp.getLocation().getPitch());
		Main.plugin.saveResource("warps.yml", true);
	}

}
