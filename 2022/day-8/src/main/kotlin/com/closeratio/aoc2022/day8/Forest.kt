package com.closeratio.aoc2022.day8

data class Forest(
    val trees: List<List<Tree>>
) {

    private val width = trees.first().size
    private val height = trees.size
    fun computeVisibleTreeCount(): Int {
        val maxX = width - 1
        val maxY = height - 1

        val horizontalLines = (0..maxY).flatMap { y ->
            listOf(
                (0..maxX).map { x ->
                    trees[y][x]
                },
                (maxX downTo 0).map { x ->
                    trees[y][x]
                }
            )
        }

        val verticalLines = (0..maxX).flatMap { x ->
            listOf(
                (0..maxY).map { y ->
                    trees[y][x]
                },
                (maxY downTo 0).map { y ->
                    trees[y][x]
                }
            )
        }

        val visibleTrees = (horizontalLines + verticalLines)
            .flatMap { treeLine ->
                treeLine.fold(emptyList<Tree>() to -1) { (visibleTrees, highestSeen), tree ->
                    if (highestSeen == 9 || tree.height <= highestSeen) {
                        visibleTrees to highestSeen
                    } else {
                        (visibleTrees + tree) to tree.height
                    }
                }.first
            }
            .toSet()

        return visibleTrees.size
    }

    private fun treesNorthOf(tree: Tree): List<Tree> = ((tree.position.y.toInt() - 1) downTo 0)
        .map { trees[it][tree.position.x.toInt()] }

    private fun treesSouthOf(tree: Tree): List<Tree> = ((tree.position.y.toInt() + 1) until height)
        .map { trees[it][tree.position.x.toInt()] }

    private fun treesEastOf(tree: Tree): List<Tree> = ((tree.position.x.toInt() + 1) until width)
        .map { trees[tree.position.y.toInt()][it] }

    private fun treesWestOf(tree: Tree): List<Tree> = ((tree.position.x.toInt() - 1) downTo 0)
        .map { trees[tree.position.y.toInt()][it] }

    private fun computeViewDistance(
        tree: Tree,
        adjacentTrees: List<Tree>
    ): Int {
        val blockingTreeIndex = adjacentTrees.indexOfFirst { it.height >= tree.height }

        return adjacentTrees.subList(
            0,
            if (blockingTreeIndex != -1) blockingTreeIndex + 1 else adjacentTrees.size
        ).size
    }

    private fun computeScenicScore(tree: Tree): Int = listOf(
        treesNorthOf(tree),
        treesSouthOf(tree),
        treesEastOf(tree),
        treesWestOf(tree)
    ).map { computeViewDistance(tree, it) }
        .reduce { acc, curr -> acc * curr }

    fun computeOptimalTreehouseScenicScore(): Int = trees
        .flatten()
        .maxOf(::computeScenicScore)
}
