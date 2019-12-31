package com.warpsyml.services;

//import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import com.warpsyml.entities.Warp;

public class WarpService {

	/*public static Warp[] LoadWarps(YamlConfiguration config) {
		Object[] list = config.getList("warps").toArray();
		List<Warp> warps;
		
		for (int i = 0; i < list.length; i++) {
			warps.add(list[i]);
		}
	}*/
	
	public static Warp ReadYml(String warpName, YamlConfiguration config) {
		String path = warpName + ".location";
		if (!config.contains(warpName)) {
			return null;
		}

		World world = Bukkit.getServer().getWorld(config.getString(path + ".world"));

		return new Warp("warpName", new Location(world,
				config.getDouble(path + ".x"),
				config.getDouble(path + ".y"),
				config.getDouble(path + ".z"),
				(float)config.getDouble(path + ".yaw"),
				(float)config.getDouble(path + ".pitch")
		));
	}
}
