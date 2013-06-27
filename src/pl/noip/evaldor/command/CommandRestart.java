package pl.noip.evaldor.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;
import pl.noip.evaldor.util.StringUtils;

public class CommandRestart implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!Evaldor.hasPerm(sender, "evaldor.superadmin")) {
			Evaldor.noPerm(sender);
			return true;
		}
		Bukkit.getServer().setWhitelist(true);
		Player[] plr = Bukkit.getOnlinePlayers();
		String reason = "";
		if (args.length == 0) reason = Evaldor.inst().getString("default-restart-message");
		else {
			reason = args[0];
			for (int i = 1; i < args.length; i++) { reason += " " + args[i]; }
		}
		reason = StringUtils.colorize(reason);
		reason = Messages.stopMessage.replaceAll("\\{reason\\}", reason).replaceAll("/n", "\n").replaceAll("\\{player\\}", Evaldor.getName(sender));
		for (int i = 0; i < plr.length; i++) { // Pierwszy element tablicy to 0 a nie 1 ;)
			Bukkit.getServer().getPlayer(plr[i].getName()).kickPlayer(reason);
		}
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "restart");
		return true;
	}
}
