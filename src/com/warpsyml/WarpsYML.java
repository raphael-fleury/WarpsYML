package com.warpsyml;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.warpsyml.commands.SetWarpCommand;
import com.warpsyml.commands.WarpCommand;
import com.warpsyml.commands.WarpsCommand;
import com.warpsyml.entities.Colors;
import com.warpsyml.services.ColorService;

public class WarpsYML extends JavaPlugin {
	
	public static JavaPlugin plugin;
	public static Colors colors;
	
	public void loadCommands() {
		this.getCommand("setwarp").setExecutor(new SetWarpCommand());
		this.getCommand("warps").setExecutor(new WarpsCommand());
		this.getCommand("warp").setExecutor(new WarpCommand());
	}
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		colors = ColorService.loadColors(getConfig());
		loadCommands();
		
		Bukkit.getConsoleSender().sendMessage(colors.success + "WarpsYML enabled!");		
		plugin = this;
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "WarpsYML disabled!");
	}
	
}
