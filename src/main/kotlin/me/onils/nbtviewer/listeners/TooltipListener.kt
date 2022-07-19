package me.onils.nbtviewer.listeners

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.nbt.*
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.apache.commons.text.WordUtils

const val GRAY = "§7"
const val DGRAY = "§8"
const val GOLD = "§6"

private val COLORS = arrayOf("§9", "§2", "§e", "§c")

class TooltipListener {
    @SubscribeEvent
    fun onTooltip(event: ItemTooltipEvent){
        event.toolTip.add(
             WordUtils.wrap(
                 "${GRAY}NBT: " + format(event.itemStack.tagCompound),
                96,
                "\n",
                false
            )
        )
    }

    private fun format(nbt: NBTBase?, level: Int = 0): String {
        val bracketColor = COLORS[level % COLORS.size]

        return when(nbt){
            null, is NBTTagEnd -> "${DGRAY}null"
            is NBTTagList ->
                Array(nbt.tagCount()) { format(nbt[it], level + 1) }.joinToString(
                    "$DGRAY, ",
                    "$bracketColor[",
                    "$bracketColor]"
                )
            is NBTTagCompound ->
                nbt.keySet.joinToString(
                    "$DGRAY, ",
                    "$bracketColor{",
                    "$bracketColor}"
                ) { key ->
                    "$DGRAY$key: ${format(nbt.getTag(key), level + 1)}"
                }
            is NBTTagByteArray ->
                nbt.byteArray.joinToString(
                    "$DGRAY, ",
                    "$bracketColor[",
                    "$bracketColor]"
                ) { "$GRAY$it" }
            is NBTTagIntArray ->
                nbt.intArray.joinToString(
                    "$DGRAY, ",
                    "$bracketColor[",
                    "$bracketColor]"
                ) { "$GRAY$it" }
            is NBTTagString ->
                "$GRAY\"${nbt.string.replace("\"", "\\\"")}$GRAY\""
            else -> "$GRAY$nbt"
        }
    }
}