package kr.cosine.resourcepack

import kr.cosine.resourcepack.service.ResourcePackService
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup
import kr.hqservice.framework.bukkit.core.component.module.Teardown

@Module
class HQResourcePackModule(
    private val resourcePackService: ResourcePackService
) {

    @Setup
    fun setup() {
        resourcePackService.loadResourcePack()
    }

    @Teardown
    fun teardown() {
        resourcePackService.stopHttpServer()
    }
}