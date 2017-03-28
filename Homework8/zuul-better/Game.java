/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Dwarf dwarf;
    //private Ogre ogre;
    //private Nettles nettles;
    private Room outside, theater, pub, lab, office; 
    private Room[] rooms;
    private Item stick, carrot, banana, cookie, dollar, chair, rabbit, juice;
    private String[] items;

        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player(10, 1);
        dwarf = new Dwarf();
        //ogre = new Ogre();
        //nettles = new Nettles();
        createRoomsAndItems();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRoomsAndItems()
    {
        rooms = new Room[5];
        rooms[0] = outside;
        rooms[1] = theater;
        rooms[2] = pub;
        rooms[3] = lab;
        rooms[4] = office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        // create the items
        stick = new Item("A wooden stick", 1);
        carrot = new Item("A half-eaten carrot", 1);
        banana = new Item("A green banana that is probably not yet ripe", 1);
        cookie = new Item("A magical cookie capable of granting superhuman strength", 0);
        dollar = new Item("A piece of paper with Washington's face on it", 1);
        chair = new Item("Spinny chair on rollers", 5);
        rabbit = new Item("A white rabbit that is commonly seen living in magicians' hats", 3);
        juice = new Item("A juice box of unknown flavor", 2);
        
        // put items in rooms
        items = new String[8];
        items[0] = "stick";
        items[1] = "carrot";
        items[2] = "banana";
        items[3] = "cookie";
        items[4] = "dollar";
        items[5] = "chair";
        items[6] = "rabbit";
        items[7] = "juice";
        outside.addItem(items[0], stick);
        theater.addItem(items[1], carrot);
        theater.addItem(items[2], banana);
        pub.addItem(items[3], cookie);
        pub.addItem(items[4], dollar);
        lab.addItem(items[5], chair);
        lab.addItem(items[6], rabbit);
        office.addItem(items[7], juice);
        
        player.setNewRoom(outside);
        dwarf.setNewRoom(pub);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getCurrentRoom().printRoomItems());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        
        else if (commandWord.equals("inventory")){
            showPlayerInventory();
        }
        
        else if (commandWord.equals("eat")){
            eat(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();


        // Try to leave current room.
        Room nextRoom =  player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setNewRoom(nextRoom);
            if(player.getCurrentRoom() == dwarf.getCurrentRoom()){
                System.out.println("The dwarf is in this room!");
                dwarf.stealItem(player, items);
            }
            System.out.println(nextRoom.getLongDescription());
            System.out.println(nextRoom.printRoomItems());
        }
    }
    
    /**
     * Player wants to take an item from a room. Check whether room has the specified item
     * If it does, then remove it from the rooom and add it to the player's inventory.
     */
    private void takeItem(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Take what item?");
            return;
        }
        
        String item = command.getSecondWord();
        Room currentRoom = player.getCurrentRoom();
        
        if(currentRoom.hasItem(item)){
            if(currentRoom.getItem(item).getWeight() + player.getCurrentWeight() > player.getMaxWeight()){
                System.out.println("You have too much stuff. Consider dropping something");
            }
            else{
                player.take(item, currentRoom.getItem(item));
                currentRoom.removeItem(item);
                System.out.println(player.getItem(item).getDescription());
                System.out.println("Your current weight is now " + player.getCurrentWeight() + " out of " + player.getMaxWeight());
            }
        }
        else {
            System.out.println("This rooom doesn't have a " + item);
        }
    }
    
    private void dropItem(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what item?");
            return;
        }
        
        String item = command.getSecondWord();
        Room currentRoom = player.getCurrentRoom();
        
        if(player.hasItem(item)){
            currentRoom.addItem(item, player.getItem(item));
            player.drop(item);
            System.out.println("You dropped the " + item );
            System.out.println("Your current weight is now " + player.getCurrentWeight() + " out of " + player.getMaxWeight());
        }
        else {
            System.out.println("You don't have a " + item);
        }
    }
    
    private void showPlayerInventory(){
        System.out.println(player.printCharacterItems());
    }
    
    private void eat(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Eat what?");
            return;
        }
        
        String item = command.getSecondWord();
        Room currRoom = player.getCurrentRoom();
        
        if(player.hasItem(item) || currRoom.hasItem(item)){
            if(item.equals("cookie")){
                currRoom.removeItem("cookie");
                player.eatCookie();
                System.out.println("You ate the cookie. Your strength has increased and you can now carry " + player.getMaxWeight() + " pounds!");
            }
            else{
                System.out.println("You can't eat the " + item);
            }   
        }
        else {
            System.out.println("Eat what?");
        }
   }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
