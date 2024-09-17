package kr.cosine.resourcepack.listener

import kr.cosine.resourcepack.service.ResourcePackService
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe
import org.bukkit.event.player.PlayerJoinEvent

@Listener
class ResourcePackListener(
    private val resourcePackService: ResourcePackService
) {

    @Subscribe
    fun onPlayerJoin(event: PlayerJoinEvent) {
        resourcePackService.applyResourcePack(event.player)
    }
}