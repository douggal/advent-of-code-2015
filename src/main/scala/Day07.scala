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

        // Note 1: bitwise operators are defined on Int and not available for Short
        //
        // Note 2: Scala does not have an unsigned int.  Discussion and display method here:
        // https://stackoverflow.com/questions/21212993/unsigned-variables-in-scala?noredirect=1&lq=1
        // one reply suggests up convert to a Long for display...
        // Convert it to the equivalent Scala BigInt
        def asUnsigned(unsignedInt: Int) =
            (BigInt(unsignedInt >>> 1) << 1) + (unsignedInt & 1)

        // ----------
        //  Part One
        // ----------
        println(s"Part 1: In little Bobby's kit's instructions booklet what signal is ultimately provided to wire a?")
        val p1T0 = Instant.now()

        val circuit = scala.collection.mutable.Map[String, Int]()
        val waitList = scala.collection.mutable.ArrayBuffer[String]()

        // 123 -> x
        //  lx -> a
        val assignValueRE = raw"([0-9]+) (->) ([a-z]+)".r
        val copyRegisterRE = raw"([a-z]+) (->) ([a-z]+)".r

        // x AND y -> d
        // 1 AND r -> s
        val operationRE = raw"([a-z]+) (AND|OR) ([a-z]+) (->) ([a-z]+)".r
        val operationFixedRE = raw"([1-9]+) (AND|OR) ([a-z]+) (->) ([a-z]+)".r

        // x LSHIFT 2 -> d
        val shiftRE = raw"([a-z]+) (LSHIFT|RSHIFT) ([1-9]+) (->) ([a-z]+)".r

        // NOT x -> h
        val notRE = raw"(NOT) ([a-z]+) (->) ([a-z]+)".r

        // p = the thin arrow "->"
        for (line <- input)
            waitList += line
            val instructions = waitList.map(x => x)
            for li <- instructions do
                val results = li match
                    case assignValueRE(v, p, w) =>
                        circuit(w) = v.toInt
                        waitList -= li
                    case copyRegisterRE(l, p, w) =>
                        if circuit.contains(l) then
                            circuit(w) = circuit(l)
                            waitList -= li
                    case operationRE(l, op, r, p, w) =>
                       if circuit.contains(l) && circuit.contains(r) then
                           op match
                               case "AND" => circuit(w) = circuit(l) & circuit(r)
                               case "OR" => circuit(w) = circuit(l) | circuit(r)
                               case _ => ???
                           waitList -= li
                    case operationFixedRE(l, op, r, p, w) =>
                        if circuit.contains(r) then
                            op match
                                case "AND" => circuit(w) = l.toInt & circuit(r)
                                case "OR" => circuit(w) = l.toInt | circuit(r)
                                case _ => ???
                            waitList -= li
                    case shiftRE(l, op, r, p, w) => {
                        if circuit.contains(l) then
                            op match
                                case "LSHIFT" => {
                                    // println("LSHIFT")
                                    // println(s"circuit(l) = ${circuit(l).toBinaryString}")
                                    val x = circuit(l) << r.toInt
                                    // println(s"x = ${circuit(w).toBinaryString}")
                                    // move high 16 bits to right side of the lower 16 bits
                                    val hi = x & 0xffff0000
                                    val lo = x & 0x0000ffff
                                    // println(s"hi = ${hi.toBinaryString}")
                                    // println(s"lo = ${lo.toBinaryString}")
                                    circuit(w) = lo | hi
                                    // println(s"circuit(w) = ${circuit(w).toBinaryString}")
                                }
                                case "RSHIFT" => {
                                    // println("RSHIFT")
                                    // println(s"circuit(l) = ${circuit(l).toBinaryString}")
                                    val x = circuit(l) >>> r.toInt // unsigned shift right
                                    // println(s"x = ${circuit(w).toBinaryString}")
                                    // move high 16 bits to left side of the lower 16 bits
                                    val hi = x & 0xffff0000
                                    val lo = x & 0x0000ffff
                                    // println(s"hi = ${hi.toBinaryString}")
                                    // println(s"lo = ${lo.toBinaryString}")
                                    circuit(w) = hi | lo
                                    // println(s"circuit(w) = ${circuit(w).toBinaryString}")
                                }
                                case _ => ???
                            waitList -= li
                    }
                    case notRE(op, r, p, w) =>
                        if circuit.contains(r) then
                            // println("NOT")
                            // println(s"circuit(r) = ${circuit(r).toBinaryString}")
                            circuit(w) = ~circuit(r) & 0x0000ffff  // NOT, then must mask out the high 16 bits!
                            // println(s"circuit(w) = ${circuit(w).toBinaryString}")
                        waitList -= li;
                    case _ => println(s"FAIL: $li")

        val keys = circuit.keys.toVector.sortWith(_ < _)
        for (k <- keys) do
            println(s"wire $k: ${asUnsigned(circuit(k))}")

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
