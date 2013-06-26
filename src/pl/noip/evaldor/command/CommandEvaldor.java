package pl.noip.evaldor.command;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

import com.google.common.collect.ImmutableList;

public class CommandEvaldor implements CommandExecutor, TabExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				Evaldor.inst().reloadConfig();
				sender.sendMessage("§2Config reloaded!");
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

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return StringUtil.copyPartialMatches(args[0], (List<String>) ImmutableList.of("reload"), new ArrayList<String>(1));
	}
}
