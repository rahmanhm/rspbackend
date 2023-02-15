package com.example.rspbackend.model;

public class RSPModel {
    private int rotations; 

    public RSPModel() {
        rotations = 3;
    }
    
    public RSPModel(int number ){
		if ( (number % 2) == 0 ){
			rotations = number+1; 
		} else {
			rotations = number; 
		}
	}
    public int getRotations(){
        return rotations;
    }

    public void setRotations(int numberOfRotations){
        if ( (numberOfRotations % 2) == 0 ) {
			rotations=numberOfRotations+1; 
		} else {
			rotations=numberOfRotations; 
		} 
    }

    public String compareRSP(String options, String options2){	
		if ( options=="ROCK" && options2=="SCISSORS" ) //rock beats scissors 
			return "User"; //user wins 
		else if ( options=="SCISSORS" && options2=="PAPER" ) //scissors cuts paper
			return "User";
		else if ( options=="PAPER" && options2=="ROCK" ) //paper wraps rock 
			return "User";
		else if ( options=="PAPER" && options2=="SCISSORS" ) 
			return "Computer"; // Computer wins
		else if ( options=="SCISSORS" && options2=="ROCK" )
			return "Computer";
		else if ( options=="ROCK" && options2=="PAPER" )
			return "Computer";
		else return "Tie"; 	
	}

    public String countWins(String[]resultSet, int half ) {
		int user=0;
        int computer=0;
        
		for(int i=0; i<=(resultSet.length-1);i++){
			if (resultSet[i].equals("User"))
				user++; 
			else if(resultSet[i].equals("Computer"))
				computer++; 
			else {
				//tie
			} 
		}
    
		if( user >= (half+1) )
			return "WIN";
		else if ( computer >= (half+1) )
			return "LOST";
		else
			return "DRAW";
	}

    public int indexLocator (String []options, String key){
        int i=0; 
        if (options == null){
            return -1;
        }
        while(i<=(options.length-1)){
            if (options[i].equals(key) ){
                return i; 
            }else{
                i++; 
            }
        }
        return -5;
    }
    
    public String showResult(String resultAnnouncement) {
        switch (resultAnnouncement){
			case "WIN": 
                System.out.println("***Human has won the Game***");
				return "***Human has won the Game***";

			case "LOST": 
                System.out.println("***Human has won the Game***");
				return "***Computer has won the Game***";

			case "DRAW":
                System.out.println("***It is a Draw. You may try again! ***");
				return "***It is a Draw. You may try again! ***"; 

			default: 
				return "Some Extraterrestrial Forces have taken action. Closing the App to avoid Resource Consumption";
		}
    }    
}
