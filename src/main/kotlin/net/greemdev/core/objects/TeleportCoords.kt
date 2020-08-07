package net.greemdev.core.objects

import org.bukkit.block.Block

public class TeleportCoords(public val x: Double, public val y: Double, public val z: Double, public val block: Block) {

    companion object {
        public fun from(x: Double, y: Double, z: Double, block: Block): TeleportCoords {
            return TeleportCoords(x, y, z, block)
        }
    }

    public override fun toString(): String {
        return x.toString() + ", " + this.y + ", " + this.z
    }

}