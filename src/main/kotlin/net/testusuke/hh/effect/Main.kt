package net.testusuke.hh.effect

import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object{
        //  plugin instance
        lateinit var plugin: Main
    }

    override fun onEnable() {
        //  instance
        plugin = this

    }

    override fun onDisable() {

    }
}