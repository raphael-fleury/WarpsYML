package com.warpsyml.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.warpsyml.WarpsYML;
import com.warpsyml.entities.CustomCommand;
import com.warpsyml.entities.CustomWarpCommand;
import com.warpsyml.entities.Warp;
import com.warpsyml.services.WarpService;

public class SetWarpCommand implements CommandExecutor {

	private String usage = "/setwarp <warp>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CustomCommand cmd = new CustomCommand(sender, usage);
		
		if(!sender.hasPermission("warp.set")) {
			cmd.sendMessage("no-permission");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			cmd.sendMessage("only-players-command");
			return true;	
		}
		
		Player player = (Player)sender;
		
		if (args.length != 1) {
			cmd.sendMessage("correct-usage");
			return true;
		}
		
		Warp warp = new Warp(args[0], player.getLocation());
		cmd = new CustomWarpCommand(sender, usage, warp);
		
		boolean success = writeYml(warp);
		
		sender.sendMessage(success ?
			cmd.getMessage("warp-set") :
			cmd.getMessage("setting-warp-error")
		);
		
		if (success && WarpsYML.plugin.getConfig().getBoolean("broadcast-on-setting-warp"))
			cmd.broadcast("warp-set-broadcast");
		
		return true;
    }
	
	public static boolean writeYml(Warp warp) {
		YamlConfiguration config = WarpService.getYml(warp.getName() + ".yml");
		config.set("world", warp.getLocation().getWorld().getName());
		config.set("x", warp.getLocation().getBlockX());
		config.set("y", warp.getLocation().getBlockY());
		config.set("z", warp.getLocation().getBlockZ());
		config.set("yaw", warp.getLocation().getYaw());
		config.set("pitch", warp.getLocation().getPitch());
		
		try {
            config.save(WarpService.getFile(warp.getName()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}

}
