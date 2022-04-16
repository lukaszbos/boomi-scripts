

use(groovy.time.TimeCategory) {
    
    String dateOfBirth_input = "2000-04-01T00:00:00Z"

    //Check if older than 14 years
    
    if(dateOfBirth_input==null || dateOfBirth_input==''){
        //WRONG DATE
        isAgeAtLeastFourteen=false
        println(isAgeAtLeastFourteen)
    
    }else{
        currentDate = new Date()
        dateOfBirth = new Date().parse("yyyy-M-mm'T'H:m:s'Z'", dateOfBirth_input)

        
    	if (dateOfBirth <= (currentDate - 14.years)){
    		isAgeAtLeastFourteen=true
    		println(isAgeAtLeastFourteen)
    	}else{
    		isAgeAtLeastFourteen=false
    		println(isAgeAtLeastFourteen)
    	}
    }
    
}
