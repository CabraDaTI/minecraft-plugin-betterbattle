package org.cabradati.betterbattle.sistemas.arrowhitkill

import org.cabradati.betterbattle.App

class ArrowHitKillConsts {

    companion object {
        private const val PREFIX = App.SYSTEMAS_PREFIX + "arrowhitkill."
        const val ATIVAR_SISTEMA = PREFIX + "enabled"
        const val DISTANCE = PREFIX + "distance"
        const val DANO_PELA_METADE = PREFIX + "half_damage"
    }

}