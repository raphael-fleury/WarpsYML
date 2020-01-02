package com.warpsyml.entities;

import org.bukkit.ChatColor;

import com.warpsyml.entities.enums.MessageType;

public class Colors {

	public ChatColor error;
	public ChatColor success;
	public ChatColor neutral;
	
	public Colors(ChatColor error, ChatColor success, ChatColor neutral) {
		this.error = error;
		this.success = success;
		this.neutral = neutral;
	}
	
	public ChatColor getColor(MessageType type) {
		switch (type) {
			case NEUTRAL:
				return neutral;
			case SUCCESS:
				return success;
			case ERROR:
				return error;

			default:
				return neutral;
		}
	}
}
