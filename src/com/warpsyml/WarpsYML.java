package com.warpsyml;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.warpsyml.commands.SetWarpCommand;
import com.warpsyml.commands.WarpCommand;
import com.warpsyml.commands.WarpsCommand;
import com.warpsyml.services.ColorService;
import com.warpsyml.services.MessageService;

public class WarpsYML extends JavaPlugin {
	
	public static JavaPlugin plugin;
	
	public void loadCommands() {
		this.getCommand("setwarp").setExecutor(new SetWarpCommand());
		this.getCommand("warps").setExecutor(new WarpsCommand());
		this.getCommand("warp").setExecutor(new WarpCommand());
	}
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		MessageService.colors = ColorService.loadColors(getConfig());
		loadCommands();
		
		MessageService.ConsoleSuccess("WarpsYML enabled!");
		plugin = this;
	}
	
	public void onDisable() {
		MessageService.ConsoleLog(ChatColor.RED + "WarpsYML disabled!");
	}
	
}
