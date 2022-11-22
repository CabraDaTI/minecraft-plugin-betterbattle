package org.cabradati.betterbattle

import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.betterbattle.sistemas.arrowhitkill.ArrowHitKillContainer

class App : JavaPlugin() {

    companion object {
        const val ATIVAR_PLUGIN = "plugin.enabled"
        const val ATIVAR_DEBUG = "plugin.debug"
        const val SYSTEMAS_PREFIX = "systems."
    }

    override fun onEnable() {

        config.addDefault(ATIVAR_PLUGIN, true)
        config.addDefault(ATIVAR_DEBUG, false)
        config.options().copyDefaults(true)

        val diContainer = DIContainer(
            this,
            server,
            config,
            logger
        )

        val arrowHitKillContainer = ArrowHitKillContainer(diContainer)
        arrowHitKillContainer.registerConfig()

        saveConfig()

        if (config.getBoolean(ATIVAR_PLUGIN)) {

            arrowHitKillContainer.registerEvents()

        } else {
            logger.info("plugin - plugin está desabilitado, nenhum evento será registrado")
        }

        super.onEnable()
    }

}