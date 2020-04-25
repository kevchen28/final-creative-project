/* creativeInstruct2.scala
 * Contains a score update and the instructions for the Pattern Matching Game
 * Called from creativeTypingGUI.scala, calls creativePatternGame.scala
 * passes difficulty and current score
 */


package main
import java.awt._
import java.awt.event.ActionEvent

import javax.swing._

object instruct2 extends App {
  def popup(userScore: Int, compScore: Int, difficulty: Int): Unit={
    val f = new JFrame("Pattern Instructions")
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

    //Score update
    if(userScore > compScore) {
      instruct.setText("User has won Round 1! User Score: " + userScore + " Comp Score: " + compScore + "\n\n")
    } else if (compScore > userScore) {
      instruct.setText("Computer has won Round 1! User Score: " + userScore + " Comp Score: " + compScore + "\n\n")
    } else instruct.setText("User Score: " + userScore + " Comp Score: " + compScore + "\n\n")


    instruct.append(
      """The second mini game is a pattern matching challenge.
        |You will see 4 different options on your screen
        |Pick the category of the option that doesn't belong to the group!
        |Click the category which you think is the WRONG category
        |The first player to correctly identify fifteen patterns wins!""".stripMargin)

    val b = new JButton("Start")
    b.addActionListener((e: ActionEvent) =>{
      if(e.getSource==b){
        patternGUI.pattern(userScore, compScore, difficulty) //call creativePatternGUI.scala
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