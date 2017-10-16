package me.bobcatsss.perworldspawn.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bobcatsss.perworldspawn.Core;
import net.md_5.bungee.api.ChatColor;

public class CommandSpawn implements CommandExecutor {
	
	private Core plugin;
	public CommandSpawn(Core pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(args.length == 0) {
				if(plugin.getLocation(p.getWorld().getName()) != null) {
					p.teleport(plugin.getLocation(p.getWorld().getName()));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SpawnTp").replace("%world%", p.getWorld().getName())));
					return true;
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.No-SpawnSet")).replace("%world%", p.getWorld().getName()));
				return true;
			}
			Player target = Bukkit.getPlayerExact(args[0]);
			if(target != null) {
				if(plugin.getLocation(target.getWorld().getName()) != null) {
					target.teleport(plugin.getLocation(target.getWorld().getName()));
					target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SpawnTp").replace("%world%", p.getWorld().getName())));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Spawn-Other")).replace("%world%", target.getWorld().getName()).replace("%target%", target.getName()));
					return true;
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.No-SpawnSet")).replace("%world%", p.getWorld().getName()));
				return true;
			}
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Player-Not-Online")).replace("%target%", args[0]));
			return true;
		}
		return false;
	}

}
