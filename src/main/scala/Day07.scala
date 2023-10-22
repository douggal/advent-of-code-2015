import scala.io.{BufferedSource, Source}
import scala.util.{Try, Success, Failure}
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day 07
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 07 puzzles.
 */
class Day07:

    var title: String = "Advent of Code 2015"
    var runType: Int = 1 // Default to test data
    def run():Unit = Day07.runPuzzle(runType)

    override def toString: String = s"Class ${Day07.puzzleTitle}"

end Day07

object Day07 {

    // created 10/21/2023
    // https://adventofcode.com/2015/day/7
    val day = "07"
    val puzzleTitle = "Day 7: Some Assembly Required"

    // input data files
    private val testData: String = s"${day}-test.txt"
    private val realData: String = s"${day}-input.txt"

    // a one-arg constructor
    def apply(runType: Int): Day07 = {
        var p = new Day07()
        p.runType = runType
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day07 = {
        var p = new Day07()
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
        println(s"  First line (up to 72 chars): ${input.head.take(72)}")
        if (input.size > 1)
            println(s"  Last line: ${input.last}")
        println("End QC on input file\n")

        // Common to both parts

        // Note:  Scala does not have an unsigned int.  Discussion and display method here:
        // https://stackoverflow.com/questions/21212993/unsigned-variables-in-scala?noredirect=1&lq=1
        // one reply suggests up convert to a Long for display...
        // Convert it to the equivalent Scala BigInt
        def asUnsigned(unsignedLong: Long) =
            (BigInt(unsignedLong >>> 1) << 1) + (unsignedLong & 1)

        // ----------
        //  Part One
        // ----------
        println(s"Part 1: In little Bobby's kit's instructions booklet (provided as your puzzle input), what signal is ultimately provided to wire a?")
        val p1T0 = Instant.now()

        val wires = scala.collection.mutable.Map[String, Int]()

        // 123 -> x
        val assignValueRE = raw"([0-9]+) (->) ([a-z]+)".r

        // x AND y -> d
        val operationRE = raw"([a-z]+) (AND|OR|) ([a-z]+) (->) ([a-z]+)".r

        // x LSHIFT 2 -> d
        val shiftRE = raw"([a-z]+) (LSHIFT|RSHIFT) ([1-9]+) (->) ([a-z]+)".r

        // NOT x -> h
        val notRE = raw"(NOT) ([a-z]+) (->) ([a-z]+)".r

        // p = the thin arrow "->"
        for (line <- input)
            val results = line match
                case assignValueRE(v, p, w) => wires(w) = v.toInt
                case operationRE(l, op, r, p, w) => {
                    op match
                        case "AND" => wires(w) = wires(l) & wires(r)
                        case "OR" => wires(w) = wires(l) | wires(r)
                        case _ => ???
                }
                case shiftRE(l, op, r, p, w) => {
                    op match
                        case "LSHIFT" => wires(w) = wires(l) << r.toInt
                        case "RSHIFT" => wires(w) = wires(l) >>> r.toInt  // unsigned shift right
                        case _ => ???
                }
                case notRE(op, r, p, w) => wires(w) = -wires(r) - 1  // https://en.wikipedia.org/wiki/Bitwise_operation#NOT
                case _ => None

        for (w <- wires) do
            println(s"${w._1}: ${asUnsigned(w._2)}")

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
