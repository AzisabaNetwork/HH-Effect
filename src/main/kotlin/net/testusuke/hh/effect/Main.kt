package net.testusuke.hh.effect

import net.testusuke.hh.effect.item.enderpearl.EnderPearlListener
import net.testusuke.hh.effect.item.record.RecordListener
import net.testusuke.hh.effect.item.totem.ActivateTotem
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object{
        //  plugin instance
        lateinit var plugin: Main
    }

    override fun onEnable() {
        //  instance
        plugin = this

        /* ------------ Config ------------ */

        /* ------------ Event Listener ------------ */
        val listeners = arrayListOf<Listener>() //  Listener List
        ////    Item
        listeners.add(RecordListener)   //  Recode
        listeners.add(ActivateTotem)    //  Totem
        listeners.add(EnderPearlListener)   //  EnderPearl

        ////  register events
        listeners.forEach { listener ->
            server.pluginManager.registerEvents(listener,this)
        }

        /* --------------- CommandExecutor --------------- */

    }

    override fun onDisable() {

    }
}