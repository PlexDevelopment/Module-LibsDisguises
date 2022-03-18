package dev.plex;

import dev.plex.command.DisguiseToggleCMD;
import dev.plex.command.UndisguiseAllCMD;
import dev.plex.listener.DisguiseListener;
import dev.plex.module.PlexModule;

public class LibsDisguises extends PlexModule
{
    public static boolean enabled = true;
    DisguiseListener disguiseListener;
    @Override
    public void enable()
    {
        registerCommand(new DisguiseToggleCMD());
        registerCommand(new UndisguiseAllCMD());
        registerListener(new DisguiseListener());
        disguiseListener = new DisguiseListener();
        disguiseListener.getCommands();
    }

    @Override
    public void disable()
    {
        unregisterListener(new DisguiseListener());
    }
}
