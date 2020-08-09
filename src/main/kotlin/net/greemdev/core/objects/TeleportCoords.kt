package net.greemdev.core.objects

import org.bukkit.block.Block
import org.jetbrains.annotations.NotNull

public data class TeleportCoords(
        @NotNull public val x: Double,
        @NotNull public val y: Double,
        @NotNull public val z: Double,
        @NotNull public val highestBlock: Block) {

    companion object {
        public fun from(x: Double, y: Double, z: Double, highestBlock: Block): TeleportCoords {
            return TeleportCoords(x, y, z, highestBlock)
        }
    }

    public override fun toString(): String {
        return x.toString() + ", " + this.y + ", " + this.z
    }

}