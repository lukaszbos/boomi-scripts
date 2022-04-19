

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


//Date with timezone

use(groovy.time.TimeCategory) {
    

    String dateOfBirth_input = "2022-04-01T10:10:10+01:00"

    //Check if older than 14 years
    
    if(dateOfBirth_input==null || dateOfBirth_input==''){
        //WRONG DATE
        isAgeAtLeastFourteen=false
        println(isAgeAtLeastFourteen)
    
    }else{
        currentDate = new Date()
        dateOfBirth = new Date().parse("yyyy-MM-dd'T'HH:mm:ssX", dateOfBirth_input)

        println(currentDate)
        println(dateOfBirth)
    	if (dateOfBirth <= (currentDate - 14.years)){
    		isAgeAtLeastFourteen=true
    		println(isAgeAtLeastFourteen)
    		
    	}else{
    		isAgeAtLeastFourteen=false
    		println(isAgeAtLeastFourteen)
    	}
    }
    
}
