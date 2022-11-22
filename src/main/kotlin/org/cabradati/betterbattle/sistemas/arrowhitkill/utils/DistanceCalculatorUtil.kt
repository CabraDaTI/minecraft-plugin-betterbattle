package org.cabradati.betterbattle.sistemas.arrowhitkill.utils

import org.bukkit.Location
import kotlin.math.sqrt

interface DistanceCalculatorUtil {
    companion object {
        fun calculate(atirador: Location, flecha: Location): Double {

            val x1: Int
            val x2: Int
            val z1: Int
            val z2: Int

            val localizacaoAtiradorX: Int = atirador.blockX
            val localizacaoAtiradorZ: Int = atirador.blockZ
            val localizacaoFlechaX: Int = flecha.blockX
            val localizacaoFlechaZ: Int = flecha.blockZ

            if (localizacaoAtiradorX > localizacaoFlechaX) {
                x1 = localizacaoAtiradorX
                x2 = localizacaoFlechaX
            } else {
                x1 = localizacaoFlechaX
                x2 = localizacaoAtiradorX
            }

            if (localizacaoAtiradorZ > localizacaoFlechaZ) {
                z1 = localizacaoAtiradorZ
                z2 = localizacaoFlechaZ
            } else {
                z1 = localizacaoFlechaZ
                z2 = localizacaoAtiradorZ
            }

            val z = z1 - z2
            val x = x1 - x2
            val distsquared = z * z + x * x

            return sqrt(distsquared.toDouble())

        }
    }
}