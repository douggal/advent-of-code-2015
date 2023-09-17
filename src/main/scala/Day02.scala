import java.io.File
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day 2
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 2 puzzles.
 */
class Day02:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run = Day02.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day02.puzzleTitle}"

end Day02

object Day02 {

    // created 9/8/2023
    // https://adventofcode.com/2015/day/1

    val puzzleTitle = "Day 2: I Was Told There Would Be No Math"
    val day = "Day02"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): Day02 = {
        var p = new Day02()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day02 = {
        var p = new Day02()
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

        // Part One
        println(s"Part 1: How many total square feet of wrapping paper should they order??")
        val p1T0 = Instant.now()

        for (li <- input) do {
            val a = li.split("x").map(_.toInt)
            println(s"L: ${a(0)} x W: ${a(1)} x H: ${a(2)}")
        }


        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Elapsed time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: ?")
        val p2T0 = Instant.now()


        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Elapsed time approx ${delta1.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")
    }

}