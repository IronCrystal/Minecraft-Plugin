package me.IronCrystal.Torture;

import java.util.Random;
import java.util.logging.Logger;

import net.minecraft.server.MobEffect;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
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
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled.");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = (Player) sender;

		if (sender instanceof Player)
		{
			if(cmd.getName().equalsIgnoreCase("starve"))
			{
				if(player.hasPermission("torture.starve")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						player.setFoodLevel(0);
						player.sendMessage(ChatColor.RED + "You are starving!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFoodLevel(0);
							targetPlayer.sendMessage(ChatColor.RED + "You have been starved!");
							player.sendMessage(ChatColor.RED + "You starved " + args [0]);
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFoodLevel(Integer.parseInt(args [1]));
							targetPlayer.sendMessage(ChatColor.RED + "You have been starved!");
							player.sendMessage(ChatColor.RED + "You starved " + args [0]);
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("hurt"))
			{
				if(player.hasPermission("torture.hurt")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						player.setHealth(1);
						player.sendMessage(ChatColor.RED + "You hurt yourself!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setHealth(1);
							targetPlayer.sendMessage(ChatColor.RED + "You have been hurt!");
							player.sendMessage(ChatColor.RED + "You hurt " + args [0]);
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int health = player.getHealth() - Integer.parseInt(args [1]);
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setHealth(health);
							targetPlayer.sendMessage(ChatColor.RED + "You have been hurt!");
							player.sendMessage(ChatColor.RED + "You hurt " + args [0]);
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("ignite"))
			{
				if(player.hasPermission("torture.ignite")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						player.setFireTicks(10000);
						player.sendMessage(ChatColor.RED + "You were set on fire!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFireTicks(10000);
							targetPlayer.sendMessage(ChatColor.RED + "You have been set on fire!");
							player.sendMessage(ChatColor.RED + "You set " + args [0] + " on fire!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("die"))
			{
				if(player.hasPermission("torture.die")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						player.setHealth(0);
						player.sendMessage(ChatColor.RED + "You killed yourself!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setHealth(0);
							targetPlayer.sendMessage(ChatColor.RED + "You have been killed!");
							player.sendMessage(ChatColor.RED + "You killed " + args [0]);
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("painful"))
			{
				if(player.hasPermission("torture.painful")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						player.setFoodLevel(0);
						player.setHealth(1);
						player.sendMessage(ChatColor.RED + "You are in pain!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFoodLevel(0);
							targetPlayer.setHealth(1);
							targetPlayer.sendMessage(ChatColor.RED + "You have been put to pain!");
							player.sendMessage(ChatColor.RED + "You put " + args [0] + " in pain!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("thelp"))
			{
				if(player.hasPermission("torture.thelp")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
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
					else if(args.length == 1)
					{
						int page = Integer.parseInt(args [0]);
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
							sender.sendMessage("/annoy <player> -annoys the player with villagers");
							sender.sendMessage("/rabid <player> -sends rabid wolves after the player");
							sender.sendMessage("/suffocate <player> -suffocates the player in a tower of sand");
							sender.sendMessage("/rainoffire <player> <number> -Drops firey arrows over the player");
							sender.sendMessage("/torture <player> <time> <1-4> -terrible things will come to pass");
							/*							sender.sendMessage(ChatColor.GREEN + "");
							sender.sendMessage(ChatColor.GREEN + "Type /help 5 to see the next page.");*/
						}
						return true;
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("poison"))
			{
				if(player.hasPermission("torture.poison")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						((CraftPlayer)player).getHandle().addEffect( new MobEffect(19, 500, 0));
						player.sendMessage(ChatColor.RED + "You are poisoned!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19,500,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been poisoned!");
							player.sendMessage(ChatColor.RED + "You poisoned " + args [0] + ".");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int time = Integer.parseInt(args[1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19,time,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been poisoned!");
							player.sendMessage(ChatColor.RED + "You poisoned " + args [0] + ".");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 3)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int strength = Integer.parseInt(args[2]) - 1;
							int time = Integer.parseInt(args[1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19, time, strength));
							targetPlayer.sendMessage(ChatColor.RED + "You have been poisoned!");
							player.sendMessage(ChatColor.RED + "You poisoned " + args [0] + ".");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("slow"))
			{
				if(player.hasPermission("torture.slow")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(2,500,0));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(4,500,0));
						player.sendMessage(ChatColor.RED + "You have been slowed down!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(2,500,0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(4,500,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been slowed down!");
							player.sendMessage(ChatColor.RED + "You slowed " + args [0] + " down.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int time = Integer.parseInt(args [1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(2,time, 0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(4,time,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been slowed down!");
							player.sendMessage(ChatColor.RED + "You slowed " + args [0] + " down.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 3)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int strength = Integer.parseInt(args [2]);
							int time = Integer.parseInt(args [1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(2, time, strength));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(4, time, strength));
							targetPlayer.sendMessage(ChatColor.RED + "You have been slowed down!");
							player.sendMessage(ChatColor.RED + "You slowed " + args [0] + " down.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("upsidedown"))
			{
				if(player.hasPermission("torture.upsidedown")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(2, 500, 15));
						player.sendMessage(ChatColor.RED + "Your world has been turn upside down!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(2, 500, 15));
							targetPlayer.sendMessage(ChatColor.RED + "Your world has been turned upside down!");
							player.sendMessage(ChatColor.RED + "You turned " + args [0] + "'s world upside down!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int time = Integer.parseInt(args [1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(2, time, 15));
							targetPlayer.sendMessage(ChatColor.RED + "Your world has been turned upside down!");
							player.sendMessage(ChatColor.RED + "You turned " + args [0] + "'s world upside down!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("blind"))
			{
				if(player.hasPermission("torture.blind")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(15,500,0));
						player.sendMessage(ChatColor.RED + "You are blind!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(15,500,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been made blind!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " blind.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int time = Integer.parseInt(args [1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(15,time,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been made blind!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " blind.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("hungry"))
			{
				if(player.hasPermission("torture.hungry") || player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(17, 500, 0));
						player.sendMessage(ChatColor.RED + "You are hungry!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17,500,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been made hungry!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " hungry.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int time = Integer.parseInt(args[1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17,time,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been made hungry!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " hungry.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 3)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int strength = Integer.parseInt(args [2]) - 1;
							int time = Integer.parseInt(args[1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17,time,strength));
							targetPlayer.sendMessage(ChatColor.RED + "You have been made hungry!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " hungry.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("ill"))
			{
				if(player.hasPermission("torture.ill")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(9,500,0));
						player.sendMessage(ChatColor.RED + "You are ill!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(9,500,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been made ill!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " ill.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int time = Integer.parseInt(args[1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(9,time,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been made ill!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " ill.");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}

				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("torture"))
			{
				if(player.hasPermission("torture.torture")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(19,500,0));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(9,500,0));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(15,500,0));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(17,500,0));
						player.sendMessage(ChatColor.RED + "You have been tortured!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19,500,0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(9,500,0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(15,500,0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17,500,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been tortured!");
							player.sendMessage(ChatColor.RED + "You tortured " + args [0] + ".");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int time = Integer.parseInt(args [1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19,time,0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(9,time,0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(15,time,0));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17,time,0));
							targetPlayer.sendMessage(ChatColor.RED + "You have been tortured!");
							player.sendMessage(ChatColor.RED + "You tortured " + args [0] + ".");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 3)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int strength = Integer.parseInt(args [2]) - 1;
							int time = Integer.parseInt(args [1]) * 20;
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19, time, strength));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(9, time, strength));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(15, time, strength));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17, time, strength));
							targetPlayer.sendMessage(ChatColor.RED + "You have been tortured!");
							player.sendMessage(ChatColor.RED + "You tortured " + args [0] + ".");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("explode"))
			{
				if(player.hasPermission("torture.explode")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						float explosionPower = 0F;
						player.getWorld().createExplosion(player.getLocation(), explosionPower);
						player.setHealth(0);


						player.sendMessage(ChatColor.RED + "You have exploded!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							float explosionPower = 0F;
							targetPlayer.getWorld().createExplosion(targetPlayer.getLocation(), explosionPower);
							targetPlayer.setHealth(0);
							targetPlayer.sendMessage(ChatColor.RED + "You have exploded!");
							player.sendMessage(ChatColor.RED + "You exploded " + args [0] + ".");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("fall"))
			{
				if(player.hasPermission("torture.fall")|| player.hasPermission("torture.*"))
				{


					if(args.length == 0)
					{
						Location blockLocation = player.getLocation();
						double y = blockLocation.getBlockY();
						double x = blockLocation.getBlockX();
						double z = blockLocation.getBlockZ();
						World currentWorld = ((Player) sender).getPlayer().getWorld();

						Location playerLocation = new Location(currentWorld, x, y + 13, z);
						player.teleport(playerLocation);
						player.sendMessage(ChatColor.RED + "You have fallen!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);

							Location blockLocation = targetPlayer.getLocation();
							double y = blockLocation.getBlockY();
							double x = blockLocation.getBlockX();
							double z = blockLocation.getBlockZ();

							World currentTargetWorld = targetPlayer.getWorld();

							Location targetPlayerLocation = new Location(currentTargetWorld, x, y + 13, z);
							((Player)targetPlayer).teleport(targetPlayerLocation);


							targetPlayer.sendMessage(ChatColor.RED + "You have fallen!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " fall!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
					else if(args.length == 2)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							int distance = Integer.parseInt(args [1]);
							Player targetPlayer = player.getServer().getPlayer(args [0]);

							Location blockLocation = targetPlayer.getLocation();
							double y = blockLocation.getBlockY();
							double x = blockLocation.getBlockX();
							double z = blockLocation.getBlockZ();

							World currentTargetWorld = targetPlayer.getWorld();

							Location targetPlayerLocation = new Location(currentTargetWorld, x, y + distance, z);
							((Player)targetPlayer).teleport(targetPlayerLocation);


							targetPlayer.sendMessage(ChatColor.RED + "You have fallen!");
							player.sendMessage(ChatColor.RED + "You made " + args [0] + " fall!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("creeper"))
			{
				if(player.hasPermission("torture.creeper")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						Location blockLocation = player.getLocation();
						double y = blockLocation.getBlockY();
						double x = blockLocation.getBlockX();
						double z = blockLocation.getBlockZ();
						World currentWorld = ((Player) sender).getPlayer().getWorld();

						Location Creeper1 = new Location(currentWorld, x + 1, y, z);
						Location Creeper2 = new Location(currentWorld, x - 1, y, z);
						Location Creeper3 = new Location(currentWorld, x, y, z + 1);
						Location Creeper4 = new Location(currentWorld, x, y, z - 1);

						player.getWorld().spawnCreature(Creeper1, CreatureType.CREEPER);
						player.getWorld().spawnCreature(Creeper2, CreatureType.CREEPER);
						player.getWorld().spawnCreature(Creeper3, CreatureType.CREEPER);
						player.getWorld().spawnCreature(Creeper4, CreatureType.CREEPER);

						player.sendMessage(ChatColor.RED + "You spawned creepers around yourself!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);

							Location blockLocation = targetPlayer.getLocation();
							double y = blockLocation.getBlockY();
							double x = blockLocation.getBlockX();
							double z = blockLocation.getBlockZ();

							World currentTargetWorld = targetPlayer.getWorld();

							Location Creeper1 = new Location(currentTargetWorld, x + 1, y, z);
							Location Creeper2 = new Location(currentTargetWorld, x - 1, y, z);
							Location Creeper3 = new Location(currentTargetWorld, x, y, z + 1);
							Location Creeper4 = new Location(currentTargetWorld, x, y, z - 1);

							targetPlayer.getWorld().spawnCreature(Creeper1, CreatureType.CREEPER);
							targetPlayer.getWorld().spawnCreature(Creeper2, CreatureType.CREEPER);
							targetPlayer.getWorld().spawnCreature(Creeper3, CreatureType.CREEPER);
							targetPlayer.getWorld().spawnCreature(Creeper4, CreatureType.CREEPER);


							targetPlayer.sendMessage(ChatColor.RED + "Creepers have spawned near you!");
							player.sendMessage(ChatColor.RED + "You spawned creepers near " + args [0] + "!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("annoy"))
			{
				if(player.hasPermission("torture.annoy")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						Location blockLocation = player.getLocation();
						double y = blockLocation.getBlockY();
						double x = blockLocation.getBlockX();
						double z = blockLocation.getBlockZ();
						World currentWorld = ((Player) sender).getPlayer().getWorld();

						Location Villager1 = new Location(currentWorld, x + 1, y, z);
						Location Villager2 = new Location(currentWorld, x - 1, y, z);
						Location Villager3 = new Location(currentWorld, x, y, z + 1);
						Location Villager4 = new Location(currentWorld, x, y, z - 1);
						Location Villager5 = new Location(currentWorld, x + 2, y, z);
						Location Villager6 = new Location(currentWorld, x - 2, y, z);
						Location Villager7 = new Location(currentWorld, x, y, z + 2);
						Location Villager8 = new Location(currentWorld, x, y, z - 2);

						Villager v1 = (Villager)player.getWorld().spawnCreature(Villager1, CreatureType.VILLAGER);
						Villager v2 = (Villager)player.getWorld().spawnCreature(Villager2, CreatureType.VILLAGER);
						Villager v3 = (Villager)player.getWorld().spawnCreature(Villager3, CreatureType.VILLAGER);
						Villager v4 = (Villager)player.getWorld().spawnCreature(Villager4, CreatureType.VILLAGER);
						Villager v5 = (Villager)player.getWorld().spawnCreature(Villager5, CreatureType.VILLAGER);
						Villager v6 = (Villager)player.getWorld().spawnCreature(Villager6, CreatureType.VILLAGER);
						Villager v7 = (Villager)player.getWorld().spawnCreature(Villager7, CreatureType.VILLAGER);
						Villager v8 = (Villager)player.getWorld().spawnCreature(Villager8, CreatureType.VILLAGER);

						player.sendMessage(ChatColor.RED + "You annoyed yourself!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);

							Location blockLocation = targetPlayer.getLocation();
							double y = blockLocation.getBlockY();
							double x = blockLocation.getBlockX();
							double z = blockLocation.getBlockZ();

							World currentTargetWorld = targetPlayer.getWorld();

							Location Villager1 = new Location(currentTargetWorld, x + 1, y, z);
							Location Villager2 = new Location(currentTargetWorld, x - 1, y, z);
							Location Villager3 = new Location(currentTargetWorld, x, y, z + 1);
							Location Villager4 = new Location(currentTargetWorld, x, y, z - 1);
							Location Villager5 = new Location(currentTargetWorld, x + 2, y, z);
							Location Villager6 = new Location(currentTargetWorld, x - 2, y, z);
							Location Villager7 = new Location(currentTargetWorld, x, y, z + 2);
							Location Villager8 = new Location(currentTargetWorld, x, y, z - 2);


							Villager v1 = (Villager)targetPlayer.getWorld().spawnCreature(Villager1, CreatureType.VILLAGER);
							Villager v2 = (Villager)targetPlayer.getWorld().spawnCreature(Villager2, CreatureType.VILLAGER);
							Villager v3 = (Villager)targetPlayer.getWorld().spawnCreature(Villager3, CreatureType.VILLAGER);
							Villager v4 = (Villager)targetPlayer.getWorld().spawnCreature(Villager4, CreatureType.VILLAGER);
							Villager v5 = (Villager)targetPlayer.getWorld().spawnCreature(Villager5, CreatureType.VILLAGER);
							Villager v6 = (Villager)targetPlayer.getWorld().spawnCreature(Villager6, CreatureType.VILLAGER);
							Villager v7 = (Villager)targetPlayer.getWorld().spawnCreature(Villager7, CreatureType.VILLAGER);
							Villager v8 = (Villager)targetPlayer.getWorld().spawnCreature(Villager8, CreatureType.VILLAGER);

							targetPlayer.sendMessage(ChatColor.RED + "You have been annoyed!");
							player.sendMessage(ChatColor.RED + "You annoyed " + args [0] + " with villagers!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("rabid"))
			{
				if(player.hasPermission("torture.rabid")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						Location blockLocation = player.getLocation();
						double y = blockLocation.getBlockY();
						double x = blockLocation.getBlockX();
						double z = blockLocation.getBlockZ();
						World currentWorld = ((Player) sender).getPlayer().getWorld();

						Location Wolf1 = new Location(currentWorld, x + 5, y, z);
						Location Wolf2 = new Location(currentWorld, x + 5, y, z - 1);
						Location Wolf3 = new Location(currentWorld, x + 5, y, z + 1);

						Wolf w1 = (Wolf)player.getWorld().spawnCreature(Wolf1, CreatureType.WOLF);
						Wolf w2 = (Wolf)player.getWorld().spawnCreature(Wolf2, CreatureType.WOLF);
						Wolf w3 = (Wolf)player.getWorld().spawnCreature(Wolf3, CreatureType.WOLF);
						player.sendMessage(ChatColor.RED + "You are being chased by rabid wolves!");

						w1.setAngry(true);
						w2.setAngry(true);
						w3.setAngry(true);
						w1.setTarget(player);
						w2.setTarget(player);
						w3.setTarget(player);

						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);

							Location blockLocation = targetPlayer.getLocation();
							double y = blockLocation.getBlockY();
							double x = blockLocation.getBlockX();
							double z = blockLocation.getBlockZ();

							World currentTargetWorld = targetPlayer.getWorld();

							Location Wolf1 = new Location(currentTargetWorld, x + 5, y, z - 1);
							Location Wolf2 = new Location(currentTargetWorld, x + 5, y, z);
							Location Wolf3 = new Location(currentTargetWorld, x + 5, y, z + 1);

							Wolf w1 = (Wolf)targetPlayer.getWorld().spawnCreature(Wolf1, CreatureType.WOLF);
							Wolf w2 = (Wolf)targetPlayer.getWorld().spawnCreature(Wolf2, CreatureType.WOLF);
							Wolf w3 = (Wolf)targetPlayer.getWorld().spawnCreature(Wolf3, CreatureType.WOLF);

							w1.setAngry(true);
							w2.setAngry(true);
							w3.setAngry(true);
							w1.setTarget(targetPlayer);
							w2.setTarget(targetPlayer);
							w3.setTarget(targetPlayer);

							targetPlayer.sendMessage(ChatColor.RED + "You are being chased by rabid wolves!");
							player.sendMessage(ChatColor.RED + "You spawned rabid wolves near " + args [0] + "!");

							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			if(cmd.getName().equalsIgnoreCase("suffocate"))
			{
				if(player.hasPermission("torture.suffocate")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{						
						Location blockLocation = player.getLocation();
						double y = blockLocation.getBlockY();
						double x = blockLocation.getBlockX();
						double z = blockLocation.getBlockZ();
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
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);

							Location blockLocation = targetPlayer.getLocation();
							double y = blockLocation.getBlockY();
							double x = blockLocation.getBlockX();
							double z = blockLocation.getBlockZ();
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

							player.sendMessage(ChatColor.RED + "You suffocated " + args [0] + "!");
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
			else if (cmd.getName().equalsIgnoreCase("rainoffire"))
			{
				if (player.hasPermission("torture.rainoffire") || player.hasPermission("torture.*"))
				{
					final Random rg = new Random();
					if(args.length == 0)
					{
						Location playerLocation = player.getLocation();
						double x = playerLocation.getBlockX();
						double y = playerLocation.getBlockY();
						double z = playerLocation.getBlockZ();

						World currentWorld = player.getPlayer().getWorld();

						Location loc = new Location(currentWorld, x, y, z);
						loc.setY(loc.getY() + 15);
						loc.setX( loc.getX() + (rg.nextInt((2*5)+1)-5) );
						loc.setZ( loc.getZ() + (rg.nextInt((2*5)+1)-5) );
						loc.setPitch(-90);
						Vector vec = new Vector(0, -1, 0);
						Arrow arrow = player.getWorld().spawnArrow(loc, vec, 0.6f, 12f);
						arrow.setFireTicks(500);
						player.sendMessage(ChatColor.RED + "You were shot at!");
					}
					else if (args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player TargetPlayer = player.getServer().getPlayer(args [0]);

							Location playerLocation = TargetPlayer.getLocation();
							double x = playerLocation.getBlockX();
							double y = playerLocation.getBlockY();
							double z = playerLocation.getBlockZ();

							World currentWorld = player.getPlayer().getWorld();

							Location loc = new Location(currentWorld, x, y, z);
							loc.setY(loc.getY() + 15);
							loc.setX( loc.getX() + (rg.nextInt((2*5)+1)-5) );
							loc.setZ( loc.getZ() + (rg.nextInt((2*5)+1)-5) );
							loc.setPitch(-90);
							Vector vec = new Vector(0, -1, 0);
							Arrow arrow = player.getWorld().spawnArrow(loc, vec, 0.6f, 12f);
							arrow.setFireTicks(500);
							player.sendMessage(ChatColor.RED + "You rained fire down upon " + args [0]);
							TargetPlayer.sendMessage("Fire has been rained upon you!");
						}
						else
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
					}
					else if (args.length == 2)
					{
						int number = Integer.parseInt(args [1]);
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player TargetPlayer = player.getServer().getPlayer(args [0]);

							Location playerLocation = TargetPlayer.getLocation();
							double x = playerLocation.getBlockX();
							double y = playerLocation.getBlockY();
							double z = playerLocation.getBlockZ();

							if(number > 121)
							{
								player.sendMessage(ChatColor.RED + "Too many arrows!");
								player.sendMessage(ChatColor.RED + "You can not use more than 121 arrows!");
								return true;
							}
							World currentWorld = player.getPlayer().getWorld();
							for(int i = 0; i < number; i++)
							{
								Location loc = new Location(currentWorld, x, y, z);
								loc.setY(loc.getY() + 15);
								loc.setX( loc.getX() + (rg.nextInt((2*5)+1)-5) );
								loc.setZ( loc.getZ() + (rg.nextInt((2*5)+1)-5) );
								loc.setPitch(-90);
								Vector vec = new Vector(0, -1, 0);
								Arrow arrow = player.getWorld().spawnArrow(loc, vec, 0.6f, 12f);
								arrow.setFireTicks(500);
							}
							player.sendMessage(ChatColor.RED + "You rained fire down upon " + args [0]);
							TargetPlayer.sendMessage("Fire has been rained upon you!");
						}
						else
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
					}
				}
				else
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
			}
		}
		return false;
	}
}