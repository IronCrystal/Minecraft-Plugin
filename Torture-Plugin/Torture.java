package me.IronCrystal.Torture;

import java.util.logging.Logger;

import net.minecraft.server.MobEffect;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Torture extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	 public static Permission permission = null;

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " is now disabled!");
		
	}

	@Override
	public void onEnable() {
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
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
		
			/*if(cmd.getName().equalsIgnoreCase("fireball"))
			{
				if(player.hasPermission("torture.fireball")|| player.hasPermission("torture.*"))
				{
					if(args.length == 0)
					{
						playEffect(player, Effect.BLAZE_SHOOT, 1);
						player.sendMessage(ChatColor.RED + "You shot a fireball!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							playEffect(player, Effect.GHAST_SHOOT, 1);
							targetPlayer.sendMessage(ChatColor.RED + "You have shot a fireball!");
							player.sendMessage(ChatColor.RED + "You shot " + args [0]);
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.RED + args [0] + " is not online.");
						}
					}
				
				else
				{
					player.sendMessage(ChatColor.RED + "You do not have the proper permissions to use this command");
				}
			}
		}*/
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
					sender.sendMessage(ChatColor.GREEN + "/starve -empties player's hunger bar");
					sender.sendMessage(ChatColor.GREEN + "/hurt -leaves player with 1 health");
					sender.sendMessage(ChatColor.GREEN + "/ignite -ignites the player on fire");
					sender.sendMessage(ChatColor.GREEN + "/die -kills the player");
					sender.sendMessage(ChatColor.GREEN + "/painful -leaves player with 1 health and empties hunger bar");
					sender.sendMessage(ChatColor.GREEN + "/blind -leaves the player blind");
					sender.sendMessage(ChatColor.GREEN + "/ill -makes them feel sick");
					sender.sendMessage(ChatColor.GREEN + "/hungry -leaves the player terribly hungry");
					sender.sendMessage(ChatColor.GREEN + "/poison -leaves the player poisoned");
					sender.sendMessage(ChatColor.GREEN + "/slow -slows the player down");
					sender.sendMessage(ChatColor.GREEN + "/fall -makes the player fall from the sky");
					sender.sendMessage(ChatColor.GREEN + "/explode -explodes the player");
					sender.sendMessage(ChatColor.GREEN + "/torture -terrible things will come to pass");

					return true;
					}
					/*else if(args.length == 1)
					{
						if (args [0] == "2")
						{
							sender.sendMessage(ChatColor.GREEN + "/painful -leaves player with 1 health and empties hunger bar");
							sender.sendMessage(ChatColor.GREEN + "/blind -leaves the player blind");
							sender.sendMessage(ChatColor.GREEN + "/ill -makes them feel sick");
							sender.sendMessage(ChatColor.GREEN + "/hungry -leaves the player terribly hungry");
							sender.sendMessage(ChatColor.GREEN + "/poison -leaves the player poisoned");
							sender.sendMessage(ChatColor.GREEN + "");
							sender.sendMessage(ChatColor.GREEN + "Type /help 3 to see the next page.");
							
						}
						else if (args [0] == "3")
						{
							sender.sendMessage(ChatColor.GREEN + "/fall -makes the player fall from the sky");
							sender.sendMessage(ChatColor.GREEN + "/explode -explodes the player");
							sender.sendMessage(ChatColor.GREEN + "/torture -terrible things will come to pass");
							sender.sendMessage(ChatColor.GREEN + "");
							sender.sendMessage(ChatColor.GREEN + "Type /help 4 to see the next page.");
						}
					}*/
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
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(19,500,1));
						player.sendMessage(ChatColor.RED + "You are poisoned!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19,500,1));
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
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(2,500,1));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(4,500,1));
						player.sendMessage(ChatColor.RED + "You have been slowed down!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(2,500,1));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(4,500,1));
							targetPlayer.sendMessage(ChatColor.RED + "You have been slowed down!");
							player.sendMessage(ChatColor.RED + "You slowed " + args [0] + "  down.");
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
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(15,500,1));
						player.sendMessage(ChatColor.RED + "You are blind!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(15,500,1));
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
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(17,500,1));
						player.sendMessage(ChatColor.RED + "You are hungry!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17,500,1));
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
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(9,500,1));
						player.sendMessage(ChatColor.RED + "You are ill!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(9,500,1));
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
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(19,500,1));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(9,500,1));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(15,500,1));
						((CraftPlayer)player).getHandle().addEffect(new MobEffect(17,500,1));
						player.sendMessage(ChatColor.RED + "You have been tortured!");
						return true;
					}
					else if(args.length == 1)
					{
						if(player.getServer().getPlayer(args [0]) != null)
						{
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(19,500,1));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(9,500,1));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(15,500,1));
							((CraftPlayer)targetPlayer).getHandle().addEffect(new MobEffect(17,500,1));
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
						World currentWorld = ((CraftPlayer) sender).getPlayer().getWorld();

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
							((CraftPlayer)targetPlayer).teleport(targetPlayerLocation);

							
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
    }
		return false;
    }
}