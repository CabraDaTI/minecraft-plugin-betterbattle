package org.cabradati.betterbattle.sistemas.arrowhitkill

import org.cabradati.betterbattle.DIContainer
import org.cabradati.betterbattle.sistemas.arrowhitkill.events.ArrowHitKillEvent

class ArrowHitKillContainer(private val diContainer: DIContainer) {

    fun registerConfig() {

        val config = diContainer.config
        val plugin = diContainer.plugin

        diContainer.log("sistema - arrow hit kill - registrando configurações")
        config.addDefault(ArrowHitKillConsts.ATIVAR_SISTEMA, true)
        config.addDefault(ArrowHitKillConsts.DISTANCE, 28)
        config.addDefault(ArrowHitKillConsts.DANO_PELA_METADE, true)
        config.options().copyDefaults(true)
        plugin.saveConfig()

    }

    fun registerEvents() {

        val config = diContainer.config
        val plugin = diContainer.plugin
        val server = diContainer.server

        if (config.getBoolean(ArrowHitKillConsts.ATIVAR_SISTEMA)) {

            diContainer.log("sistema - arrow hit kill - registrando eventos")
            server.pluginManager.registerEvents(
                ArrowHitKillEvent(diContainer),
                plugin
            )

        } else {
            diContainer.log("sistema - arrow hit kill - eventos estão desabilitados")
        }

    }

}