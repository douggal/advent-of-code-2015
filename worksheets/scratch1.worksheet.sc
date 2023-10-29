
val pat = raw"(turn on|toggle|turn off) (\d+,\d+) through (\d+,\d+)".r
val line = "turn on 0,0 through 999,999"
val pat(cmd, corner1, corner2) = line : @unchecked


def parseCoord(corner: String): (Int, Int) = {
    val coords = corner.split(',').map(_.toInt)
    (coords(0), coords(1))
}
parseCoord("99,100")
parseCoord("100,1")

// exclusive OR - flip the bit
val bit = 0
bit ^ 1

val bit2 = 1
bit2 ^ 1

val wires = scala.collection.mutable.Map[String, Int]()
val w = "a"
val l = "x"
val r = "y"
wires(w) = 5
wires(l) = 3
wires(r) = 1

wires += w -> 3
wires(w) = wires(l) & wires(r)

def int2bin(i: Int, numPos: Int): String = {
    def nextPow2(i: Int, acc: Int): Int = if (i < acc) acc else nextPow2(i, 2 * acc)
    (nextPow2(i, math.pow(2, numPos).toInt) + i).toBinaryString.substring(1)
}
println(wires(w).toBinaryString)
println(int2bin(wires(w),16))
