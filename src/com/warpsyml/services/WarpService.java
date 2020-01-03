package com.warpsyml.services;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import com.warpsyml.WarpsYML;
import com.warpsyml.entities.Warp;

public class WarpService {
	
	public static File path() {
		return new File(WarpsYML.plugin.getDataFolder(), "warps");
	}
	
	public static File warpFile(String warpName) {
		return new File(path(), warpName);
	}
	
	public static YamlConfiguration warpYmlFile(String warpName) {
		return YamlConfiguration.loadConfiguration(warpFile(warpName ));
	}
	
	public static ArrayList<String> warpList() {
		ArrayList<String> list = new ArrayList<String>();
		
		if(path().list() == null) //list of file names with extension
			return list;
		
		String[] names = path().list();
		for (int i = 0; i < names.length; i++) {
			if (names[i].contains(".yml")) {
				list.add(names[i].replaceAll(".yml", ""));
			}							
		}
		return list;
	}
	
	public static ArrayList<Warp> warps() {
		ArrayList<Warp> warps = new ArrayList<Warp>();
		ArrayList<String> list = warpList();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(".yml")) {
				String name = list.get(i).replaceAll(".yml", "");
				Warp warp = readYml(name);
				if (warp == null) {
					MessageService.consoleError("Error on loading warp " + list.get(i));
				}
				else {
					warps.add(warp);
				}
			}							
		}
		return warps;
	}
	
	public static Warp readYml(String warpName) {
		File path = warpFile(warpName + ".yml");
		
		if (!path.exists()) {
			return null;
		}
		
		YamlConfiguration config = YamlConfiguration.loadConfiguration(path);
		World world = Bukkit.getServer().getWorld(config.getString("world"));
		
		try {
			return new Warp(warpName, new Location(world,
					config.getDouble("x"),
					config.getDouble("y"),
					config.getDouble("z"),
					(float)config.getDouble("yaw"),
					(float)config.getDouble("pitch")
			));
		}
		catch (Exception e) {
			MessageService.consoleError("Error on loading warp " + warpName);
			return null;
		}
	}
	
}
