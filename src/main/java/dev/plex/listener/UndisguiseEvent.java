package dev.plex.listener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event that is run when all players are undisguised
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UndisguiseEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    /**
     * Determines if admins should be undisguised or not
     */
    private final boolean undisguiseAdmins;

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
}