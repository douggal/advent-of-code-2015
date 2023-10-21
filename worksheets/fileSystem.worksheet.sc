println(System.getProperty("user.dir"))

val p = System.getProperty("user.dir")

import java.nio.file.Paths
val q = Paths.get(".").toAbsolutePath


// https://www.baeldung.com/scala/read-file-from-resources
import java.io.File

def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).toList
    } else {
        List[File]()
    }
}

val files = getListOfFiles("src/main/resources")
    .map(f => f.getName)
files.foreach(println)
