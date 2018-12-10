import java.io.File

diagnosis(args[0])


fun diagnosis(r: String) {
	File(r).readLines().forEach {
		println(it)	
	}
}

