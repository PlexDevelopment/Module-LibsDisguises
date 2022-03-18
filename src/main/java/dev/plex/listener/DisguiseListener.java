package dev.plex.listener;

import dev.plex.LibsDisguises;
import dev.plex.cache.DataUtils;
import dev.plex.player.PlexPlayer;
import dev.plex.util.PlexLog;
import java.util.ArrayList;
import java.util.List;
import me.libraryaddict.disguise.DisguiseAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class DisguiseListener extends PlexListener
{
    final List<Command> commands = new ArrayList<>();

    @EventHandler
    public void onDisguiseToggle(UndisguiseEvent event)
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (event.isUndisguiseAdmins())
            {
                DisguiseAPI.undisguiseToAll(player);
            }
            else
            {
                PlexPlayer plexPlayer = DataUtils.getPlayer(player.getUniqueId());
                if (!plugin.getRankManager().isAdmin(plexPlayer))
                {
                    DisguiseAPI.undisguiseToAll(player);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        String message = event.getMessage();
        // Don't check the arguments
        message = message.replaceAll("\\s.*", "").replaceFirst("/", "");
        if (!LibsDisguises.enabled)
        {
            for (Command command : commands)
            {
                for (String commandAliases : command.getAliases())
                {
                    if (message.equalsIgnoreCase(command.getName()) || message.equalsIgnoreCase(commandAliases))
                    {
                        event.getPlayer().sendMessage(Component.text("LibsDisguises is currently disabled.").color(NamedTextColor.RED));
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }

    public List<Command> getCommands()
    {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins())
        {
            if (plugin.getName().equals("LibsDisguises"))
            {
                List<Command> commandList = PluginCommandYamlParser.parse(plugin);
                commands.addAll(commandList);
            }
        }
        PlexLog.log("Successfully fetched all LibsDisguises commands!");
        return commands;
    }
}
