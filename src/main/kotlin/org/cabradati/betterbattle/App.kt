package org.cabradati.betterbattle

import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.betterbattle.sistemas.arrowhitkill.ArrowHitKillContainer
import org.cabradati.betterbattle.sistemas.capacetedrop.CapaceteDropContainer
import org.cabradati.betterbattle.sistemas.nocaute.NocauteContainer
import org.cabradati.betterbattle.sistemas.utils.SistemaContainer

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

        val listaContainer: List<SistemaContainer> = listOf(
            ArrowHitKillContainer(diContainer),
            CapaceteDropContainer(diContainer),
            NocauteContainer(diContainer)
        )

        listaContainer.forEach { config ->
            config.registerConfig()
        }

        saveConfig()

        if (config.getBoolean(ATIVAR_PLUGIN)) {

            listaContainer.forEach { config ->
                config.registerEvents()
                config.registerSchedulers()
            }

        } else {
            logger.info("plugin - plugin está desabilitado, nenhum evento será registrado")
        }

        super.onEnable()
    }

}