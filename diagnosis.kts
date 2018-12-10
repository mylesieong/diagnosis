import java.io.File

diagnosis(args[0])

fun diagnosis(filepath: String) {

	var blob = readFileBlob(filepath)
	var regex = Regex("A1((\n)*.*)*B1")

	println("blob is like:\n$blob")
	println(if (findRegex(blob, regex)) "Match result: yes" else "Match result: no")
}

fun findRegex(s: String, r : Regex) = r.find(s) != null

fun readFileBlob(path : String) : String {
	var b = ""
	File(path).readLines().forEach {b += "$it\n"}
	return b
}
