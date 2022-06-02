import kotlin.math.abs
import kotlin.random.Random

/**
 * Represents a binary state, so either set or unset. This is
 * used to represent the properties (genes) of individuals.
 */
class Binary(value: UByte) {
    private var value: UByte = if (value in 0u..1u) value else 0u
    fun isSet() = value == 1.toUByte()
    fun isNotSet() = value == 0.toUByte()
    fun reverse() {
        value = if (isSet()) {
            0u
        } else {
            1u
        }
    }

    override fun toString(): String = if (isSet()) "1" else "0"
}

fun getRandomBinaryValue() = Binary(abs(Random.nextInt() % 2).toUByte())
