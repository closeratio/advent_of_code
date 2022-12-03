package com.closeratio.aoc.common

import org.springframework.boot.test.context.SpringBootTest
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@SpringBootTest(
    classes = [CommonConfig::class]
)
@Target(CLASS)
@Retention(RUNTIME)
annotation class AocTest
