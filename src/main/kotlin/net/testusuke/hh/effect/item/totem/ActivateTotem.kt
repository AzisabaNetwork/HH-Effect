package net.testusuke.hh.effect.item.totem

import net.minecraft.server.v1_16_R3.PacketPlayOutEntityStatus
import net.testusuke.hh.effect.Main.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import java.util.concurrent.ThreadLocalRandom

object ActivateTotem:Listener {

    @EventHandler
    fun onActiveTotem(event:EntityDamageEvent){
        val player = event.entity
        //  is player
        if(player !is Player)return
        //  ignore when player is not survival
        if(player.gameMode == GameMode.CREATIVE)return
        //  has totem
        if(player.inventory.contains(Material.TOTEM_OF_UNDYING) || player.inventory.itemInOffHand.type == Material.TOTEM_OF_UNDYING){
            //  Remove
            player.inventory.remove(Material.TOTEM_OF_UNDYING)
            if(player.inventory.itemInOffHand.type == Material.TOTEM_OF_UNDYING){
                player.inventory.setItemInOffHand(ItemStack(Material.AIR))
            }
            //  NMS for activate totem animation
            val craftPlayer = player as CraftPlayer
            val entityPlayer = craftPlayer.handle
            //  totem effect id
            val id:Byte = 35
            val ppoes = PacketPlayOutEntityStatus(entityPlayer, id)
            entityPlayer.playerConnection.sendPacket(ppoes) //  execute
            //  Task(tnt and lightning)
            object : BukkitRunnable(){
                override fun run(){
                    val location = player.location
                    val world = location.world
                    val beforeX = location.blockX
                    val y = location.blockY
                    val beforeZ = location.blockZ
                    for(i in 0..9){
                        val x = ThreadLocalRandom.current().nextDouble(-2.0,2.0)
                        val z = ThreadLocalRandom.current().nextDouble(-2.0,2.0)
                        val targetLocation = Location(world,beforeX + x, y.toDouble(),beforeZ + z)
                        Bukkit.getScheduler().runTask(plugin, Runnable {
                            world?.strikeLightning(targetLocation)
                            world?.createExplosion(targetLocation,6.0F)
                        })
                    }
                }
            }.runTaskLaterAsynchronously(plugin,40)
        }
    }
}