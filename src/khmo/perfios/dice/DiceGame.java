package khmo.perfios.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DiceGame {

	/**
	 * @param args
	 */
	static String diceString = "4D6 + 3d4 - 3D6";
	
	/*List<Integer> nums = new ArrayList<Integer>();*/
	static int totalDiceRoleValue = 0;
	static int wordStart = 0;
	static char operator = ' ';
	static Map<Integer, Integer> supportedSides = new HashMap<Integer, Integer>();
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// putting supported sides values in hashmap so that we can find valid side number in O(1) complexity
		supportedSides.put(4, 4);
		supportedSides.put(6, 6);
		supportedSides.put(8, 8);
		supportedSides.put(10, 10);
		supportedSides.put(12, 12);
		supportedSides.put(20, 20);
				
		while(true){
				//Clering the variables
				totalDiceRoleValue = 0;
				wordStart = 0;
				operator = ' ';
				
				// Getting out from the user
				System.out.print("diceRoller ");
				diceString = in.nextLine();
				
				//It will start the game and as per user response proceed to roll more 
				if(!playDiceAndRoll(diceString)){
					System.out.println("Thank You. ");
					break;
				}
		      				
		}
	}

	
	public static boolean playDiceAndRoll(String diceString){

		int value = 1;
		for(int i = 0; i< diceString.length(); i++){
				
				if(diceString.charAt(i) == '+' || diceString.charAt(i) == '-') {
					value = getDiceValue(diceString.substring(wordStart, i), operator);
					operator = diceString.charAt(i);
					wordStart = i+1;
				}
				else if(i == diceString.length() - 1){
				value = getDiceValue(diceString.substring(wordStart), operator);
					
				}
				
				//if dice roll value is 0 
				if(value == 0){
					break;
				}
		}
		//if dice roll value is not zero then show the total roll value
		if(value != 0){
			System.out.println("\nTotal Dice Roll Value : "+ totalDiceRoleValue);
		}
		
		  System.out.println("########################################");
	      System.out.print("\nDo you Want to roll more? Y/N ");
	      String flag = in.nextLine();
	      if(flag.trim().equalsIgnoreCase("y")){
	    	  return true;
	      }
		
		return false;
	}
	
	
	private static int getDiceValue(String dices, char operatoe) {
		
		System.out.println("processing " + operatoe + dices.trim());
		int value = diceValueSemanticCheck(dices.trim());
		
		if(value != 0){
			if(operatoe == '+' || operatoe == ' '){
				totalDiceRoleValue += value;
			}else{
				totalDiceRoleValue -= value;
			}	
		}
		return value;
		
	}
	
	
	private static int diceValueSemanticCheck(String diceValue) {
		// TODO Auto-generated method stub
		
		if(diceValue.contains(" ") ){
			System.out.println("\nSorry ! Invalid format : " +diceValue);
			return 0;
		}
		
		String[] dicesAndSides = diceValue.split("[dD]");
		if(dicesAndSides.length != 2)
		{
			System.out.println("\nSorry ! Invalid format : " +diceValue);
			return 0;
		}
		
		//Considering that number of dices and slides are already numbers as it is not mentioned in the assignment doc. So not handling Number format problem here
		int noOfDices = Integer.parseInt(dicesAndSides[0]);
		int noOfSides = Integer.parseInt(dicesAndSides[1]); 
	
		if(noOfDices == 0){
			System.out.println("Sorry ! Number of dices can not be 0");
		}
		
		//
		if(supportedSides.containsKey(noOfSides)){
			
			int maxValue = noOfSides * noOfDices;
			
			int dicesRollValue = (int)(noOfDices + (maxValue - noOfDices) * Math.random());
			
			System.out.println("\t"+dicesAndSides[0] +" Dices with "+dicesAndSides[1] +" sides ") ;
			System.out.println("\tDice Roll value : "+dicesRollValue);
			
			return dicesRollValue;	
						
		}else{
			System.out.println("Sorry ! Number of sides not Supported : "+ noOfSides);
			return 0;
		}
		
		
		
	}

	
	
}
