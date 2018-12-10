import java.io.File

var recipes = arrayOf(
        Recipe(name = "Tests fail", symptoms = arrayOf("A1", "B2"), diagnosis = "Tests failed. Fix them"),
        Recipe(name = "Tests fail (reverse)", symptoms = arrayOf("B2", "A1"), diagnosis = "Tests failed (Reverse). Fix them"),
        Recipe(name = "Test(instrumented) crash", symptoms = arrayOf("A3"), diagnosis = "Test(instrumented) crash, check the test logic"),
        Recipe(name = "Impossible", symptoms = arrayOf("T3"), diagnosis = "Impossible")
)

main(args[0])

fun main(filepath: String) {

    val blob = readFileBlob(filepath)

    for (r in recipes){
        println("Does it match Recipe ${r.name}? " + if (r.matches(blob)) "Yes -> ${r.diagnosis}" else "No")
    }
}

fun String.findRegex(r : Regex, startIndex : Int = 0): MatchResult? =  r.find(this, startIndex)

fun readFileBlob(path : String) : String {
    var b = ""
    File(path).readLines().forEach {b += "$it\n"}
    return b
}

/*assume all symptoms are positive*/
class Recipe(var name: String, var symptoms: Array<String> , var diagnosis: String) {
    fun matches(blob : String) : Boolean {
        val regexSeparator = "((\\n)*.*)*"
        var generatedRegexString = ""
        for (s in symptoms) {
            generatedRegexString += s + regexSeparator
        }
        return blob.findRegex(Regex(generatedRegexString)) != null
    }
}