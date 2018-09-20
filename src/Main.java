import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		
		ArrayList<Character> teams = new ArrayList<Character>();

		teams.add('d');
		teams.add('b');
		teams.add('a');
		teams.add('c');
		teams.add('e');

		
//		for (int i = 0; i < teams.size(); i++) {
//			System.out.print(teams.get(i) + "\t");
//		}
		
		//System.out.println();
		
	
		
		
		printRound(teams);


	
		
		
	}
	
	public static void printRound(ArrayList<Character> teams) {
		
		if (teams.size() == 1) {
			System.out.println(teams.get(0));
			
		} else {
			for (int i = 0; i < teams.size(); i++) {
				System.out.print(teams.get(i) + "\t");
			}
			System.out.println();
			getRemainingTeams(teams);
		}
	}
	
	public static void getRemainingTeams(ArrayList<Character> teams) {
		ArrayList<Character> teamsLeft= new ArrayList<Character>();
		for (int i = 0; i < Math.floor(teams.size()/2); i++) {
			char winner = fight(teams.get(i*2), teams.get((i*2)+1));
			teamsLeft.add(winner);
		}
		printRound(teamsLeft);
	}
	
	public static char fight(char team1, char team2) {
		if (team1 < team2) {
			return team1;
		} else {
			return team2;
		}
		
	
	}

	
}


