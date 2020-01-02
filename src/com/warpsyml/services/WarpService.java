package com.warpsyml.services;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.warpsyml.WarpsYML;
import com.warpsyml.entities.Warp;

public class WarpService {
	
	public static File path() {
		return new File(WarpsYML.plugin.getDataFolder(), "warps");
	}
	
	public static File warpFile(String warpName) {
		return new File(path(), warpName + ".yml");
	}
	
	public static YamlConfiguration warpYmlFile(String warpName) {
		return YamlConfiguration.loadConfiguration(warpFile(warpName));
	}
	
	public static Warp[] warpList() {
		String[] names = path().list();
		Warp[] warps = new Warp[names.length];
		for (int i = 0; i < names.length; i++) {
			warps[i] = ReadYml(names[i]);
		}
		return warps;
	}
	
	public static Warp ReadYml(String warpName) {
		File path = warpFile(warpName);
		if (!path.exists()) {
			return null;
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(path);
		World world = Bukkit.getServer().getWorld(config.getString("world"));

		return new Warp("warpName", new Location(world,
				config.getDouble("x"),
				config.getDouble("y"),
				config.getDouble("z"),
				(float)config.getDouble("yaw"),
				(float)config.getDouble("pitch")
		));
	}
	
}
