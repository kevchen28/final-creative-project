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
  //This will return to main who calls startGUI
  //0 for easy, 1 for medium, 2 for hard
  //var difficulty: Int = new Int

  val frame = new JFrame("Computer Battle!")
  frame.setPreferredSize(new Dimension(700, 600))
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  val total_panel = new JPanel()
  total_panel.setLayout(new DefaultMenuLayout(total_panel, BoxLayout.Y_AXIS))
  total_panel.setOpaque(true)
  total_panel.setBackground(Color.ORANGE)

  val top_panel = new JPanel()
  top_panel.setOpaque(false)

  val bottom_panel = new JPanel()
  bottom_panel.setOpaque(true)
  bottom_panel.setBackground(Color.ORANGE)


  val titleImage = new JLabel(new ImageIcon("resources/fire2.jpg"))
  val text = new JLabel("Computer Battle!")
  val titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 20)
  text.setFont(titleFont)
  text.setLocation(350, 100)
  titleImage.add(text)

  val bodyFont = new Font(Font.SANS_SERIF, Font.PLAIN, 10)
  val body = new JLabel("Battle against the computer in mini games. Compete to earn the top score!")
  body.setLocation(350, 200)
  body.setFont(bodyFont)
  body.setHorizontalAlignment(SwingConstants.CENTER)
  titleImage.add(body)
  top_panel.add(titleImage)

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