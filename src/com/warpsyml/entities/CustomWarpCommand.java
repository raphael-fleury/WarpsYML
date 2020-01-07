package com.warpsyml.entities;

import org.bukkit.command.CommandSender;

import com.warpsyml.services.MessageService;
import com.warpsyml.services.WarpService;

public class CustomWarpCommand extends CustomCommand {

	private Warp warp;
	
	public CustomWarpCommand(CommandSender sender, String label, Warp warp) {
		super(sender, label);
		this.warp = warp;
	}
	
	public CustomWarpCommand(CommandSender sender, String label, String warp) throws Exception {
		super(sender, label);
		this.warp = WarpService.getWarp(warp);
		if (this.warp == null) {
			throw new Exception(MessageService.getMessage("warp-not-set"));
		}
	}

	@Override
	public String getMessage(String key) {
		String string = super.getMessage(key);
		string = string.replaceAll("%warpname%", warp.getName());
		string = string.replaceAll("%warplocation%", warp.getLocationToString());
		return string;
	}
}
