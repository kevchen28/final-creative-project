/* creativeStartGUI.scala
 * The initial GUI for our game program
 * Contains 3 difficulties which will modify Computer Future delays
 */

import java.awt.event.ActionEvent
import java.awt.{Color, Dimension, Font}

import javax.swing._
import javax.swing.plaf.basic.DefaultMenuLayout
import main.instruct1

object startGui extends App {
  //This will return to typing challenge's instruction GUI
  //0 for easy, 1 for medium, 2 for hard
  //var difficulty: Int = new Int

  val frame = new JFrame("Computer Battle!")
  frame.setPreferredSize(new Dimension(700, 600))
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  //Making a top and bottom panel to go in total panel ensures that the button appear in the middle of the screen 
  val total_panel = new JPanel()
  total_panel.setLayout(new DefaultMenuLayout(total_panel, BoxLayout.Y_AXIS))
  total_panel.setOpaque(true)
  total_panel.setBackground(Color.ORANGE)

  val top_panel = new JPanel()
  top_panel.setOpaque(false)

  val bottom_panel = new JPanel()
  bottom_panel.setOpaque(true)
  bottom_panel.setBackground(Color.ORANGE)

  // calls instruct1
  val easy = new JButton("Easy")

  easy.addActionListener((e: ActionEvent) =>{
    if(e.getSource==easy){
      instruct1.popup(0)
      frame.setVisible(false)
    }
  })


  val medium = new JButton("Medium")

  medium.addActionListener((e: ActionEvent) =>{
    if(e.getSource==medium){
      instruct1.popup(1)
      frame.setVisible(false)
    }
  })


  val hard = new JButton("Hard")

  hard.addActionListener((e: ActionEvent) =>{
    if(e.getSource==hard){
      instruct1.popup(2)
      frame.setVisible(false)
    }
  })

  //Add together the pieces of the GUI
  bottom_panel.add(easy)
  bottom_panel.add(medium)
  bottom_panel.add(hard)
  total_panel.add(top_panel)
  total_panel.add(bottom_panel)
  frame.add(total_panel)
  frame.getContentPane
  frame.pack()
  frame.setLocation(500, 0)
  frame.setVisible(true)
  frame.setResizable(false)
}
