package com.closeratio.aoc.common

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@ComponentScan("com.closeratio")
@EnableAsync
class CommonConfig
