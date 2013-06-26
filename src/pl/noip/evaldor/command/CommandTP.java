package pl.noip.evaldor.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_5_R3.command.CraftConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandTP implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof CraftConsoleCommandSender) {
			sender.sendMessage("LOL");
		} else if (sender instanceof Player) {
			if (args.length == 1) {
				Player target = (Bukkit.getServer().getPlayer(args[0]));
				if (target == null) {
					sender.sendMessage(ChatColor.WHITE + "["
							+ ChatColor.DARK_RED + "BLAD" + ChatColor.WHITE
							+ "]" + ChatColor.YELLOW + " Gracz "
							+ args[0].toString() + " nie jest online!");
				} else {
					((Player) sender).teleport(sender.getServer()
							.getPlayer(args[0]).getLocation());
					sender.sendMessage(ChatColor.WHITE + "["
							+ ChatColor.DARK_GREEN + "OK" + ChatColor.WHITE
							+ "] " + ChatColor.YELLOW + "Przeteleportowano do "
							+ args[0]);
				}
			} else if (args.length == 2) {
				Player target = (Bukkit.getServer().getPlayer(args[0]));
				Player target2 = (Bukkit.getServer().getPlayer(args[1]));
				if (target != null && target2 != null) {
					sender.getServer()
							.getPlayer(args[0])
							.teleport(
									sender.getServer().getPlayer(args[1])
											.getLocation());
					sender.sendMessage(ChatColor.WHITE + "["
							+ ChatColor.DARK_GREEN + "SUKCES" + ChatColor.WHITE
							+ "] " + ChatColor.YELLOW
							+ "Przeteleportowano gracza " + args[0]
							+ " do gracza " + args[1]);
					Bukkit.getServer()
							.getPlayer(args[0])
							.sendMessage(
									ChatColor.WHITE + "[" + ChatColor.YELLOW
											+ "INFO" + ChatColor.WHITE + "] "
											+ ChatColor.YELLOW
											+ sender.getName()
											+ " przeteleportowal Cie do "
											+ args[1]);
					Bukkit.getServer()
							.getPlayer(args[1])
							.sendMessage(
									ChatColor.WHITE + "[" + ChatColor.YELLOW
											+ "INFO" + ChatColor.WHITE + "] "
											+ ChatColor.YELLOW
											+ sender.getName()
											+ " przeteleportowal do Ciebie "
											+ args[0]);
				} else if (target == null && target2 == null) {
					sender.sendMessage(ChatColor.WHITE + "["
							+ ChatColor.DARK_RED + "BLAD" + ChatColor.WHITE
							+ "]" + ChatColor.YELLOW
							+ "Zaden z podanych graczy nie jest online!");
				} else if (target == null) {
					sender.sendMessage(ChatColor.WHITE + "["
							+ ChatColor.DARK_RED + "BLAD" + ChatColor.WHITE
							+ "]" + ChatColor.YELLOW + " Gracz "
							+ args[0].toString() + " nie jest online!");
				} else if (target2 == null) {
					sender.sendMessage(ChatColor.WHITE + "["
							+ ChatColor.DARK_RED + "BLAD" + ChatColor.WHITE
							+ "]" + ChatColor.YELLOW + " Gracz "
							+ args[1].toString() + " nie jest online!");
				}
			}

		}
		return true;
	}
}
