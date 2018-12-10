import java.io.File

var recipes = arrayOf(
        Recipe(name = "Tests fail", symptoms = arrayOf(Regex("A1"), Regex("B2")), diagnosis = "Tests failed. Fix them"),
        Recipe(name = "Test(instrumented) crash", symptoms = arrayOf(Regex("A3")), diagnosis = "Test(instrumented) crash, check the test logic"),
        Recipe(name = "Impossible", symptoms = arrayOf(Regex("T3")), diagnosis = "Impossible")
)

main(args[0])

fun main(filepath: String) {

    val blob = readFileBlob(filepath)
    println("blob is like:\n$blob")

    for (r in recipes){
        println("Does it match Recipe ${r.name}? " + if (r.matches(blob)) "Yes -> ${r.diagnosis}" else "No")
    }
}

fun String.findRegex(r : Regex): Boolean =  r.find(this) != null

fun readFileBlob(path : String) : String {
    var b = ""
    File(path).readLines().forEach {b += "$it\n"}
    return b
}

/*assume all symptoms are positive*/
class Recipe(var name: String, var symptoms: Array<Regex> , var diagnosis: String) {
    fun matches(blob : String) : Boolean {
        var match = true
        for (sRegex in symptoms) {
            match = blob.findRegex(sRegex)
        }
        return match
    }
}
