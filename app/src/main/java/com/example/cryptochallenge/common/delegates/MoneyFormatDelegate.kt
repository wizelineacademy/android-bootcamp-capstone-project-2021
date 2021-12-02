package com.example.cryptochallenge.common.delegates

import java.lang.Exception
import java.text.NumberFormat
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MoneyFormatDelegate : ReadWriteProperty<Any, String> {
    private var moneyFormattedString = "$0.00"
    override operator fun getValue(thisRef: Any, property: KProperty<*>) = moneyFormattedString
    override operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        var doubleNumber: Double
        try {
            doubleNumber = value.toDouble()
            moneyFormattedString = "$${NumberFormat.getInstance().format(doubleNumber)} :)"
        } catch (e: Exception) {
            moneyFormattedString = "$0.0"
        }
    }

    operator fun getValue(thisRef: Nothing?, property: KProperty<*>) = moneyFormattedString
    operator fun setValue(thisRef: Nothing?, property: KProperty<*>, value: String) {
        var doubleNumber: Double
        try {
            doubleNumber = value.toDouble()
            moneyFormattedString = "$${NumberFormat.getInstance().format(doubleNumber)}"
        } catch (e: Exception) {
            moneyFormattedString = "$0.0"
        }
    }
}