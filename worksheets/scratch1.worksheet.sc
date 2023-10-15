
val pat = raw"(turn on|toggle|turn off) (\d+,\d+) through (\d+,\d+)".r
val line = "turn on 0,0 through 999,999"
val pat(cmd, corner1, corner2) = line : @unchecked