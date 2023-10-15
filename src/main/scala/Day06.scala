import scala.io.{BufferedSource, Source}
import scala.util.{Try, Success, Failure}
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day 6
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 6 puzzles.
 */
class Day06:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run = Day06.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day06.puzzleTitle}"

end Day06

object Day06 {

    // created 9/24/2023
    // https://adventofcode.com/2015/day/6

    val puzzleTitle = "Day 6: Probably a Fire Hazard"
    val day = "Day06"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): Day06 = {
        var p = new Day06()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day06 = {
        var p = new Day06()
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

        // Puzzle Input Data File
        val filename = if (runType == 1) testData else realData
        val path = java.nio.file.Paths.get("./input").toAbsolutePath().toString
        val text = readTextFile(s"$path/$filename")
        text match
            case Success(contents) => println(contents.head)
            case Failure(exception) => {
                System.err.println(s"Exiting. ERROR! ${exception.getMessage}")
                System.exit(1)
            }
        val input = text.get
        println(s"Start Timestamp ${ZonedDateTime.now()}")

        println("\nData Quality Control:")
        println(s"Input file name: $filename")
        println(s"Each line is a: ${input.getClass}")
        println(s"Number lines: ${input.length}")
        println(s"Number items per line: ${input.head.count(_ => true)}")
        println(s"Input first line: ${input.head.take(72)}")
        if (input.size > 1) println(s"Input last line: ${input.tail.last}")
        println("End QC on input file\n")

        // Commmon to both parts


        // Part One
        println(s"Part 1: how many lights are lit?")
        val p1T0 = Instant.now()



        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 elased time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: ???")
        val p2T0 = Instant.now()



        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 elapsed time approx ${delta2.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")

        // errata...for visualation with Excel chart



    }
}
