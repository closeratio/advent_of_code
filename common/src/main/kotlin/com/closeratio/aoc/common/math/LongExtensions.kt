package com.closeratio.aoc.common.math

import java.math.BigInteger

fun Collection<Long>.lcm(): Long {
    check(isNotEmpty()) { "Can't find LCM of an empty list" }

    if (size == 1) {
        return first()
    }

    return reduce { acc, curr -> acc.lcm(curr) }
}

fun Long.lcm(other: Long): Long {
    val thisBigInt = BigInteger.valueOf(this)
    val otherBigInt = BigInteger.valueOf(other)

    val gcd = thisBigInt.gcd(otherBigInt)
    val absProduct = thisBigInt.multiply(otherBigInt).abs()
    return absProduct.divide(gcd).toLong()
}
