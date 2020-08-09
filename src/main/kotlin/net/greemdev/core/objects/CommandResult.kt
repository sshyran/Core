package net.greemdev.core.objects

import org.jetbrains.annotations.NotNull

public class CommandResult(
    @NotNull val successful: Boolean
) {
    companion object {
        public fun unsuccessful(): CommandResult = CommandResult(false)
        public fun successful(): CommandResult = CommandResult(true)
    }
}