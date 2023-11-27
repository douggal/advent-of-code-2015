import scala.io.Source
import java.time.{Duration, Instant}

/** Advent of Code 2015 Day XX
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day XX puzzles.
 */
class DayXX private (val title: String, val runType: Int ):

    //var title: String = "Advent of Code 2015"
    //var runType: Int = 1 // Default to test data
    def run():Unit = DayXX.runPuzzle(runType)

    override def toString: String = s"Class ${DayXX.puzzleTitle}"

end DayXX

object DayXX:

    // created 10/21/2023
    // https://adventofcode.com/2015/day/7
    val day = "XX"
    val puzzleTitle = "Day 7: Some Assembly Required"

    // input data files
    private val testData: String = s"$day-test.txt"
    private val realData: String = s"$day-input.txt"

    // Factory methods
    // a one-arg constructor
    def apply(runType: Int): DayXX = {
        new DayXX("AoC", runType)
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): DayXX = {
        new DayXX(title, runType)
    }

    private def runPuzzle(runType: Int): Unit = {

        println(s"--- Advent of Code 2015 ---")
        println(s"--- $puzzleTitle ---\n")

        // Read the puzzle input data file
        val filename = if (runType == 1) testData else realData
        print("Attempting to read input data file using ")
        if runType == 1 then
            println("TEST DATA ... ")
        else
            println("REAL INPUT DATA ...")

        // simple text file read:  Jan-Pieter van den Heuvel [Saving Christmas Using Scala](https://www.youtube.com/watch?v=tHU36gQ5iAI)
        val input = Source.fromResource(filename).getLines().toVector

        println("\nData Quality Control:")
        println(s"  Input file name: $filename")
        println(s"  Each line is a: ${input.head.getClass}")
        println(s"  Number lines: ${input.length}")
        println(s"  Number items per line: ${input.head.count(_ => true)}")
        println(s"  First line: ${input.head}")
        if input.size > 1 then
            println(s"  Last line: ${input.last}")
        println

        // Common to both parts
        // code goes here ...

        // ----------
        //  Part One
        // ----------
        println(s"Part 1: TBD ???")
        val p1T0 = Instant.now()


        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 run time approx ${delta1.toMillis} milliseconds\n")


        // ----------
        //  Part Two
        // ----------
        println(s"Part 2: TBD ???")
        val p2T0 = Instant.now()



        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 run time approx ${delta2.toMillis} milliseconds")

        // errata...for visualization with Excel chart

    }

end DayXX
