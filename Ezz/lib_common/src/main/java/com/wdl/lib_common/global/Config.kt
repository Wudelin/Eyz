package com.wdl.lib_common.global

const val CONFIG_API_URL = "config_api_url"

class Config {
    private val CONFIG = mutableMapOf<Any, Any>()

    fun init(baseUrl: String) {
        CONFIG[CONFIG_API_URL] = baseUrl
    }

    fun <T> getConfiguration(key: Any): T {
        val value = CONFIG[key]

        require(value != null)

        return value as T
    }
}