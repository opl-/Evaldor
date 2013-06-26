package lolwut.command;

import java.util.HashMap;
import java.util.Map;

import lolwut.Command1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_5_R3.command.CraftConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandTPA implements CommandExecutor {

	public static Map<Player, Player> tpamap;

	public CommandTPA() {
		tpamap = new HashMap<Player, Player>();
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (label.equalsIgnoreCase("tpa")) {
			if (sender instanceof CraftConsoleCommandSender) {
				sender.sendMessage(ChatColor.RED
						+ "Ta komenda moze zostac wykonana tylko jako gracz.");
			} else if (sender instanceof Player) {
				if (args.length == 1) {
					String senderIGN = sender.getName();
					String sentto = args[0];
					Player target = Bukkit.getServer().getPlayer(sentto);
					if (target == null) {
						sender.sendMessage(ChatColor.WHITE + "["
								+ ChatColor.RED + "BLAD" + ChatColor.WHITE
								+ "]" + ChatColor.YELLOW
								+ " Podany gracz nie jest online.");

					} else {
						tpamap.put(target, (Player) sender);
						sender.sendMessage(ChatColor.WHITE + "["
								+ ChatColor.DARK_GREEN + "SUKCES"
								+ ChatColor.WHITE + "]" + ChatColor.YELLOW
								+ " Prosba o teleportacje zostala wyslana.");
						target.sendMessage(ChatColor.WHITE + "["
								+ ChatColor.YELLOW + "INFO" + ChatColor.WHITE
								+ "]" + ChatColor.YELLOW + " Gracz " + sender.getName()
								+ " prosi o teleportacje.");
						target.sendMessage(ChatColor.YELLOW + "Wpisz "
								+ ChatColor.GREEN + "/tpaccept "
								+ ChatColor.YELLOW + "aby zaakceptowac, albo "
								+ ChatColor.RED + "/tpdeny" + ChatColor.YELLOW
								+ " aby odrzucic");
						Bukkit.getScheduler().runTaskLater(Command1.inst, new tpreqtime(target), 300);
					}
				}
			}
		} else if (label.equalsIgnoreCase("tpaccept")) {
			
			
		}
		return true;
	}
	
	public class tpreqtime implements Runnable {
		private Player key;
		public tpreqtime(Player key) {
			this.key = key;
		}
		public void run() {
			if (tpamap.containsKey(key)) {
				key.sendMessage(ChatColor.WHITE + "["
						+ ChatColor.YELLOW + "INFO" + ChatColor.WHITE
						+ "]" + ChatColor.YELLOW + " Zadanie teleportacji od " + tpamap.get(key).getName() + " wygaslo.");
				Bukkit.getServer().getPlayer(tpamap.get(key).getName()).sendMessage(ChatColor.WHITE + "["
						+ ChatColor.YELLOW + "INFO" + ChatColor.WHITE
						+ "]" + ChatColor.YELLOW + " Zadanie teleportacji do " + key.getName() + " wygaslo.");
				tpamap.remove(key);

			}
		}
	}
	
}
