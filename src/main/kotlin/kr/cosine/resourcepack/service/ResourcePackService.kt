package kr.cosine.resourcepack.service

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import kr.cosine.resourcepack.config.SettingConfig
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.zeroturnaround.zip.ZipUtil
import java.io.File
import java.security.MessageDigest

@Service
class ResourcePackService(
    plugin: Plugin,
    private val settingConfig: SettingConfig
) {

    private val messageDigest = MessageDigest.getInstance("SHA-1")

    private val dataFolder = plugin.dataFolder
    private val server = plugin.server

    private val inputFolder = File(dataFolder, "input")
    private val outputFolder = File(dataFolder, "output")

    private val generatedFile get() = File(outputFolder, "generated.zip")

    private var resourcePackUrl = ""

    private var httpServer: HttpServer? = null

    private fun startHttpServer() {
        val host = settingConfig.host
        val port = settingConfig.port

        resourcePackUrl = "http://$host:$port"

        httpServer = Vertx.vertx().createHttpServer()
        httpServer?.requestHandler { request ->
            val path = generatedFile.path
            request.response().sendFile(path)
        }
        httpServer?.listen(port)
    }

    fun stopHttpServer() {
        httpServer?.close()
        httpServer = null
    }

    fun loadResourcePack() {
        generateResourcePack()
        startHttpServer()
        applyResourcePack()
    }

    fun reloadResourcePack() {
        generateResourcePack()
        applyResourcePack()
    }

    fun applyResourcePack(player: Player) {
        val sha1 = messageDigest.digest(generatedFile.readBytes())
        val url = "$resourcePackUrl#$sha1"
        player.setResourcePack(url)
    }

    private fun applyResourcePack() {
        server.onlinePlayers.forEach(::applyResourcePack)
    }

    private fun generateResourcePack() {
        ZipUtil.pack(inputFolder, generatedFile)
    }
}