package kr.cosine.resourcepack.config

import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.yaml.config.HQYamlConfiguration

@Bean
class SettingConfig(
    private val config: HQYamlConfiguration
) {

    val host get() = config.getString("web-server.host")

    val port get() = config.getInt("web-server.port")

    fun reload() {
        config.reload()
    }
}