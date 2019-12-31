package com.warpsyml;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.warpsyml.commands.SetWarpCommand;
import com.warpsyml.commands.WarpsCommand;
import com.warpsyml.entities.Warp;

public class Main extends JavaPlugin {
	
	public static JavaPlugin plugin;
	public static List<Warp> warps = new ArrayList<Warp>();
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "WarpsYML enabled!");
		this.getCommand("setwarp").setExecutor(new SetWarpCommand());
		this.getCommand("warps").setExecutor(new WarpsCommand());
		
		plugin = this;
		
		saveConfig();
		    
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "WarpsYML disabled!");
	}
	
}
