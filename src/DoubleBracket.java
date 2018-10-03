import java.util.ArrayList;
import java.util.Collections;

public class DoubleBracket extends Bracket{

 ArrayList<Team> teams;
 int numRounds;
 int numTeams;
 int numMatches;
 int[] numMatchesInRound;
 int oldNumMatchesLosers = 0;
 int addIndex; 
 int initalRounds;
 int numMatchesWinners;
 int numMatchesLosers;
 boolean roundFinal = false;
 boolean finals = false;
 
 //ArrayList<Team>[] round;
 ArrayList<ArrayList<String>> round;
 ArrayList<ArrayList<String>> loserRound;
 String [] finalRound = new String[2]; 

 
 

  
 // DONE
 DoubleBracket(ArrayList<Team> teams) {
  this.teams = teams;
  
 
  numTeams = teams.size();
  numMatchesInRound = new int[numRounds + 1];  
  numMatches = (teams.size() - 1) * 2;
  
  //Calculate number of rounds 
  double L2 = log(teams.size(),2);  
  initalRounds = (int)Math.ceil(L2); 
  int surplusRounds = (int)(Math.ceil(log((int)L2,2)));
  numRounds = initalRounds + surplusRounds + 1 + 1; 
  
  round = new ArrayList<ArrayList<String>>(numRounds);
  loserRound = new ArrayList<ArrayList<String>>(numRounds);
  
  
  
   //Every round is an ArrayList of the teams that play in the round
  for (int i = 0; i < numRounds; i++) {
    ArrayList<String> indArr = new ArrayList<String>();
    round.add(indArr);
    //loserRound.add(indArr);
  }
  
  
  for (int i = 0; i < numRounds; i++) {
    ArrayList<String> indArr = new ArrayList<String>();
    loserRound.add(indArr);
  }
  
  int roundOneSize = nextPowerOftwo(teams.size());
  int index = teams.size()/2; 
  // Add teams to the very first round 
  
  
  for (int i = 0; i < teams.size(); i++) {
    round.get(0).add(i,teams.get(i).getName());
  }
  
  int numByes = nextPowerOftwo(teams.size()) - teams.size();
  
  
  for(int i = 1; i <= numByes;i++) {
    round.get(0).add((i*2)-1,"BYE");
  }
    
  for(int i = 1; i<=numRounds; i++){
    for(int j = 0; j<teams.size()/2; j++){//+1 incase its odd
      loserRound.get(i-1).add("?");      
    }
   }



 }
 
 
 // DONE
 @Override
 int getNumberOfTeams() {
  return numTeams;
 }
 // DONE
 @Override
 int getNumberOfRounds() {
  return numRounds;
 }
 





 @Override
 int getNumberOfMatchesInRound(int roundNum) {
 numMatchesWinners = (int) Math.floor((round.get(roundNum-1).size())/2); 
 numMatchesLosers = (int) Math.floor(numMatchesWinners/2) + (oldNumMatchesLosers/2);
  oldNumMatchesLosers = numMatchesLosers;
  //System.out.println(round.get(0));
  //System.out.println("loser amount " + loserRound.get(1));
  numMatches = numMatchesWinners + numMatchesLosers;
  return numMatches;  
 }

 @Override
 String[][] getTeamsInMatch(int roundNum, int matchNumber) {
	 
	 String[][] teamsInMatch = new String[2][];

	 ArrayList<String> possibleTop = new ArrayList<String>();
	 ArrayList<String> possibleBottom = new ArrayList<String>();

	 
	 if (roundNum > 1) {
		 possibleTop.addAll(findPossTop(roundNum-1, matchNumber*2));
		 possibleBottom.addAll(findPossTop(roundNum-1, (matchNumber*2)-1));
		 
		 for (int i = 0; i < possibleTop.size(); i++) {
			 possibleTop.remove("BYE");
		 }
		 
		 for (int i = 0; i < possibleBottom.size(); i++) {
			 possibleBottom.remove("BYE");
		 }
		 
		 teamsInMatch[1] = new String[possibleTop.size()];
		 teamsInMatch[0] = new String[possibleBottom.size()];
		 
		 
		 for (int i = 0; i < teamsInMatch[1].length; i++) {
			 teamsInMatch[1][i] = possibleTop.get(i);
		 }
		 for (int i = 0; i < teamsInMatch[0].length; i++) {
			 teamsInMatch[0][i] = possibleBottom.get(i);
		 }
		 

//		 for (int i = 0; i < teamsInMatch[1].length; i++) {
//			 if (teamsInMatch[1][i] != "BYE") {
//				 teamsInMatch[1][i] = possibleTop.get(i);
//			 }
//		 }
//		 for (int i = 0; i < teamsInMatch[0].length; i++) {
//			 if (teamsInMatch[0][i] != "BYE") {
//				 teamsInMatch[0][i] = possibleBottom.get(i);
//			 }
//		 }
		 
		 
		 
	 } else {
		 String top = round.get(0).get((matchNumber*2)-2);
		 String bottom =round.get(0).get((matchNumber*2)-1);
		 
			 teamsInMatch[0] = new String[] {top};

		 
			 teamsInMatch[1] = new String[] {bottom};

		 
	 }

	 
	 
//	 teamsInMatch[0] = (String[]) possibleBottom.toArray();
//	 teamsInMatch[1] = (String[]) possibleTop.toArray();

	 
	 //System.out.println(findPossTop(roundNum-1, matchNumber*2).toString());
	 //System.out.println(findPossTop(roundNum-1, (matchNumber*2)-1).toString());


  return teamsInMatch;
 }
 
 ArrayList<String> findPossTop(int roundNum, int matchNumber) {
	 ArrayList<String> possibleTop = new ArrayList<String>();		
	 		
	 	// If it gets to round 0, no teams are playing, so an empty array is returned
	 	if (roundNum == 0) {
	 		return possibleTop;
	 	}
	 	
	 	if (matchNumber > this.getNumberOfMatchesInRound(roundNum)) {
	 		
	 		IndexOutOfBoundsException e = new IndexOutOfBoundsException();
	 		e.printStackTrace();
	 		return null;
	 	}
	 	
	 	// 
		 if ((matchNumber*2)-2 < round.get(roundNum-1).size()) {
			 
			 possibleTop.add(round.get(roundNum-1).get((matchNumber*2)-1));
			 if ((matchNumber*2)-2 < round.get(roundNum-1).size()) {
				 possibleTop.add(round.get(roundNum-1).get((matchNumber*2)-2));
			 }
			 
			 return possibleTop;
			 
		 } else {

			 ArrayList<String> combinedArray = new ArrayList<String>();
			 combinedArray.addAll(findPossTop(roundNum-1, matchNumber*2));
			 combinedArray.addAll(findPossTop(roundNum-1, (matchNumber*2)-1));
	
			 return combinedArray;
		 }
		 


 }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 @Override
 void setMatchWinner(String teamName, int roundNum, int matchNumber) {  
   
   if(!finals){
     int roundSize = round.get(roundNum-1).size();
     int loserRoundSize = loserRound.get(roundNum-1).size();
     
     
  boolean inWinnersBracket;
  if(matchNumber*2<= roundSize){
     inWinnersBracket = true; 
  }else{
    inWinnersBracket = false;
  }
  
  
  //to change the value of roundNum to the index of the round 
  roundNum = roundNum - 1;
   
  //if size of mainRound is 1 delete all nulls in loser 
  
  if(roundSize != 1){
    if(roundSize % 2 != 0){
      round.get(roundNum).add("?");
    } 
  }
 
  
  //dont need later when i set it to automtically move on the team with by
  if(loserRoundSize % 2 != 0){
    loserRound.get(roundNum).add("?");
  }
  
    
  if(inWinnersBracket){
    round.get(roundNum+1).add(matchNumber-1, teamName);
    if(roundNum == 0){
      
      //add losing team to losers bracker
      if(round.get(roundNum).get(matchNumber*2-2).equals(teamName)){
        loserRound.get(roundNum).add(matchNumber-1,(round.get(roundNum).get(matchNumber*2-1)));
      }else if(round.get(roundNum).get(matchNumber*2-1).equals(teamName)){
        loserRound.get(roundNum).add(matchNumber-1, (round.get(roundNum).get(matchNumber*2-2)));
      }  
      
    }else if(roundNum != 0){
      //adding teams into the losers bracket after round 1 
      //index for add into black spaces in between the previous losers 
      
      
      addIndex = (matchNumber * 2) -1;
      
      //add losing team to losers bracker(might need to delete finals things) 
      if(!roundFinal){
        if(round.get(roundNum).get(matchNumber*2-2).equals(teamName)){
          loserRound.get(roundNum).set(addIndex,(round.get(roundNum).get(matchNumber*2-1)));
        }else if(round.get(roundNum).get(matchNumber*2-1).equals(teamName)){
          loserRound.get(roundNum).set(addIndex, (round.get(roundNum).get(matchNumber*2-2)));
        }   
      }else{
        if(round.get(roundNum).get(matchNumber*2-2).equals(teamName)){
          loserRound.get(roundNum).add(round.get(roundNum).get(matchNumber*2-1));
        }else if(round.get(roundNum).get(matchNumber*2-1).equals(teamName)){
          loserRound.get(roundNum).add((round.get(roundNum).get(matchNumber*2-2)));
        } 
      }
    }
   
  }else if(!inWinnersBracket){   
    //add leaving one space in between   
    if(roundFinal==true){
      loserRound.get(roundNum + 1).add(teamName);
    }else{
        addIndex = ((matchNumber * 2) - roundSize) -2; 
        loserRound.get(roundNum + 1).set(addIndex,teamName);
    } 
  }
  
  

  
  
  /*
  if(roundNum+2 == getNumberOfRounds()-2){
    round.get(roundNum+1).add(loserRound.get(roundNum-1).get(0));
  }
  */
  
  
  
  if(roundFinal){
    if(round.get(roundNum+1).size() == 0){
      round.get(roundNum+1).add(round.get(roundNum-1).get(0));
    }
  }
  

  if(roundSize == 2){
    roundFinal = true;
  }
  
  
  
  if(roundFinal){
    for(int i = initalRounds; i<numRounds; i++){
    loserRound.get(i).removeAll(Collections.singleton("?"));
    }
  }
  
  //doingfinals 
 }
   
   if(finals && roundNum - 1  != numRounds -1){
     if(teamName.equals(finalRound[0])){
       round.get(numRounds-2).add(teamName);
     }else{
       System.out.println("added");
       loserRound.get(numRounds-2).add(finalRound[0]);
     }
   }
   
  if(round.get(initalRounds).size() == 1 && loserRound.get(numRounds - 3).size() == 1){
    finals = true;
    finalRound[0] = (round.get(numRounds - 4).get(0));
    finalRound[1] = (loserRound.get(numRounds - 3).get(0));
  }
  

   

  if(roundNum - 1  == numRounds -1){
    
    round.get(numRounds-1).add(teamName);
  }
 
   
  

 }
 
 @Override
 int getMatchBracket(int roundNum, int matchNumber) {
   
   if(matchNumber*2 <= round.get(roundNum-1).size()){
     return 0;
     
   }else if(matchNumber*2 <= (round.get(roundNum-1).size() + loserRound.get(roundNum-1).size())){
     return 1;
   }
  
 
 // if losers bracket then 1
  // if winners bracket then 0
  
   return -1; 
 }

 static double log(int x, int base) {
     return (double) (Math.log(x) / Math.log(base));
 }
 
 static int nextPowerOftwo(int n){
   int p = 1; 
   if(n > 0 && (n & (n-1))==0){
     return n;
   } 
   while(p<n) {
     p<<=1; 
   }
   return p;
   
 }
 
 
 

}
