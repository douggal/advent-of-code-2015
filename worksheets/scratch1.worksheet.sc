
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