package me.onils.nbtviewer

import me.onils.nbtviewer.listeners.TooltipListener
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(
    name = "@NAME@",
    modid = "@ID@",
    version = "@VERSION@",
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
)
object NBTViewer {
    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent){
        MinecraftForge.EVENT_BUS.register(TooltipListener())
    }
}