/* finalScreen.scala
 * The end screen for the game. Prints the score
 * called from creativePatternGUI.scala
 */

package main
import java.awt._

import javax.swing._

object finalScreen extends App {
  def popup(userScore: Int, compScore: Int, difficulty: Int): Unit={
    val f = new JFrame("The End")
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

    if(userScore > compScore) {
      instruct.setText("User has won the game! Congratulations! User Score: " + userScore + " Comp Score: " + compScore + "\n\n")
    } else if (compScore > userScore) {
      instruct.setText("Computer has won the game! User Score: " + userScore + " Comp Score: " + compScore + "\n\n")
    } else instruct.setText("User Score: " + userScore + " Comp Score: " + compScore + "\n\n")

    pan.add(instruct, BorderLayout.CENTER)
    f.add(pan)
    f.setSize(600,250)
    f.setVisible(true)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setLocation(550, 300)
    f.setResizable(false)
  }
}