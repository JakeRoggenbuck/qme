package org.qme.world.res;

import org.qme.world.TileType;

/**
 * An abstract class for resources
 * @author jakeroggenbuck
 * @since 0.3.0
 */
public abstract class AbstractResource {
    // Spawn chances can be a whole number from 0 to 100
    public abstract int getSpawnChance(TileType type);
    public abstract ResourceType getType();
    public abstract String getTexturePath();
}