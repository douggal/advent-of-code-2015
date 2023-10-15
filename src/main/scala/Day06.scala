import scala.io.{BufferedSource, Source}
import scala.util.{Try, Success, Failure}
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/** Advent of Code 2015 Day 6
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 6 puzzles.
 */
class Day06:

    var title: String = "Advent of Code 2015"
    var runType: Int = 1 // Default to test data

    def run = Day06.runPuzzle(runType)

    override def toString: String = s"Class ${Day06.puzzleTitle}"

end Day06

object Day06 {

    // created 9/24/2023
    // https://adventofcode.com/2015/day/6
    val day = "06"
    val puzzleTitle = "Day 6: Probably a Fire Hazard"

    // input data files
    private val testData: String = s"Day${day}TestData.txt"
    private val realData: String = s"Day${day}Input.txt"

    // a one-arg constructor
    def apply(runType: Int): Day06 = {
        var p = new Day06()
        p.runType = runType
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day06 = {
        var p = new Day06()
        p.title = title
        p.runType = runType
        p
    }

    // Read text file method (modified from original) from Alvin Alexander.  Thanks Al!
    // _Learn Scala 3 The Fast Way!: Book 1, The Adventure Begins_  Amazon Kindle edition, September 2022
    def readTextFile(filename: String): Try[List[String]] = {
        var source: BufferedSource = null
        try
            source = Source.fromFile(filename, "UTF-8")
            val lines = source.getLines().toList
            Success(lines)
        catch
            case t: Throwable => Failure(t)
        finally
            if source != null then
                source.close
    }

    private def runPuzzle(runType: Int): Unit = {

        println(s"--- Advent of Code 2015 ---")
        println(s"--- $puzzleTitle ---\n")

        // Read the puzzle input data file
        print("Attempting to read input data file using ")
        if (runType == 1) then
            println("TEST DATA ... ")
        else
            println("REAL INPUT DATA ...")
        val filename = if (runType == 1) testData else realData
        val path = java.nio.file.Paths.get("./input").toAbsolutePath().toString
        val text = readTextFile(s"$path/$filename")
        text match
            case Success(contents) => println(s"input file successfully read in.")
            case Failure(exception) => {
                System.err.println(s"exiting. ERROR! ${exception.getMessage}")
                System.exit(1)
            }
        val input = text.get

        println("\nData Quality Control:")
        println(s"  Input file name: $filename")
        println(s"  Each line is a: ${input.head.getClass}")
        println(s"  Number lines: ${input.length}")
        println(s"  Number items per line: ${input.head.count(_ => true)}")
        println(s"  First line: ${input.head.take(72)}")
        if (input.size > 1)
            println(s"  Last line: ${input.last}")
        println("End QC on input file\n")

        // Commmon to both parts


        // Part One
        println(s"Part 1: how many lights are lit?")
        val p1T0 = Instant.now()

        // model the square grid of 1000 x 1000 array as a 1d sequence of integers, 0 = off, 1 = on
        // origin top-left, x's go to the right, y's go down
        // that is, to traverse each row cell by cell, the outer loop is over the y's (the rows)
        // index 0 in the 1d grid is empty
        val offset = 100
        val grid = ArrayBuffer[Int](offset*offset)

        for
            y <- 0 to offset
            x <- 0 to offset
        do
            grid.append(0)
            // println(s"(${x} ${y}) => ${y * offset + x + 1}, ")

        val pat = raw"(turn on|toggle|turn off) (\d{1,},\d{1,}) through (\d{1,},\d{1,})".r
        for line <- input do {
            val pat(cmd, corner1, corner2) = line

        }




        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 run time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: ???")
        val p2T0 = Instant.now()



        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 run time approx ${delta2.toMillis} milliseconds")

        // errata...for visualation with Excel chart

    }
}
