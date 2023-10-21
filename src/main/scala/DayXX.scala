import scala.io.{BufferedSource, Source}
import scala.util.{Try, Success, Failure}
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day XX
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day XX puzzles.
 */
class DayXX:

    var title: String = "Advent of Code 2015"
    var runType: Int = 1 // Default to test data

    def runPuzzle():Unit = DayXX.runPuzzle(runType)

    override def toString: String = s"Class ${DayXX.puzzleTitle}"

end DayXX

object DayXX {

    // created 9/24/2023
    // https://adventofcode.com/2015/day/X
    val day = "XX"
    val puzzleTitle = "Day XX: ... "

    // input data files
    private val testData: String = s"${day}-test.txt"
    private val realData: String = s"${day}-input.txt"

    // a one-arg constructor
    def apply(runType: Int): DayXX = {
        var p = new DayXX()
        p.runType = runType
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): DayXX = {
        var p = new DayXX()
        p.title = title
        p.runType = runType
        p
    }

    private def runPuzzle(runType: Int): Unit = {

        println(s"--- Advent of Code 2015 ---")
        println(s"--- $puzzleTitle ---\n")

        // Read the puzzle input data file
        print("Attempting to read input data file using ")
        if (runType == 1)
            println("TEST DATA ... ")
        else
            println("REAL INPUT DATA ...")
        val filename = if (runType == 1) testData else realData

        // simple text file read:  Jan-Pieter van den Heuvel - Saving Christmas Using Scala  https://www.youtube.com/watch?v=tHU36gQ5iAI
        val input = Source.fromResource(filename).getLines().toVector

        println("\nData Quality Control:")
        println(s"  Input file name: $filename")
        println(s"  Each line is a: ${input.head.getClass}")
        println(s"  Number lines: ${input.length}")
        println(s"  Number items per line: ${input.head.count(_ => true)}")
        println(s"  First line (first 72 chars): ${input.head.take(72)}")
        if (input.size > 1)
            println(s"  Last line: ${input.last}")
        println("End QC on input file\n")

        // Common to both parts


        // ----------
        //  Part One
        // ----------
        println(s"Part 1: ???")
        val p1T0 = Instant.now()



        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 run time approx ${delta1.toMillis} milliseconds\n")


        // ----------
        //  Part Two
        // ----------
        println(s"Part 2: ???")
        val p2T0 = Instant.now()



        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 run time approx ${delta2.toMillis} milliseconds")

        // errata...for visualation with Excel chart

    }
}
