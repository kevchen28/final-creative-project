/* creativeInstruct1.scala
 * Contains the instructions for the Typing Challenge Game
 * Called from creativeStartGUI.scala, calls creativeTypingGUI.scala
 */

package main

import java.awt.event.ActionEvent
import java.awt.{Color, BorderLayout, Font}
import javax.swing._

object instruct1 extends App {
  def popup(difficulty: Int): Unit={
    val f = new JFrame("Typing Instructions")
    f.setBackground(Color.white)

    val pan = new JPanel()
    pan.setLayout(new BorderLayout())
    pan.setBackground(Color.white)

    val instruct = new JTextArea()
    val iFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20)
    instruct.setBounds(0, 0, 500, 300)
    instruct.setFont(iFont)
    instruct.setEditable(false)
    instruct.setWrapStyleWord(true)
    instruct.setLineWrap(true)

    instruct.setText(
      """The first mini game is a typing challenge.
        |You will see a scrambled sentence in the 'target' field.
        |Type in each word, one at a time, to gain points.
        |Points will be removed for incorrect entries.
        |The computer will also compete to fill in scrambled sentences.
        |The first player to completely enter ten sentences will receive a
        |bonus of 10 points!""".stripMargin)

    // calls typingGUI
    val b = new JButton("Start")
    b.addActionListener((e: ActionEvent) =>{
      if(e.getSource==b){
        typingGUI.typing(difficulty)
        f.setVisible(false)
      }
    })

    pan.add(instruct, BorderLayout.CENTER)
    pan.add(b, BorderLayout.PAGE_END)
    f.add(pan)
    f.setSize(600,250)
    f.setVisible(true)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setLocation(550, 300)
    f.setResizable(false)
  }
}