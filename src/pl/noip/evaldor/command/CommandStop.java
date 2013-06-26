package pl.noip.evaldor.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;
import pl.noip.evaldor.util.StringUtils;

public class CommandStop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!Evaldor.hasPerm(sender, "evaldor.superadmin")) {
			sender.sendMessage(Messages.noPermission);
			return true;
		}
		Bukkit.getServer().setWhitelist(true);
		Player[] plr = Bukkit.getOnlinePlayers();
		if (args.length == 0) {
			if (plr.length == 1) {
				Bukkit.getServer().getPlayer(plr[0].getName()).kickPlayer("§cPolaczenie utracone:" + "\n" + Messages.serverShutdown.replaceAll("/n", "\n") + "\n" + "§6Komendy /stop uzyl: " + Evaldor.getName(sender));
				Bukkit.shutdown();
			} else {
			for (int i = 1; i < plr.length; i++) { Bukkit.getServer().getPlayer(plr[i].getName()).kickPlayer("§cPolaczenie utracone:" + "\n" + Messages.serverShutdown.replaceAll("/n", "\n") + "§6Komendy /stop uzyl: " + Evaldor.getName(sender) ); }	}	} else {
			if (plr.length == 1) {
				String msg = args[0];
				for (int i = 1; i < args.length; i++) { msg += " " + args[i]; }
				msg = StringUtils.colorize(msg.replaceAll("/n", "\n"));
				Bukkit.getServer().setWhitelist(true);
				Bukkit.getServer().getPlayer(plr[0].getName()).kickPlayer("§cPolaczenie utracone:" + "\n" + msg + "\n" + "§6Komendy /stop uzyl: " + Evaldor.getName(sender));
				Bukkit.shutdown();
		} else {
			String msg = args[0];
			for (int i = 1; i < args.length; i++) { msg += " " + args[i]; }
			msg = StringUtils.colorize(msg.replaceAll("/n", "\n"));
			Bukkit.getServer().setWhitelist(true);
			for (int i = 1; i < plr.length; i++) { Bukkit.getServer().getPlayer(plr[i].getName()).kickPlayer("§cPolaczenie utracone:" + "\n" + msg + "\n" + "§6Komendy /stop uzyl: " + Evaldor.getName(sender)); }
			Bukkit.shutdown();
			}
			
		}

		return true;
	}
	

}
