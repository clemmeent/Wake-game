import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2019)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;
    private JPanel aPanelEast;
    private JPanel aPanelSouth;
    private JPanel aPanelNorth;
    private JPanel aPanelWest;
    private JPanel aButton;
    private JButton aButtonNorth;
    private JButton aButtonSouth;
    private JButton aButtonEast;
    private JButton aButtonWest;
    private JButton aButtonHelp;
    private JButton aButtonLook;
    private JButton aButtonBack;
    private JButton aButtonEat;
    
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource("Images/"+ vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "The Quest" ); // change the title
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        JPanel vPanel2 = new JPanel();
        vPanel2.setLayout(new GridLayout(2,1));
        JPanel vPanel3 = new JPanel();
        vPanel3.setLayout(new GridLayout(1,1));
       
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        this.aMyFrame.getContentPane().add(vPanel2, BorderLayout.EAST);

        this.aButtonNorth = new JButton("north");
        vPanel2.add(this.aButtonNorth);
        this.aButtonSouth = new JButton("south");
        vPanel2.add(this.aButtonSouth);
        this.aButtonEast = new JButton("east");
        vPanel2.add(this.aButtonEast);
        this.aButtonWest = new JButton("west");
        vPanel2.add(this.aButtonWest);
        this.aButtonBack= new JButton("back");
        vPanel2.add(this.aButtonBack);

       
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );


       
       

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        aButtonNorth.addActionListener(this);
        aButtonSouth.addActionListener(this);
        aButtonEast.addActionListener(this);
        aButtonWest.addActionListener(this);
        aButtonBack.addActionListener(this);
        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        this.processCommand(); // never suppress this line
    if(pE.getSource() == this.aButtonNorth){ 
        this.aEngine.interpretCommand("go North");
    }
    if(pE.getSource() == this.aButtonSouth){ 
        this.aEngine.interpretCommand("go South");}
    if(pE.getSource() == this.aButtonEast){ this.aEngine.interpretCommand("go East");}
    if(pE.getSource() == this.aButtonWest){ this.aEngine.interpretCommand("go West");}
    if(pE.getSource() == this.aButtonEat){ this.aEngine.interpretCommand("eat");}
    if(pE.getSource() == this.aButtonLook){ this.aEngine.interpretCommand("look");}
    if(pE.getSource() == this.aButtonBack){ this.aEngine.interpretCommand("back");}
    if(pE.getSource() == this.aButtonHelp){ this.aEngine.interpretCommand("help");}
    if(pE.getSource() == this.aEntryField) {processCommand();}
    }// actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 