package kr.cosine.resourcepack.command

import kr.cosine.resourcepack.service.ResourcePackService
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender

@Command(label = "리소스팩관리", isOp = true)
class ResourcePackAdminCommand(
    private val resourcePackService: ResourcePackService
) {

    @CommandExecutor("리로드", "리소스팩을 리로드합니다.")
    fun reloadResourcePack(sender: CommandSender) {
        resourcePackService.reloadResourcePack()
        sender.sendMessage("§a리소스팩을 리로드하였습니다.")
    }
}