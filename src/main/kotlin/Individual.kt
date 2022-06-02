class Individual private constructor(
    val genes: MutableList<Binary>
) {
    fun getFitness() = genes.count { it.isSet() }
    fun numberOfGenes() = genes.size.toUByte()
    fun chromosome() = genes.joinToString()

    override fun hashCode(): Int {
        return chromosome().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Individual) {
            return false
        }
        return other.chromosome() == chromosome()
    }

    override fun toString() = "${getFitness()}"

    companion object {
        fun create(numberOfGenes: UByte) = Individual(
            numberOfGenes.downTo(1U).map { getRandomBinaryValue() }.toMutableList()
        )
    }
}
