import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

@ExperimentalUnsignedTypes
class UtilsTest {
    @Test
    fun `getRandomBinaryValue returns a number which either is 0 or 1`() {
        repeat(100) {
            val result = getRandomBinaryValue()
            assertTrue(result.isNotSet() || result.isSet())
        }
    }
}