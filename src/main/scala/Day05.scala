import java.io.File
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day 5
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 5 puzzles.
 */
class Day05:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run = Day05.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day05.puzzleTitle}"

end Day05

object Day05 {

    // created 9/24/2023
    // https://adventofcode.com/2015/day/5

    val puzzleTitle = "Day 5: Doesn't He Have Intern-Elves For This?"
    val day = "Day05"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): Day05 = {
        var p = new Day05()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day05 = {
        var p = new Day05()
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

        // Puzzle Input Data File
        val filename = if (runType == 1) testData else realData

        val readInputData = () => {
            val f = java.nio.file.Paths.get("./input").toAbsolutePath().toString
            val source = io.Source.fromFile(s"$f/$filename", "UTF-8")
            for {
                line <- source.getLines().toVector
            } yield
                line
        }

        val input = readInputData()
        /*for (line <- input) {
            println(line)
        }*/

        println(s"Start Timestamp ${ZonedDateTime.now()}")

        println("\nData Quality Control:")
        println(s"Input file name: $filename")
        println(s"Each line is a: ${input.getClass}")
        println(s"Number lines: ${input.length}")
        println(s"Number items per line: ${input.head.count(_ => true)}")
        println(s"Input first line: ${input.head.take(25)}")
        if (input.size > 1) println(s"Input last line: ${input.tail.last}")
        println("End QC on input file\n")

        // Commmon to both parts
        val vowels = "aeiou"

        // Part One
        println(s"Part 1: How many strings are nice?")
        val p1T0 = Instant.now()

        // Scala is fast.  Regex can be hard to debug and get right.
        // Use simple regex pattern with capture groups
        // and Scala methods to do counts on how many captures where found on each string.

        // at least 3 vowels
        val pattern1 = "(a|i|e|o|u)".r  // use findAllIn and count >= 3

        // at least 1 char that appears twice in a row
        val pattern2 = "(a{2,}|b{2,}|c{2,}|d{2,}|e{2,}|f{2,}|g{2,}|h{2,}|i{2,}|j{2,}|k{2,}|l{2,}|m{2,}|n{2,}|o{2,}|p{2,}|q{2,}|r{2,}|s{2,}|t{2,}|u{2,}|v{2,}|w{2,}|x{2,}|y{2,}|z{2,})".r  // use findAllIn and count >= 1

        // does NOT contain any of the following even if part of other pattern
        val pattern3 = "(ab|cd|pq|xy)".r  // use findAllIn if count > 0 then fail

        val nices = ArrayBuffer[String]()
        for s <- input
            if pattern1.findAllIn(s).size >= 3  // at least 3 vowels
            if pattern2.findAllIn(s).size >= 1  // at least 1 doubled char
            if pattern3.findAllIn(s).size == 0  // does not contain disallowed pattern
        do
            nices += s
            //println(s)

        println(s"Number of nice strings found is ${nices.length}")

        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 elased time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: TBD")
        val p2T0 = Instant.now()



        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 elapsed time approx ${delta2.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")

        // errata...for visualation with Excel chart



    }
}
