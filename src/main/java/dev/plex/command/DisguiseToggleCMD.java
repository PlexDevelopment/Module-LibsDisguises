package dev.plex.command;

import dev.plex.LibsDisguises;
import dev.plex.command.annotation.CommandParameters;
import dev.plex.command.annotation.CommandPermissions;
import dev.plex.listener.UndisguiseEvent;
import dev.plex.rank.enums.Rank;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CommandParameters(name = "disguisetoggle", description = "Toggle LibsDisguises", aliases = "dtoggle")
@CommandPermissions(level = Rank.ADMIN, permission = "plex.libsdisguises.disguisetoggle")
public class DisguiseToggleCMD extends PlexCommand
{
    @Override
    protected Component execute(@NotNull CommandSender commandSender, @Nullable Player player, @NotNull String[] strings)
    {
        LibsDisguises.enabled = !LibsDisguises.enabled;
        if (!LibsDisguises.enabled)
        {
            Bukkit.getServer().getPluginManager().callEvent(new UndisguiseEvent(true));
        }
        Bukkit.broadcast(Component.text(commandSender.getName() + " - " + (LibsDisguises.enabled ? "Enabling LibsDisguises" : "Disabling LibsDisguises")).color((LibsDisguises.enabled ? NamedTextColor.AQUA : NamedTextColor.RED)));
        return null;
    }
}
