package pl.noip.evaldor.command;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

import com.google.common.collect.ImmutableList;

public class CommandWeather implements CommandExecutor, TabExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		World w;
		if (!(sender instanceof Player)) {
			w = null;
		} else {
			w = ((Player) sender).getWorld();
		}
		int dur = 6000;
		if (args.length == 2) {
			try {
				dur = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				if (Bukkit.getWorld(args[1]) == null) {
					sender.sendMessage(Messages.weatherUnknownWorld.replaceAll("\\{world\\}", args[1]));
					return true;
				} else {
					w = Bukkit.getWorld(args[1]);
				}
			}
		} else if (args.length == 3) {
			try {
				dur = Integer.parseInt(args[1]);
				if (Bukkit.getWorld(args[2]) == null) {
					sender.sendMessage(Messages.weatherUnknownWorld.replaceAll("\\{world\\}", args[2]));
					return true;
				} else {
					w = Bukkit.getWorld(args[2]);
				}
			} catch (NumberFormatException e) {
				try {
					dur = Integer.parseInt(args[2]);
					if (Bukkit.getWorld(args[1]) == null) {
						sender.sendMessage(Messages.weatherUnknownWorld.replaceAll("\\{world\\}", args[1]));
						return true;
					} else {
						w = Bukkit.getWorld(args[1]);
					}
				} catch (NumberFormatException e2) {
					sender.sendMessage(Messages.wrongUsage + command.getUsage());
					return true;
				}
			}
		}
//		if (w != null) {
//			if (!Evaldor.hasPerm(sender, "evaldor.weather") && !Evaldor.hasPerm(sender, "evaldor.weather.*") && !Evaldor.hasPerm(sender, "evaldor.weather.*."+w.getName())) {
//				Evaldor.noPerm(sender);
//				Bukkit.getLogger().warning("to tutaj!");
//				return true;
//			}
//		}
		if (args.length >= 1) {
			if (w == null && !(sender instanceof Player)) {
				sender.sendMessage(Messages.wrongUsage + command.getUsage());
				return true;
			}
			if (args[0].equalsIgnoreCase("get")) {
				if (!Evaldor.hasPerm(sender, "evaldor.weather.get")) {
					Evaldor.noPerm(sender);
					return true;
				}
				if (((Player) sender).getWorld().getWeatherDuration() == 0 && ((Player) sender).getWorld().getThunderDuration() == 0) {
					sender.sendMessage(Messages.weatherGet.replaceAll("\\{state\\}", Messages.weatherStates[0]));
				} else if (((Player) sender).getWorld().getWeatherDuration() > 0 && ((Player) sender).getWorld().getThunderDuration() == 0) {
					sender.sendMessage(Messages.weatherGet.replaceAll("\\{state\\}", Messages.weatherStates[1]));
				} else {
					sender.sendMessage(Messages.weatherGet.replaceAll("\\{state\\}", Messages.weatherStates[2]));
				}
				return true;
			} else if (args[0].equalsIgnoreCase("sun") || args[0].equalsIgnoreCase("clear")) {
				if (!Evaldor.hasPerm(sender, "evaldor.weather") && !Evaldor.hasPerm(sender, "evaldor.weather.sun") && !Evaldor.hasPerm(sender, "evaldor.weather.sun."+w.getName())) {
					Evaldor.noPerm(sender);
					return true;
				}
				w.setStorm(false);
				w.setThundering(false);
				w.setWeatherDuration(dur);
				w.setThunderDuration(dur);
				Bukkit.getServer().broadcastMessage(Messages.weatherChanged.replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{duration\\}", ""+(dur/20)).replaceAll("\\{world\\}", w.getName()).replaceAll("\\{state\\}", Messages.weatherStates[0]));
				return true;
			} else if (args[0].equalsIgnoreCase("rain") || args[0].equalsIgnoreCase("storm")) {
				if (!Evaldor.hasPerm(sender, "evaldor.weather.storm") && !Evaldor.hasPerm(sender, "evaldor.weather")) {
					Evaldor.noPerm(sender);
					return true;
				}
				w.setStorm(true);
				w.setThundering(false);
				w.setWeatherDuration(dur);
				w.setThunderDuration(dur);
				Bukkit.getServer().broadcastMessage(Messages.weatherChanged.replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{duration\\}", ""+(dur/20)).replaceAll("\\{world\\}", w.getName()).replaceAll("\\{state\\}", Messages.weatherStates[1]));
				return true;
			} else if (args[0].equalsIgnoreCase("thunderstorm")) {
				if (!Evaldor.hasPerm(sender, "evaldor.weather.thunderstorm") && !Evaldor.hasPerm(sender, "evaldor.weather")) {
					Evaldor.noPerm(sender);
					return true;
				}
				w.setStorm(true);
				w.setThundering(true);
				w.setWeatherDuration(dur);
				w.setThunderDuration(dur);
				Bukkit.getServer().broadcastMessage(Messages.weatherChanged.replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{duration\\}", ""+(dur/20)).replaceAll("\\{world\\}", w.getName()).replaceAll("\\{state\\}", Messages.weatherStates[2]));
				return true;
			} else {
				sender.sendMessage(Messages.wrongUsage + command.getUsage());
				return true;
			}
		} else {
			sender.sendMessage(Messages.wrongUsage + command.getUsage());
			return true;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			return StringUtil.copyPartialMatches(args[0], (List<String>) ImmutableList.of("sun", "storm", "thunderstorm", "get"), new ArrayList<String>());
		} else if (args.length == 2) {
			List<String> list = new ArrayList<String>();
			for (World w : Bukkit.getServer().getWorlds()) {
				list.add(w.getName());
			}
			return StringUtil.copyPartialMatches(args[1], list, new ArrayList<String>());
		}
		return new ArrayList<String>();
	}
}
