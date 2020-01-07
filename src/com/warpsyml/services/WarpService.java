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
	
	public static File getPath() {
		return new File(WarpsYML.plugin.getDataFolder(), "warps");
	}
	
	public static boolean noWarps() {
		return getPath().list() == null;
	}
	
	public static File getFile(String warpName) {
		return new File(getPath(), warpName + ".yml");
	}
	
	public static YamlConfiguration getYml(String warpName) {
		return YamlConfiguration.loadConfiguration(getFile(warpName + ".yml"));
	}
	
	public static Warp getWarp(String warpName) {
		File path = getFile(warpName);
		
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
			return null;
		}
	}
	
	public static ArrayList<String> getNamesList() {
		ArrayList<String> list = new ArrayList<String>();
		
		if(noWarps()) //list of file names with extension
			return list;
		
		String[] names = getPath().list();
		for (int i = 0; i < names.length; i++) {
			if (names[i].contains(".yml")) {
				list.add(names[i].replaceAll(".yml", ""));
			}							
		}
		return list;
	}

	public static ArrayList<Warp> getList() {
		ArrayList<Warp> warps = new ArrayList<Warp>();
		ArrayList<String> list = getNamesList();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(".yml")) {
				String name = list.get(i).replaceAll(".yml", "");
				Warp warp = getWarp(name);
				if (warp == null) {
					MessageService.consoleLog("loading-warp-error");
				}
				else {
					warps.add(warp);
				}
			}							
		}
		return warps;
	}
		
	public static String getListToString() {
		ArrayList<String> warpList = getNamesList();
		String text = "";
		String separator = MessageService.getMessage("warp-list-separator");
		
		for(int i = 0; i < warpList.size(); i++) {
			text += warpList.get(i);
			if (i < warpList.size() - 1)
				text += separator;
		}
		return text;
	}
	
}
