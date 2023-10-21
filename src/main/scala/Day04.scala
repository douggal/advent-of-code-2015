import java.time.{Duration, Instant, ZonedDateTime}

/** Advent of Code 2015 Day 4
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 4 puzzles.
 */
class Day04:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run: Unit = Day04.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day04.puzzleTitle}"

end Day04

object Day04 {

    // created 9/23/2023
    // https://adventofcode.com/2015/day/3

    val puzzleTitle = "Day 4: The Ideal Stocking Stuffer"
    val day = "04"
    private val testData: String = s"${day}-test.txt"
    private val realData: String = s"${day}-input.txt"

    // a one-arg constructor
    def apply(title: String): Day04 = {
        val p = new Day04()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day04 = {
        var p = new Day04()
        p.title = title
        p.runType = runType
        p
    }

    private def runPuzzle(title: String, runType: Int): Unit = {

        println(s"--- $title ---")
        println(s"--- $puzzleTitle ---")
        if (runType == 1)
            println("--- USING TEST DATA ---\n")
        else
            println("--- USING REAL INPUT DATA ---\n")

        println(s"Start Timestamp ${ZonedDateTime.now()}")

        // Commmon to both parts
        // input data - no file for this day only short string:
        val input = if (runType == 2) "ckczppom" else "pqrstuv"

        // MD5:  credit, Alvin Alexander for Scala method.  I've modified from original:
        // https://alvinalexander.com/source-code/scala-method-create-md5-hash-of-string/
        // returns a 32-character MD5 hash version of the input string
        import java.math.BigInteger
        import java.security.MessageDigest
        def md5HashPassword(usPassword: String): String = {
            val md = MessageDigest.getInstance("MD5")
            val digest: Array[Byte] = md.digest(usPassword.getBytes)
            val bigInt = new BigInteger(1, digest)
            val hashedPassword = bigInt.toString(16).trim
            prependWithZeros(hashedPassword)
        }

        /**
         * This uses a little magic in that the string I start with is a
         * “format specifier,” and it states that the string it returns
         * should be prepended with blank spaces as needed to make the
         * string length equal to 32. Then I replace those blank spaces
         * with the character `0`.
         */
         def prependWithZeros(pwd: String): String =
            "%1$32s".format(pwd).replace(' ', '0')

        // END MD5 methods from Alvin Alexander

        // Part One
        println(s"Part 1: find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.")
        val p1T0 = Instant.now()

        // 1st try I'll take the brute force/naive approach with IP methods
        // Turn each Int into a string, concat with prefix, run MD5, check for 5 leading zeros
        // Continue iterating until smallest number combined with prefix produces
        // a MD5 has with 5 leading 0's is found.

        val limit = 1_000_000_000
        var i = -1
        var found = false
        while (!found && i < limit)
            i += 1
            found = md5HashPassword(input + i.toString).startsWith("00000")

        println(s"Answer smallest Int is $i")

        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 elased time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: smallest number combined with prefix produces a MD5 has with *6* leading 0's is found")
        val p2T0 = Instant.now()

        // 1st try I'll take the brute force/naive approach with IP methods
        // Turn each Int into a string, concat with prefix, run MD5, check for 6 leading zeros
        // Continue iterating until smallest number combined with prefix produces
        // a MD5 has with *6* leading 0's is found.

        val limit2 = 1_000_000_000
        var i2 = -1
        var found2 = false
        while (!found2 && i2 < limit2)
            i2 += 1
            found2 = md5HashPassword(input + i2.toString).startsWith("000000")

        println(s"Answer smallest Int is $i2")


        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 elapsed time approx ${delta2.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")

        // errata...for visualation with Excel chart

    }
}
