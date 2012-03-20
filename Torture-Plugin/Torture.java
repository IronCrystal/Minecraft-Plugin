package me.IronCrystal.Torture;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Torture extends JavaPlugin
{

	public final Logger logger = Logger.getLogger("Minecraft");

	public static Torture plugin;

	public static Permission permission = null;

	@Override
	public void onDisable()
	{

		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " is now disabled!");
	}

	@Override
	public void onEnable()
	{

		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been enabled.");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		Player player = null;
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		if (player == null || player.hasPermission("torture." + cmd.getName()))
		{
			if (cmd.getName().equalsIgnoreCase("thelp"))
			{
				if (args.length == 0)
				{
					sender.sendMessage(ChatColor.GREEN + "/thelp -shows this page");
					sender.sendMessage(ChatColor.GREEN + "/starve <player> <amount left> -depletes player's hunger bar");
					sender.sendMessage(ChatColor.GREEN + "/hurt <player> <damage> -hurts the player");
					sender.sendMessage(ChatColor.GREEN + "/ignite <player> -ignites the player on fire, painful death");
					sender.sendMessage(ChatColor.GREEN + "/die <player> -kills the player");
					sender.sendMessage(ChatColor.GREEN + "");
					sender.sendMessage(ChatColor.GREEN + "Type /thelp 2 to see the next page.");

					return true;
				}
				else if (args.length == 1)
				{
					int page = Integer.parseInt(args[0]);
					if (page == 2)
					{
						sender.sendMessage(ChatColor.GREEN + "/painful -leaves player with 1 health and empties hunger bar");
						sender.sendMessage(ChatColor.GREEN + "/blind <player> <time> -leaves the player blind");
						sender.sendMessage(ChatColor.GREEN + "/ill <player> <time> -makes them feel sick");
						sender.sendMessage(ChatColor.GREEN + "/hungry <player> <time> <1 - 4> -leaves the player terribly hungry");
						sender.sendMessage(ChatColor.GREEN + "/poison <player> <time> <1 - 4> -leaves the player poisoned");
						sender.sendMessage(ChatColor.GREEN + "");
						sender.sendMessage(ChatColor.GREEN + "Type /thelp 3 to see the next page.");
						return true;
					}
					else if (page == 3)
					{
						sender.sendMessage(ChatColor.GREEN + "/slow <player> <time> <1 - 4> -slows the player down");
						sender.sendMessage(ChatColor.GREEN + "/upsidedown <player> <time> -turns the player's world upside down!");
						sender.sendMessage(ChatColor.GREEN + "/fall <player> <distance> -makes the player fall from the sky");
						sender.sendMessage(ChatColor.GREEN + "/explode <player> -explodes the player, automatic death");
						sender.sendMessage(ChatColor.GREEN + "/creeper <player> -spawns creepers all around the player");
						sender.sendMessage(ChatColor.GREEN + "");
						sender.sendMessage(ChatColor.GREEN + "Type /help 4 to see the next page.");
					}
					else if (page == 4)
					{
						sender.sendMessage(ChatColor.GREEN + "/annoy <player> -annoys the player with villagers");
						sender.sendMessage(ChatColor.GREEN + "/rabid <player> -sends rabid wolves after the player");
						sender.sendMessage(ChatColor.GREEN + "/suffocate <player> -suffocates the player in a tower of sand");
						sender.sendMessage(ChatColor.GREEN + "/rainoffire <player> <number> -Drops firey arrows over the player");
						sender.sendMessage(ChatColor.GREEN + "/torture <player> <time> <1-4> -terrible things will come to pass");
						sender.sendMessage(ChatColor.GREEN + "");
						sender.sendMessage(ChatColor.GREEN + "Type /help 5 to see the next page.");
					}
					else if (page == 5)
					{
						sender.sendMessage(ChatColor.GREEN + "/spider <player> -spawns spiders all around the player");
						sender.sendMessage(ChatColor.GREEN + "/zombie <player> -spawns zombies all around the player");
						sender.sendMessage(ChatColor.GREEN + "/skeleton <player> -spawns skeletons all around the player");
						sender.sendMessage(ChatColor.GREEN + "/tstop <player> -stops all effects currently on the player");
						/*
						 * sender.sendMessage(ChatColor.GREEN + "");
						 * sender.sendMessage(ChatColor.GREEN + "");
						 * sender.sendMessage(ChatColor.GREEN +
						 * "Type /help 6 to see the next page.");
						 */
					}
					return true;
				}
			}
			else if (player == null && args.length == 0)
			{
				sender.sendMessage(ChatColor.RED + "Please type player name (/" + cmd.getName() + " <player>)");
			}
			else if (cmd.getName().equalsIgnoreCase("starve"))
			{
				if (args.length == 0 && player != null)
				{
					player.setFoodLevel(0);
					player.sendMessage(ChatColor.RED + "You are starving!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.setFoodLevel(0);
						sender.sendMessage(ChatColor.RED + "You starved " + args[0]);
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.setFoodLevel(Integer.parseInt(args[1]));
						sender.sendMessage(ChatColor.RED + "You starved " + args[0]);
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("hurt"))
			{
				if (args.length == 0 && player != null)
				{
					player.setHealth(1);
					player.sendMessage(ChatColor.RED + "You hurt yourself!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.setHealth(1);
						sender.sendMessage(ChatColor.RED + "You hurt " + args[0]);
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int health = player.getHealth() - Integer.parseInt(args[1]);
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.setHealth(health);
						sender.sendMessage(ChatColor.RED + "You hurt " + args[0]);
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("ignite"))
			{
				if (args.length == 0 && player != null)
				{
					player.setFireTicks(10000);
					player.sendMessage(ChatColor.RED + "You were set on fire!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.setFireTicks(10000);
						sender.sendMessage(ChatColor.RED + "You set " + args[0] + " on fire!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("die"))
			{
				if (args.length == 0 && player != null)
				{
					player.setHealth(0);
					player.sendMessage(ChatColor.RED + "You killed yourself!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.setHealth(0);
						sender.sendMessage(ChatColor.RED + "You killed " + args[0]);
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("painful"))
			{
				if (args.length == 0 && player != null)
				{
					player.setFoodLevel(0);
					player.setHealth(1);
					player.sendMessage(ChatColor.RED + "You are in pain!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.setFoodLevel(0);
						targetPlayer.setHealth(1);
						sender.sendMessage(ChatColor.RED + "You put " + args[0] + " in pain!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("poison"))
			{
				if (args.length == 0 && player != null)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 500, 0));
					sender.sendMessage(ChatColor.RED + "You are poisoned!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 500, 0));
						sender.sendMessage(ChatColor.RED + "You poisoned " + args[0] + ".");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, time, 0));
						sender.sendMessage(ChatColor.RED + "You poisoned " + args[0] + ".");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 3)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int strength = Integer.parseInt(args[2]) - 1;
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, time, strength));
						sender.sendMessage(ChatColor.RED + "You poisoned " + args[0] + ".");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("slow"))
			{
				if (args.length == 0 && player != null)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 500, 0));
					player.sendMessage(ChatColor.RED + "You have been slowed down!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 500, 0));
						sender.sendMessage(ChatColor.RED + "You slowed " + args[0] + " down.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, time, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, time, 0));
						sender.sendMessage(ChatColor.RED + "You slowed " + args[0] + " down.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 3)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int strength = Integer.parseInt(args[2]);
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, time, strength));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, time, strength));
						sender.sendMessage(ChatColor.RED + "You slowed " + args[0] + " down.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("upsidedown"))
			{
				if (args.length == 0 && player != null)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 15));
					player.sendMessage(ChatColor.RED + "Your world has been turn upside down!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 15));
						sender.sendMessage(ChatColor.RED + "You turned " + args[0] + "'s world upside down!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						int time = Integer.parseInt(args[1]) * 20;
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, time, 15));
						sender.sendMessage(ChatColor.RED + "You turned " + args[0] + "'s world upside down!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("blind"))
			{
				if (args.length == 0 && player != null)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 0));
					player.sendMessage(ChatColor.RED + "You are blind!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 0));
						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " blind.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, time, 0));
						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " blind.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("hungry"))
			{
				if (args.length == 0 && player != null)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 500, 0));
					player.sendMessage(ChatColor.RED + "You are hungry!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 500, 0));
						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " hungry.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, time, 0));
						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " hungry.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 3)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int strength = Integer.parseInt(args[2]) - 1;
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, time, strength));
						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " hungry.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("ill"))
			{
				if (args.length == 0 && player != null)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 500, 0));
					player.sendMessage(ChatColor.RED + "You are ill!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 500, 0));
						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " ill.");
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, time, 0));
						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " ill.");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}

			}
			else if (cmd.getName().equalsIgnoreCase("torture"))
			{
				if (args.length == 0 && player != null)
				{
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 500, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 500, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 0));
					player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 500, 0));
					player.sendMessage(ChatColor.RED + "You have been tortured!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 500, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 500, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 500, 0));
						sender.sendMessage(ChatColor.RED + "You tortured " + args[0] + ".");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, time, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, time, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, time, 0));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, time, 0));
						sender.sendMessage(ChatColor.RED + "You tortured " + args[0] + ".");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 3)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int strength = Integer.parseInt(args[2]) - 1;
						int time = Integer.parseInt(args[1]) * 20;
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, time, strength));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, time, strength));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, time, strength));
						targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, time, strength));
						sender.sendMessage(ChatColor.RED + "You tortured " + args[0] + ".");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("explode"))
			{
				if (args.length == 0 && player != null)
				{
					float explosionPower = 0F;
					player.getWorld().createExplosion(player.getLocation(), explosionPower);
					player.setHealth(0);
					player.sendMessage(ChatColor.RED + "You have exploded!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
						float explosionPower = 0F;
						targetPlayer.getWorld().createExplosion(targetPlayer.getLocation(), explosionPower);
						targetPlayer.setHealth(0);
						sender.sendMessage(ChatColor.RED + "You exploded " + args[0] + ".");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("fall"))
			{

				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					Location NewPlayerLocation = new Location(currentWorld, x, y + 13, z);
					player.teleport(NewPlayerLocation);
					player.sendMessage(ChatColor.RED + "You have fallen!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location targetPlayerLocation = new Location(currentTargetWorld, x, y + 13, z);
						targetPlayer.teleport(targetPlayerLocation);

						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " fall!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
				else if (args.length == 2)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						int distance = Integer.parseInt(args[1]);
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location targetPlayerLocation = new Location(currentTargetWorld, x, y + distance, z);
						targetPlayer.teleport(targetPlayerLocation);

						sender.sendMessage(ChatColor.RED + "You made " + args[0] + " fall!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("creeper"))
			{
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					Location Creeper1 = new Location(currentWorld, x + 1, y, z);
					Location Creeper2 = new Location(currentWorld, x - 1, y, z);
					Location Creeper3 = new Location(currentWorld, x, y, z + 1);
					Location Creeper4 = new Location(currentWorld, x, y, z - 1);

					Creeper c1 = (Creeper) player.getWorld().spawnCreature(Creeper1, EntityType.CREEPER);
					Creeper c2 = (Creeper) player.getWorld().spawnCreature(Creeper2, EntityType.CREEPER);
					Creeper c3 = (Creeper) player.getWorld().spawnCreature(Creeper3, EntityType.CREEPER);
					Creeper c4 = (Creeper) player.getWorld().spawnCreature(Creeper4, EntityType.CREEPER);

					c1.setTarget(player);
					c2.setTarget(player);
					c3.setTarget(player);
					c4.setTarget(player);

					player.sendMessage(ChatColor.RED + "You spawned creepers around yourself!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location Creeper1 = new Location(currentTargetWorld, x + 1, y, z);
						Location Creeper2 = new Location(currentTargetWorld, x - 1, y, z);
						Location Creeper3 = new Location(currentTargetWorld, x, y, z + 1);
						Location Creeper4 = new Location(currentTargetWorld, x, y, z - 1);

						Creeper c1 = (Creeper) player.getWorld().spawnCreature(Creeper1, EntityType.CREEPER);
						Creeper c2 = (Creeper) player.getWorld().spawnCreature(Creeper2, EntityType.CREEPER);
						Creeper c3 = (Creeper) player.getWorld().spawnCreature(Creeper3, EntityType.CREEPER);
						Creeper c4 = (Creeper) player.getWorld().spawnCreature(Creeper4, EntityType.CREEPER);

						c1.setTarget(player);
						c2.setTarget(player);
						c3.setTarget(player);
						c4.setTarget(player);

						sender.sendMessage(ChatColor.RED + "You spawned creepers near " + args[0] + "!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("annoy"))
			{
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					Location Villager1 = new Location(currentWorld, x + 1, y, z);
					Location Villager2 = new Location(currentWorld, x - 1, y, z);
					Location Villager3 = new Location(currentWorld, x, y, z + 1);
					Location Villager4 = new Location(currentWorld, x, y, z - 1);
					Location Villager5 = new Location(currentWorld, x + 2, y, z);
					Location Villager6 = new Location(currentWorld, x - 2, y, z);
					Location Villager7 = new Location(currentWorld, x, y, z + 2);
					Location Villager8 = new Location(currentWorld, x, y, z - 2);

					Villager v1 = (Villager) player.getWorld().spawnCreature(Villager1, EntityType.VILLAGER);
					Villager v2 = (Villager) player.getWorld().spawnCreature(Villager2, EntityType.VILLAGER);
					Villager v3 = (Villager) player.getWorld().spawnCreature(Villager3, EntityType.VILLAGER);
					Villager v4 = (Villager) player.getWorld().spawnCreature(Villager4, EntityType.VILLAGER);
					Villager v5 = (Villager) player.getWorld().spawnCreature(Villager5, EntityType.VILLAGER);
					Villager v6 = (Villager) player.getWorld().spawnCreature(Villager6, EntityType.VILLAGER);
					Villager v7 = (Villager) player.getWorld().spawnCreature(Villager7, EntityType.VILLAGER);
					Villager v8 = (Villager) player.getWorld().spawnCreature(Villager8, EntityType.VILLAGER);

					v1.setTarget(player);
					v2.setTarget(player);
					v3.setTarget(player);
					v4.setTarget(player);
					v5.setTarget(player);
					v6.setTarget(player);
					v7.setTarget(player);
					v8.setTarget(player);

					player.sendMessage(ChatColor.RED + "You annoyed yourself!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location Villager1 = new Location(currentTargetWorld, x + 1, y, z);
						Location Villager2 = new Location(currentTargetWorld, x - 1, y, z);
						Location Villager3 = new Location(currentTargetWorld, x, y, z + 1);
						Location Villager4 = new Location(currentTargetWorld, x, y, z - 1);
						Location Villager5 = new Location(currentTargetWorld, x + 2, y, z);
						Location Villager6 = new Location(currentTargetWorld, x - 2, y, z);
						Location Villager7 = new Location(currentTargetWorld, x, y, z + 2);
						Location Villager8 = new Location(currentTargetWorld, x, y, z - 2);

						Villager v1 = (Villager) targetPlayer.getWorld().spawnCreature(Villager1, EntityType.VILLAGER);
						Villager v2 = (Villager) targetPlayer.getWorld().spawnCreature(Villager2, EntityType.VILLAGER);
						Villager v3 = (Villager) targetPlayer.getWorld().spawnCreature(Villager3, EntityType.VILLAGER);
						Villager v4 = (Villager) targetPlayer.getWorld().spawnCreature(Villager4, EntityType.VILLAGER);
						Villager v5 = (Villager) targetPlayer.getWorld().spawnCreature(Villager5, EntityType.VILLAGER);
						Villager v6 = (Villager) targetPlayer.getWorld().spawnCreature(Villager6, EntityType.VILLAGER);
						Villager v7 = (Villager) targetPlayer.getWorld().spawnCreature(Villager7, EntityType.VILLAGER);
						Villager v8 = (Villager) targetPlayer.getWorld().spawnCreature(Villager8, EntityType.VILLAGER);

						v1.setTarget(player);
						v2.setTarget(player);
						v3.setTarget(player);
						v4.setTarget(player);
						v5.setTarget(player);
						v6.setTarget(player);
						v7.setTarget(player);
						v8.setTarget(player);

						sender.sendMessage(ChatColor.RED + "You annoyed " + args[0] + " with villagers!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("rabid"))
			{
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					Location Wolf1 = new Location(currentWorld, x + 5, y, z);
					Location Wolf2 = new Location(currentWorld, x + 5, y, z - 1);
					Location Wolf3 = new Location(currentWorld, x + 5, y, z + 1);

					Wolf w1 = (Wolf) player.getWorld().spawnCreature(Wolf1, EntityType.WOLF);
					Wolf w2 = (Wolf) player.getWorld().spawnCreature(Wolf2, EntityType.WOLF);
					Wolf w3 = (Wolf) player.getWorld().spawnCreature(Wolf3, EntityType.WOLF);
					player.sendMessage(ChatColor.RED + "You are being chased by rabid wolves!");

					w1.setAngry(true);
					w2.setAngry(true);
					w3.setAngry(true);
					w1.setTarget(player);
					w2.setTarget(player);
					w3.setTarget(player);

					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location Wolf1 = new Location(currentTargetWorld, x + 5, y, z - 1);
						Location Wolf2 = new Location(currentTargetWorld, x + 5, y, z);
						Location Wolf3 = new Location(currentTargetWorld, x + 5, y, z + 1);

						Wolf w1 = (Wolf) targetPlayer.getWorld().spawnCreature(Wolf1, EntityType.WOLF);
						Wolf w2 = (Wolf) targetPlayer.getWorld().spawnCreature(Wolf2, EntityType.WOLF);
						Wolf w3 = (Wolf) targetPlayer.getWorld().spawnCreature(Wolf3, EntityType.WOLF);

						w1.setAngry(true);
						w2.setAngry(true);
						w3.setAngry(true);
						w1.setTarget(targetPlayer);
						w2.setTarget(targetPlayer);
						w3.setTarget(targetPlayer);

						sender.sendMessage(ChatColor.RED + "You spawned rabid wolves near " + args[0] + "!");

						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("suffocate"))
			{
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					int count1 = 0;
					int count2 = 0;
					int count3 = 0;
					int count4 = 0;
					int count5 = 0;
					int count6 = 0;
					int count7 = 0;
					int count8 = 0;
					int count9 = 0;
					y = y - 1;
					x = x + 1;
					while (count2 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count2++;
					}
					y = y - count2;
					x = x - 2;
					while (count3 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count3++;
					}
					y = y - count3;
					x = x + 1;
					z = z + 1;
					while (count4 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count4++;
					}
					y = y - count4;
					z = z - 2;
					while (count5 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count5++;
					}
					y = y - count5;
					x = x + 1;
					while (count6 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count6++;
					}
					y = y - count6;
					x = x - 2;
					while (count7 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count7++;
					}
					y = y - count7;
					z = z + 2;
					while (count8 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count8++;
					}
					y = y - count8;
					x = x + 2;
					while (count9 <= 2)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.DIRT);
						count9++;
					}
					x = x - 1;
					z = z - 1;
					y = y - count9 + 3;
					while (count1 <= 10)
					{
						y++;
						Location Sand = new Location(currentWorld, x, y, z);
						Sand.getBlock().setType(Material.SAND);
						count1++;
					}

					player.sendMessage(ChatColor.RED + "You suffocated!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();
						World currentWorld = (targetPlayer).getPlayer().getWorld();

						int count1 = 0;
						int count2 = 0;
						int count3 = 0;
						int count4 = 0;
						int count5 = 0;
						int count6 = 0;
						int count7 = 0;
						int count8 = 0;
						int count9 = 0;
						y = y - 1;
						x = x + 1;
						while (count2 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count2++;
						}
						y = y - count2;
						x = x - 2;
						while (count3 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count3++;
						}
						y = y - count3;
						x = x + 1;
						z = z + 1;
						while (count4 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count4++;
						}
						y = y - count4;
						z = z - 2;
						while (count5 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count5++;
						}
						y = y - count5;
						x = x + 1;
						while (count6 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count6++;
						}
						y = y - count6;
						x = x - 2;
						while (count7 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count7++;
						}
						y = y - count7;
						z = z + 2;
						while (count8 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count8++;
						}
						y = y - count8;
						x = x + 2;
						while (count9 <= 2)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.DIRT);
							count9++;
						}
						x = x - 1;
						z = z - 1;
						y = y - count9 + 3;
						while (count1 <= 10)
						{
							y++;
							Location Sand = new Location(currentWorld, x, y, z);
							Sand.getBlock().setType(Material.SAND);
							count1++;
						}

						sender.sendMessage(ChatColor.RED + "You suffocated " + args[0] + "!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("rainoffire"))
			{
				final Random rg = new Random();
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double x = playerLocation.getBlockX();
					double y = playerLocation.getBlockY();
					double z = playerLocation.getBlockZ();

					World currentWorld = player.getPlayer().getWorld();

					Location loc = new Location(currentWorld, x, y, z);
					loc.setY(loc.getY() + 15);
					loc.setX(loc.getX() + (rg.nextInt((2 * 5) + 1) - 5));
					loc.setZ(loc.getZ() + (rg.nextInt((2 * 5) + 1) - 5));
					loc.setPitch(-90);
					Vector vec = new Vector(0, -1, 0);
					Arrow arrow = player.getWorld().spawnArrow(loc, vec, 0.6f, 12f);
					arrow.setFireTicks(500);
					player.sendMessage(ChatColor.RED + "You were shot at!");
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double x = playerLocation.getBlockX();
						double y = playerLocation.getBlockY();
						double z = playerLocation.getBlockZ();

						World currentWorld = player.getPlayer().getWorld();

						Location loc = new Location(currentWorld, x, y, z);
						loc.setY(loc.getY() + 15);
						loc.setX(loc.getX() + (rg.nextInt((2 * 5) + 1) - 5));
						loc.setZ(loc.getZ() + (rg.nextInt((2 * 5) + 1) - 5));
						loc.setPitch(-90);
						Vector vec = new Vector(0, -1, 0);
						Arrow arrow = player.getWorld().spawnArrow(loc, vec, 0.6f, 12f);
						arrow.setFireTicks(500);
						sender.sendMessage(ChatColor.RED + "You rained fire down upon " + args[0]);
					}
					else
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
				}
				else if (args.length == 2)
				{
					int number = Integer.parseInt(args[1]);
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double x = playerLocation.getBlockX();
						double y = playerLocation.getBlockY();
						double z = playerLocation.getBlockZ();

						if (number > 121)
						{
							player.sendMessage(ChatColor.RED + "Too many arrows!");
							player.sendMessage(ChatColor.RED + "You can not use more than 121 arrows!");
							return true;
						}
						World currentWorld = player.getPlayer().getWorld();
						for (int i = 0; i < number; i++)
						{
							Location loc = new Location(currentWorld, x, y, z);
							loc.setY(loc.getY() + 15);
							loc.setX(loc.getX() + (rg.nextInt((2 * 5) + 1) - 5));
							loc.setZ(loc.getZ() + (rg.nextInt((2 * 5) + 1) - 5));
							loc.setPitch(-90);
							Vector vec = new Vector(0, -1, 0);
							Arrow arrow = player.getWorld().spawnArrow(loc, vec, 0.6f, 12f);
							arrow.setFireTicks(500);
						}
						sender.sendMessage(ChatColor.RED + "You rained fire down upon " + args[0]);
					}
					else
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
				}
			}
			else if (cmd.getName().equalsIgnoreCase("spider"))
			{
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					Location Spider1 = new Location(currentWorld, x + 2, y, z);
					Location Spider2 = new Location(currentWorld, x - 2, y, z);
					Location Spider3 = new Location(currentWorld, x, y, z + 2);
					Location Spider4 = new Location(currentWorld, x, y, z - 2);

					Spider s1 = (Spider) player.getWorld().spawnCreature(Spider1, EntityType.SPIDER);
					Spider s2 = (Spider) player.getWorld().spawnCreature(Spider2, EntityType.SPIDER);
					Spider s3 = (Spider) player.getWorld().spawnCreature(Spider3, EntityType.SPIDER);
					Spider s4 = (Spider) player.getWorld().spawnCreature(Spider4, EntityType.SPIDER);

					s1.setTarget(player);
					s2.setTarget(player);
					s3.setTarget(player);
					s4.setTarget(player);

					player.sendMessage(ChatColor.RED + "You spawned spiders around yourself!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location Spider1 = new Location(currentTargetWorld, x + 2, y, z);
						Location Spider2 = new Location(currentTargetWorld, x - 2, y, z);
						Location Spider3 = new Location(currentTargetWorld, x, y, z + 2);
						Location Spider4 = new Location(currentTargetWorld, x, y, z - 2);

						Spider s1 = (Spider) targetPlayer.getWorld().spawnCreature(Spider1, EntityType.SPIDER);
						Spider s2 = (Spider) targetPlayer.getWorld().spawnCreature(Spider2, EntityType.SPIDER);
						Spider s3 = (Spider) targetPlayer.getWorld().spawnCreature(Spider3, EntityType.SPIDER);
						Spider s4 = (Spider) targetPlayer.getWorld().spawnCreature(Spider4, EntityType.SPIDER);

						s1.setTarget(targetPlayer);
						s2.setTarget(targetPlayer);
						s3.setTarget(targetPlayer);
						s4.setTarget(targetPlayer);

						sender.sendMessage(ChatColor.RED + "You spawned spiders near " + args[0] + "!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("skeleton"))
			{
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					Location Skeleton1 = new Location(currentWorld, x + 2, y, z);
					Location Skeleton2 = new Location(currentWorld, x - 2, y, z);
					Location Skeleton3 = new Location(currentWorld, x, y, z + 2);
					Location Skeleton4 = new Location(currentWorld, x, y, z - 2);

					Skeleton s1 = (Skeleton) player.getWorld().spawnCreature(Skeleton1, EntityType.SKELETON);
					Skeleton s2 = (Skeleton) player.getWorld().spawnCreature(Skeleton2, EntityType.SKELETON);
					Skeleton s3 = (Skeleton) player.getWorld().spawnCreature(Skeleton3, EntityType.SKELETON);
					Skeleton s4 = (Skeleton) player.getWorld().spawnCreature(Skeleton4, EntityType.SKELETON);

					s1.setTarget(player);
					s2.setTarget(player);
					s3.setTarget(player);
					s4.setTarget(player);

					player.sendMessage(ChatColor.RED + "You spawned skeletons around yourself!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location Skeleton1 = new Location(currentTargetWorld, x + 2, y, z);
						Location Skeleton2 = new Location(currentTargetWorld, x - 2, y, z);
						Location Skeleton3 = new Location(currentTargetWorld, x, y, z + 2);
						Location Skeleton4 = new Location(currentTargetWorld, x, y, z - 2);

						Skeleton s1 = (Skeleton) targetPlayer.getWorld().spawnCreature(Skeleton1, EntityType.SKELETON);
						Skeleton s2 = (Skeleton) targetPlayer.getWorld().spawnCreature(Skeleton2, EntityType.SKELETON);
						Skeleton s3 = (Skeleton) targetPlayer.getWorld().spawnCreature(Skeleton3, EntityType.SKELETON);
						Skeleton s4 = (Skeleton) targetPlayer.getWorld().spawnCreature(Skeleton4, EntityType.SKELETON);

						s1.setTarget(targetPlayer);
						s2.setTarget(targetPlayer);
						s3.setTarget(targetPlayer);
						s4.setTarget(targetPlayer);

						sender.sendMessage(ChatColor.RED + "You spawned skeletons near " + args[0] + "!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("zombie"))
			{
				if (args.length == 0 && player != null)
				{
					Location playerLocation = player.getLocation();
					double y = playerLocation.getBlockY();
					double x = playerLocation.getBlockX();
					double z = playerLocation.getBlockZ();
					World currentWorld = ((Player) sender).getPlayer().getWorld();

					Location Zombie1 = new Location(currentWorld, x + 2, y, z);
					Location Zombie2 = new Location(currentWorld, x - 2, y, z);
					Location Zombie3 = new Location(currentWorld, x, y, z + 2);
					Location Zombie4 = new Location(currentWorld, x, y, z - 2);

					Zombie s1 = (Zombie) player.getWorld().spawnCreature(Zombie1, EntityType.ZOMBIE);
					Zombie s2 = (Zombie) player.getWorld().spawnCreature(Zombie2, EntityType.ZOMBIE);
					Zombie s3 = (Zombie) player.getWorld().spawnCreature(Zombie3, EntityType.ZOMBIE);
					Zombie s4 = (Zombie) player.getWorld().spawnCreature(Zombie4, EntityType.ZOMBIE);

					s1.setTarget(player);
					s2.setTarget(player);
					s3.setTarget(player);
					s4.setTarget(player);

					player.sendMessage(ChatColor.RED + "You spawned zombies around yourself!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						Location playerLocation = targetPlayer.getLocation();
						double y = playerLocation.getBlockY();
						double x = playerLocation.getBlockX();
						double z = playerLocation.getBlockZ();

						World currentTargetWorld = targetPlayer.getWorld();

						Location Zombie1 = new Location(currentTargetWorld, x + 2, y, z);
						Location Zombie2 = new Location(currentTargetWorld, x - 2, y, z);
						Location Zombie3 = new Location(currentTargetWorld, x, y, z + 2);
						Location Zombie4 = new Location(currentTargetWorld, x, y, z - 2);

						Zombie s1 = (Zombie) targetPlayer.getWorld().spawnCreature(Zombie1, EntityType.ZOMBIE);
						Zombie s2 = (Zombie) targetPlayer.getWorld().spawnCreature(Zombie2, EntityType.ZOMBIE);
						Zombie s3 = (Zombie) targetPlayer.getWorld().spawnCreature(Zombie3, EntityType.ZOMBIE);
						Zombie s4 = (Zombie) targetPlayer.getWorld().spawnCreature(Zombie4, EntityType.ZOMBIE);

						s1.setTarget(targetPlayer);
						s2.setTarget(targetPlayer);
						s3.setTarget(targetPlayer);
						s4.setTarget(targetPlayer);

						sender.sendMessage(ChatColor.RED + "You spawned zombies near " + args[0] + "!");
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
			else if (cmd.getName().equalsIgnoreCase("tstop"))
			{
				if (args.length == 0 && player != null)
				{
					if (player.hasPotionEffect(PotionEffectType.POISON) == true)
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 0, 0), true);
					}
					if (player.hasPotionEffect(PotionEffectType.BLINDNESS))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 0, 0), true);
					}
					if (player.hasPotionEffect(PotionEffectType.SLOW))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0), true);
					}
					if (player.hasPotionEffect(PotionEffectType.HUNGER))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 0, 0), true);
					}
					if (player.hasPotionEffect(PotionEffectType.CONFUSION))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 0, 0), true);
					}
					if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 0, 0), true);
					}
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage(ChatColor.GREEN + "You are cured!");
					return true;
				}
				else if (args.length == 1)
				{
					if (Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

						if (targetPlayer.hasPotionEffect(PotionEffectType.POISON) == true)
						{
							targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 0, 0), true);
						}
						if (targetPlayer.hasPotionEffect(PotionEffectType.BLINDNESS))
						{
							targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 0, 0), true);
						}
						if (targetPlayer.hasPotionEffect(PotionEffectType.SLOW))
						{
							targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0), true);
						}
						if (targetPlayer.hasPotionEffect(PotionEffectType.HUNGER))
						{
							targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 0, 0), true);
						}
						if (targetPlayer.hasPotionEffect(PotionEffectType.CONFUSION))
						{
							targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 0, 0), true);
						}
						if (targetPlayer.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
						{
							targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 0, 0), true);
						}
						targetPlayer.setHealth(20);
						targetPlayer.setFoodLevel(20);
						return true;
					}
					else
					{
						sender.sendMessage(ChatColor.RED + args[0] + " is not online.");
					}
				}
			}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
		}

		return false;
	}
}
