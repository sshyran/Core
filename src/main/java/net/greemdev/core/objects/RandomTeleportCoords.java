package net.greemdev.core.objects;

import org.bukkit.block.Block;

public class RandomTeleportCoords {

    public RandomTeleportCoords(int x, int y, int z, Block block) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block;
    }


    private final int x;
    private final int y;
    private final int z;
    private final Block block;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public Block getBlock() {
        return this.block;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.z;
    }
}
