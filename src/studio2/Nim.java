
package studio2;

/**
 * Control the game and the create the Pile.
 * It is only dependent on Pile and Player interface.
 * @author Richard'PC
 */
public class Nim {
    
    //Variables
    private Player[] players;
    private String currentTurn;
    
    /**
     * Assigns the setting.
     * @param players List Players
     * @param firstPlayer First Turn
     */
    public Nim(Player[] players, String firstPlayer){
        this.players = players;
        this.currentTurn = firstPlayer;
        
    }
    
    /**
     * Will create a Pile and allow Players to take turns taking marbles out of 
     * it. 
     * @return The winners Name 
     */
    public String play(){
        
        
        Player playing = lookForPlayer(currentTurn); //getting name of first player
        Pile newPile = new Pile(); //create new pile
        //Method to display a header with information about the game 
        displayGameInfo(playing, newPile.getAmount());
        boolean cancelGame = false; // to cancel game if true
        //Loop will stop if Pile gets to 1.
        //Meaning that player that goes next will loose
        while(newPile.getAmount()>1){
            //Polymorphically getting the amount been removed by the Players
            int amountTakenByPlayer = playing.move(newPile.getAmount());
            //Handeling a cancel this will exit the game
            if(amountTakenByPlayer == -1){
                cancelGame = true;
                break;
            }
            //Taking the amount from Pile
            newPile.take(amountTakenByPlayer);
            //Logging the turn, amount removed, and remainder.
            System.out.println("Turn: \t" + playing.getName() + "\tRemoved: \t"+ amountTakenByPlayer+ "\tPile: \t" + newPile.getAmount());
            //Looping the players turns
            playing = nextTurn();
        }
        if(cancelGame){
            return null;
        }
        //Since last player Lost, the next turn Player won
        playing = nextTurn();
        return playing.getName();
    }
    
    /**
     * Given a String Name it will return the Player. 
     * @param name Name of Player
     * @return Player with given Name
     */
    private Player lookForPlayer(String name){
        //Loop thorugh Players
        for(int index =0; index < players.length; index++){
            //Getting name and comparing it to Name been looked for
            if(players[index].getName().equals(name)){
                return players[index];
            }
            
        }
        return null;
    }
    
    /**
     * Returns the next player 
     * @return Next Player
     */
    
      private Player nextTurn(){
          // simple conditional to change the turn  between both players
        if(players[0].getName() == currentTurn){
            currentTurn = players[1].getName();
            return players[1];
        }else{
            currentTurn = players[0].getName();
            return players[0]; 
        }
    }
      
      /**
       * Logs a header for the game with the information of the Player, pile size
       * and who goes first.
       * @param first Player that goes first
       * @param pileSize Size of pile when created
       */
      private void displayGameInfo(Player first, int pileSize){
          System.out.println("*****************************************");
          System.out.println("\t\tNim Game\n");
          for(int index = 0; index < players.length; index++){
              System.out.println("Players Name: \t" +players[index].getName());
          }
          System.out.println("First Turn: "+ first.getName());
          System.out.println("Pile Size: "+ pileSize);
          System.out.println("*****************************************");   
      }  
}