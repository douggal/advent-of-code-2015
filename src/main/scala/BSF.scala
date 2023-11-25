@main
def BSF: Unit = {

    /* Breadth-first search example */

    import scala.collection.mutable.{ArrayBuffer, Queue}

    /*
    graph:
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

    // number nodes, goal of search
    val n = 9
    val goal = 3

    def solve(s: Int):ArrayBuffer[Int] = {
        // initialize queue and add starting node
        val q = scala.collection.mutable.Queue[Int](s)

        // prev -  is each node's parent (index gives node ID)
        val prev = scala.collection.mutable.ArrayBuffer[Int]()
        // initialize parent of each node to empty string
        for i <- 0 until n do
            prev.append(-1)

        // nodes visited
        val explored = scala.collection.mutable.ArrayBuffer[Boolean]()
        for i <- 0 until n do
            explored.append(false)


        while !q.isEmpty do
            val node = q.dequeue()
            explored(node) = true
            println(s"Visited node $node")

            // add all node's neighbors to the the queue, if not already there
            for neighbor <- g(node) do
                // ignore n if already explored
                // else add to queue
                if explored(neighbor) == false then
                    explored(neighbor) = true
                    q.enqueue(neighbor)
                    prev(neighbor) = node  // n's parent is "node"
                end if
            end for
        end while

        prev
    }

    def reconstructPath(s: Int, e: Int, prev:ArrayBuffer[Int]): ArrayBuffer[Int] = {
        val path = ArrayBuffer[Int]()
        var at = e
        while at != -1 do
            path += at
            at = prev(at)

        val pathr = path.reverse

        if pathr(0) == s then
            pathr
        else
            ArrayBuffer[Int]()
    }

    def bsf(s: Int, e: Int): ArrayBuffer[Int] = {
        // do a BSF of g starting at s
        val prev = solve(s)
        // return reconstructed path s -> e
        reconstructPath(s, e, prev)
    }

    bsf(0, 7).foreach(println)


}
