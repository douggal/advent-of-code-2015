import scala.collection.mutable.{ArrayBuffer, Queue}
/*

                  0
          1              2
      3     4        5      6
            7        8

 */
val g = scala.collection.immutable.Map[Int, Seq[Int]](
    (0 -> Seq(1, 2)),
    (1 -> Seq(3,4)),
    (2 -> Seq(5, 6)),
    (3 -> Seq()),
    (4 -> Seq(7)),
    (5 -> Seq(8)),
    (6 -> Seq()),
    (7 -> Seq()),
    (8 -> Seq())
)
g.foreach(println)

// prev - given a node (key) value is node's parent
val prev = scala.collection.mutable.ArrayBuffer[Int]()
// initialize parent of each node to empty string
for i <- 0 until n do
    prev.append(0)

val n = 9
val goal = 3

def solve(s: Int):Unit = {
    // initialize queue and add starting node
    val q = scala.collection.mutable.Queue[Int](s)

    // nodes visited
    val explored = scala.collection.mutable.ArrayBuffer[Boolean]()
    for i <- 0 until n do
        explored.append(false)

    while !q.isEmpty do
        val node = q.dequeue()
        println(s"Visited node $node")

        // add all node's neighbors to the the queue, if not already there
        for n <- g(node) do
            // ignore n if already explored
            // else add to queue
            if explored(n) == false then
                explored(n) = true
                q.enqueue(n)
                prev(n) = node  // n's parent is "node"
            end if
        end for
    end while
}

def reconstructPath(s: Int, e: Int): ArrayBuffer[Int] = {
    val path = ArrayBuffer[Int]()
    for i <- prev do
        path += i

    val pathr = path.reverse

    if pathr(0) == s then
        pathr
    else
        ArrayBuffer[Int]()
}

def BSF(s: Int, e: Int): ArrayBuffer[Int] = {
    // do a BSF of g starting at s
    solve(s)
    // return reconstructed path s -> e
    reconstructPath(s, e)
}

BSF(0, 3).foreach(println)
