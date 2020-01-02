package com.warpsyml.entities;

import org.bukkit.ChatColor;

public class Colors {

	public ChatColor error;
	public ChatColor success;
	public ChatColor neutral;
	
	public Colors(ChatColor error, ChatColor success, ChatColor neutral) {
		this.error = error;
		this.success = success;
		this.neutral = neutral;
	}
}
