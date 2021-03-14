package net.testusuke.hh.effect.item.record

import net.testusuke.hh.effect.Main.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Creature
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.concurrent.ThreadLocalRandom

enum class RecordAction {

    Record11{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }

        }
    },
    Record13{
        override fun run(player:Player){
            player.openInventory(player.enderChest)
        }
    },
    RecordBlocks{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            val effect = PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*20, 50)
            player.addPotionEffect(effect)
        }
    },
    RecordCat{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            for(entity in player.getNearbyEntities(3.0, 3.0, 3.0)) {
                if(entity is Player) {
                    //  ignore myself
                    if(entity == player) continue
                    //  Wither Effect
                    val effect = PotionEffect(PotionEffectType.WITHER, 20*15, 2)
                    entity.addPotionEffect(effect)
                }
            }
        }
    },
    RecordChirp{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
        }
    },
    //  周囲3ブロックのプレイヤーをspawn地点に移動
    //  かつEntityにはダメージ
    RecordFar{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            for(entity in player.getNearbyEntities(3.0, 3.0, 3.0)) {
                if(entity is Player) {
                    //  Command
                    Bukkit.dispatchCommand(entity, "spawn")
                }else {
                    //  check if entity is mob
                    if(entity !is Creature)continue
                    //  落下ダメージ
                    RecordUtils.damage(entity)
                }
            }
        }
    },
    //  自身のエフェクトリセット(Milkと同じ)
    RecordMall{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            for(effect in player.activePotionEffects){
                player.removePotionEffect(effect.type)
            }
        }
    },
    //  周囲３ブロックのプレーヤーに俊敏20秒3レベル
    RecordMellohi{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            val effect = PotionEffect(PotionEffectType.SPEED, 20 * 20, 3)
            for(entity in player.getNearbyEntities(3.0, 3.0, 3.0)){
                if(entity is Player) {
                    entity.addPotionEffect(effect)
                }
            }
            player.addPotionEffect(effect)
        }
    },
    //  20秒間の再生3
    RecordStal{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            val effect = PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 3)
            player.addPotionEffect(effect)
        }
    },
    //  60秒の水中呼吸
    RecordStrad{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            val effect = PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 60, 1)
            player.addPotionEffect(effect)
        }
    },
    RecordWait{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
            val effect = PotionEffect(PotionEffectType.HEAL, 1, 3)
            player.addPotionEffect(effect)
        }
    },
    RecordWard{
        override fun run(player:Player){
            if(!RecordUtils.dice()){
                RecordUtils.cannotUse(player)
                return
            }
        }
    };

    open fun run(player: Player){}

}