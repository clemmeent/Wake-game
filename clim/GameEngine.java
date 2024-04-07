import java.util.Stack;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
 
public class GameEngine
{
    
    private Parser aParser;
    private UserInterface aGui;
   
   
  
    private Player aPlayer;
    
    public GameEngine(){
        this.aParser=new Parser();
        
        this.aPlayer= new Player ("Oui");
        this.createRooms();
    }
    /**
     * creer les salles
     */
    private void createRooms(){
        Room vViroom=new Room("in victor's room","Viroom.jpg");
        Room vCleroom=new Room("in clément's room","cleroom.jpg");
        Room vFortdeFrance=new Room("at Fort-de-France","FortdeFrance.jpg");
        Room vCocofly=new Room("at Cocofly","cocofly.jpg");
        Room vWakeRoom1=new Room("in first room at wake","wakeroom1.jpg");
        Room vWakeRoom2=new Room("in first room at wake","wakeroom2.jpg");
        Room vMacdo=new Room("at Macdonald's","Macdo.jpg");
        Room vShotel=new Room("at surf hotel","Shotel.jpg");
        Room vSlocal=new Room("at surf local","Slocal.jpg");
        Room vSplage=new Room("at surf beach","Splage.jpg");
        
        vViroom.setExits("South",vShotel);
        vShotel.setExits("North",vViroom);
        vShotel.setExits("West",vSlocal);
        vShotel.setExits("South",vSplage);
        vShotel.setExits("East",vFortdeFrance);
        vSlocal.setExits("West",vShotel);
        vSplage.setExits("North",vShotel);
        vFortdeFrance.setExits("West",vShotel);
        vFortdeFrance.setExits("North",vMacdo);
        vFortdeFrance.setExits("South",vWakeRoom2);
        vFortdeFrance.setExits("East",vCocofly);
        vMacdo.setExits("South",vFortdeFrance);
        vCocofly.setExits("South",vCleroom);
        vCocofly.setExits("West",vFortdeFrance);
        vWakeRoom2.setExits("North",vFortdeFrance);
        vWakeRoom2.setExits("East",vWakeRoom1);
        vWakeRoom1.setExits("West",vWakeRoom2);
        vWakeRoom1.setExits("East",vCleroom);
        vCleroom.setExits("North",vCocofly);
        vCleroom.setExits("West",vWakeRoom1);
        
        this.aPlayer.setLocalisation(vViroom);
    }
    
    
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }
    /**
     * methode pour les changements de Room
     */private void goRoom(final Command pCommand){
        if(!pCommand.hasSecondWord()){
           this.aGui.println("go where ?");
           return;
        }  
         
         
        String vdirection = pCommand.getSecondWord();
        String vdirectionPossible= "Exits : ";
        Room vNextRoom = this.aPlayer.getLocalisation().getExit(vdirection);
        if(vNextRoom==null){
        this.aGui.println ("there is no door!");
        }
        else{
        this.aPlayer.setLastRoom(this.aPlayer.getLocalisation());
        this.aPlayer.setLocalisation(vNextRoom);
        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
        if(this.aPlayer.getLocalisation().getImages() != null)
            this.aGui.showImage(this.aPlayer.getLocalisation().getImages());
        }
        
        printLocationInfo();
        //this.aLastRoom = this.aCurrentRoom;
        //this.aLastRooms.push(this.aCurrentRoom);
        
        return;
    }
    /** 
     * Message d'acceuil
     */
    private void printWelcome(){
        printLocationInfo();
        this.aGui.print("\n");
        this.aGui.println("Welcome to the World of Zuul!");
        this.aGui.println("World of Zuul is a new, incredibly boring adventure game.");
        this.aGui.println("Type 'help' if you need help.");
        this.aGui.print("\n");

        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
        return;
    }
    /**
     * Aide le joueur
     */
    private void printHelp(){
    
        this.aGui.println( "your command are: " + this.aParser.getCommandString() );
    }

    private void printLocationInfo(){
        this.aGui.showImage(this.aPlayer.getLocalisation().getImages());
        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
    }
    /**
     * permet de quitter le jeu
     */
    private boolean quit(final Command pCommand){
        String vCommand=pCommand.getSecondWord();
        boolean vQuit = true;
        if(vCommand!=null){
            vQuit=false;
            this.aGui.println("Quit what ?");
        }
        return vQuit;
    }
    private void look(){
        this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
    }
    private void eat(){
        this.aGui.println("you just ate, you aren't longer hungry");
    }
    /**
     * transforme les commandes en action
     */
    public void interpretCommand(final String pCommandLine){
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "help" ) )
            this.printHelp();
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                this.endGame();
        }
    
        else if (vCommand.equals("look")){
            this.look();
        }
        else if (vCommand.equals("eat")){
            this.eat();
        }
        else if (vCommand.equals("back")){
            this.back();
        }
        else if (vCommand.equals("test")){
            test(vCommand);
        }


    }    
}
    
    
 

    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }
    private void back()
    {
    if(this.aPlayer.lastRoomsIsEmpty() == true) //si la liste est vide(retour a la premier position)
    {
        this.aGui.println("You are all ready in your first localisation.");
    }
    else
    {
        this.aGui.println("your go back in the last room");
        this.aPlayer.setLocalisation(this.aPlayer.getLastRoom()); // la piece courant deviens la piece au sommet de la pile
    }

    this.aGui.println(this.aPlayer.getLocalisation().getLongDescription());
    if(this.aPlayer.getLocalisation().getImages() != null)
    this.aGui.showImage(this.aPlayer.getLocalisation().getImages());
    }
    private void test(final Command pCommand)
    {
    if(!pCommand.hasSecondWord())
    {
        this.aGui.println("test what?");
        return;
    }         

    String vFile = pCommand.getSecondWord();
    Scanner vScan = null;

    try {vScan = new Scanner(new File("./"+vFile+".txt"));}
    catch ( final java.io.FileNotFoundException pException )
    {
        this.aGui.println("File not find");
    }

    while(vScan.hasNextLine())
    {
        String vLigne = vScan.nextLine();
        interpretCommand(vLigne);
    }
    if (vScan != null) {vScan.close();}
    }
}
