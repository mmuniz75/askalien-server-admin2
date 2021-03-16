package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.domain.Usage
import edu.muniz.askalien.admin.domain.View
import edu.muniz.askalien.admin.dto.StatisticDTO
import edu.muniz.askalien.admin.services.StatisticService
import edu.muniz.askalien.admin.services.UsageService
import edu.muniz.askalien.admin.services.ViewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class StatisticController {

    @Autowired
    lateinit var service: StatisticService

    @Autowired
    lateinit var usageService: UsageService

    @Autowired
    lateinit var viewService: ViewService

    @GetMapping("/admin/statistics")
    fun getStatistics(): Mono<StatisticDTO> {
        return service.getAccessStatistic()
    }

    @GetMapping("/admin/usage/{year}")
    fun getUsage(@PathVariable year: Short): Flux<Usage> {
        return usageService.getUsageFromYear(year)
    }

    @GetMapping("/admin/usage")
    fun getAllUsage(): Flux<Usage> {
        return usageService.getUsageAllYears()
    }

    @GetMapping("/admin/view/{year}")
    fun getView(@PathVariable year: Short): Flux<View> {
        return viewService.getViewFromYear(year)
    }

    @GetMapping("/admin/view")
    fun getAllView(): Flux<View> {
        return viewService.getViewAllYears()
    }
}