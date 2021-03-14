package net.testusuke.hh.effect.item.enderpearl

import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerTeleportEvent


object EnderPearlListener:Listener {

    @EventHandler
    fun onTeleportByEnderPearl(event:PlayerTeleportEvent){

        if(event.cause != PlayerTeleportEvent.TeleportCause.ENDER_PEARL)return
        val player = event.player
        //  Damage
        val health = player.health
        val giveDamage = health / 2
        player.damage(giveDamage)
        val sound = Sound.ENTITY_SHULKER_HURT
        player.playSound(player.location,sound,1F,2F)
    }



}