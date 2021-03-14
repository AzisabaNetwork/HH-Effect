package net.testusuke.hh.effect.item.record

import net.testusuke.hh.effect.Main
import org.bukkit.Sound
import org.bukkit.entity.Creature
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import java.util.concurrent.ThreadLocalRandom

object RecordUtils {

    fun cannotUse(player: Player){
        player.playSound(player.location, Sound.BLOCK_GLASS_BREAK,1F,2F)
    }

    fun dice():Boolean {
        var b = false
        val r = ThreadLocalRandom.current().nextInt(1,10)
        if(r <= 4){
            b = true
        }
        return b
    }


    fun damage(entity: Entity){
        object : BukkitRunnable(){
            override fun run(){
                //  check if entity is mob
                if(entity !is Creature) return

                val vector = Vector(0,2,0)
                entity.velocity = vector
                Thread.sleep(500)
                entity.velocity = Vector()
            }
        }.runTaskAsynchronously(Main.plugin)

    }
}