package com.example.rspbackend.controller;

import java.io.IOException;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rspbackend.model.AuthToken;
import com.example.rspbackend.model.Hash;
import com.example.rspbackend.model.RSPModel;
import com.example.rspbackend.model.Result;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class RSPController {
    
    Random randomIndex=new Random(); 
    RSPModel play; 
    
    /**
     * @param response
     * @throws IOException
     * The function is forwarding any traffic to the backend's homepage 
     * to the front end immediately. 
     */
    @GetMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:4200");
    }

    /**
     * @param userChoice
     * @return a Result object containing a message for the front end
     * The method is considered to be used when the user wants to play 
     * against the computer manually. 
     */
    @CrossOrigin (origins="http://localhost:4200")
    @PostMapping("/play")
    public Result play(@RequestBody String userChoice) {
        userChoice=userChoice.substring(userChoice.indexOf(":")+2, userChoice.length()-2);
        userChoice=userChoice.toUpperCase(); 
        int userMove; 
        int computerMove; 
        String result; 
        play=new RSPModel(1);
 
		String[]options={"ROCK","SCISSORS","PAPER"}; 
        userMove= play.indexLocator(options, userChoice);
		computerMove= this.randomIndex.nextInt(options.length);    
		result = play.compareRSP(options[userMove], options[computerMove]);
   
        return new Result(result);

    }
    /**
     * @param noRot
     * @return Object Result containing a message to the frontend. 
     * This scenario is considered for the times when the competion between the player
     * and the computer is done based on a number of Rotations (noRot) the winner is 
     * then decided which one of them has picked up the same tool more times. 
     * the default number of rotations is set to 3 Rotations. 
     */
    @CrossOrigin (origins="http://localhost:4200")
    @PostMapping("/autoplay")
    public Result autoPlay(@RequestBody String noRot) {
        RSPModel play; 
        noRot=noRot.substring(noRot.indexOf(":")+2, noRot.length()-2);
        
        if (noRot!=null && noRot.matches("[0-9.]+")){
            play=new RSPModel(Integer.parseInt(noRot));
        }else {
            play=new RSPModel();
        }
        int userMove;
		int computerMove;
		String [] result=new String[play.getRotations()]; 
		String[]options={"ROCK","SCISSORS","PAPER"}; 
    
		for (int i=0; i<=(play.getRotations()-1); i++){
			userMove= this.randomIndex.nextInt(options.length);
			computerMove= this.randomIndex.nextInt(options.length);
			result[i] = play.compareRSP(options[userMove], options[computerMove]);
		} 
		String gameInfo=play.countWins(result, (play.getRotations()/2) ); 
        return new Result(play.showResult(gameInfo));

    }

    /**
     * @param cleartext
     * @return Object Result containing the message failed or success
     * In order to use the app a token has to be provided as a log-in semulation 
     * The verification of the token takes place within this function. 
     */
    @CrossOrigin (origins="http://localhost:4200")
    @PostMapping("/verify")
    public Result verifyPhrase(@RequestBody String cleartext) {
        String resultMessage="";
        String text=cleartext.substring(cleartext.indexOf(":")+2, cleartext.length()-2);
        String hashedText=new Hash().sha1(text);
        String appToken=new AuthToken().getAuthToken();
        
        if(hashedText.equals(appToken)){
            resultMessage= "success";
        }else{
            resultMessage= "failed";
        }
        return new Result(resultMessage); 
    }

    public Random getRandomIndex() {
        return randomIndex;
    }

    public void setRandomIndex(Random randomIndex) {
        this.randomIndex = randomIndex;
    }

    public RSPModel getPlay() {
        return play;
    }

    public void setPlay(RSPModel play) {
        this.play = play;
    }
}
