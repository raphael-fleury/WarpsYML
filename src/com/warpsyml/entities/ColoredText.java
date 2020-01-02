package com.warpsyml.entities;

import org.bukkit.ChatColor;

public class ColoredText {

	public String text;
	public ChatColor color;
	
	public ColoredText(String text, ChatColor color) {
		this.text = text;
		this.color = color;
	}
	
	public String ToString() {
		return color + text;
	}
}
