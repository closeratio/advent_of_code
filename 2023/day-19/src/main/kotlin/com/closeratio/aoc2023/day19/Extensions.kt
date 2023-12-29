val LongRange.size: Long
    get() = if (!isEmpty()) (last - first) + 1 else 0

fun LongRange.intersect(other: LongRange): LongRange =
    maxOf(first, other.first)..minOf(last, other.last)
