package dev.plex;

import dev.plex.command.DisguiseToggleCMD;
import dev.plex.command.UndisguiseAllCMD;
import dev.plex.listener.DisguiseListener;
import dev.plex.module.PlexModule;
import dev.plex.util.PlexLog;
import org.bukkit.Bukkit;

public class LibsDisguises extends PlexModule
{
    public static boolean enabled = true;
    DisguiseListener disguiseListener;

    @Override
    public void enable()
    {
        if (!Bukkit.getPluginManager().isPluginEnabled("LibsDisguises"))
        {
            PlexLog.error("The Plex-LibsDisguises module requires the LibsDisguises plugin to work.");
            return;
        }
        registerCommand(new DisguiseToggleCMD());
        registerCommand(new UndisguiseAllCMD());
        registerListener(new DisguiseListener());
        disguiseListener = new DisguiseListener();
        disguiseListener.getCommands();
    }

    @Override
    public void disable()
    {
        // Unregistering listeners / commands is handled by Plex
    }
}
