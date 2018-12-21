# Diagnosis

* Diagnosis is a tool that analyze logs and give diagnosis.
* It is written in Kotlin
* The config DSL looks like below:
  ```
  diagnosis {
  
	  symptom { 
		  found "There was $d failure:" thenFound "junit.framework.AssertionFailedError" 
	  } -> {
		  diagnosis VALID
		  suggestion nothing
	  }
  	
	  symptom { 
		  found "There was $d failure:" thenCannotFound "junit.framework.AssertionFailedError"  
	  } -> {
		  diagnosis "Test fails other than assertion error"
		  suggestion "restart test"
	  }
  	
	  symptom {
		  found "INSTRUMENTATION_RESULT: shortMsg=Process crashed"
	  } -> {
		  diagnosis "Instrumented test app crashed for some reason."
		  suggestion "Improve the instrumented test"
	  }
  
  }
  ```
* The main user story would be to analyze Stack success or failing behavior


#! What if conflict happens between signatures? Treat symptom as tree note, which diagnosis tree has greater depth gets the game
#? How to treat the thenFound and thenCannotFound? Are they same level tree note?
#? Some vision: 
	#? Can we learn from success log and failing log to auto generate recipes?
