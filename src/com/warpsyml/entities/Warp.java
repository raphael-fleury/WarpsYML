package com.warpsyml.entities;

import org.bukkit.Location;

import com.warpsyml.services.WarpService;

public class Warp {

	private String name;
	private Location location;
	
	public Warp(String name, Location location) {
		this.name = name;
		this.location = location;
	}
	
	public String getName() { return name; }
	public Location getLocation() { return location; }

	public String getLocationToString() {
		return location.getWorld().getName() + ", " +
			Math.round(location.getX()) + ", " + 
			Math.round(location.getY()) + ", " + 
			Math.round(location.getZ());
	}
	
	public String toString() {
		return name + ": " + getLocationToString();
	}
	
	public boolean exists() {
		return WarpService.getWarp(name) != null;
	}
}
