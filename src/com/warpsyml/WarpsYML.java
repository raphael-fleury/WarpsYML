package com.warpsyml;

import org.bukkit.plugin.java.JavaPlugin;

import com.warpsyml.commands.DelWarpCommand;
import com.warpsyml.commands.SetWarpCommand;
import com.warpsyml.commands.WarpCommand;
import com.warpsyml.commands.WarpsCommand;
import com.warpsyml.services.MessageService;

public class WarpsYML extends JavaPlugin {
	
	public static JavaPlugin plugin;
	
	public void loadCommands() {
		this.getCommand("setwarp").setExecutor(new SetWarpCommand());
		this.getCommand("delwarp").setExecutor(new DelWarpCommand());
		this.getCommand("warps").setExecutor(new WarpsCommand());
		this.getCommand("warp").setExecutor(new WarpCommand());
	}
	
	public void onEnable() {
		plugin = this;
		loadCommands();
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		saveResource("messages.yml", false);
		MessageService.consoleLog("on-enable");		
	}
	
	public void onDisable() {
		MessageService.consoleLog("on-disable");
	}
	
}
