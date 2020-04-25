/* creativeTypingGUI.scala
 * GUI for the Typing Game Challenge
 * Future delay based off difficulty
 * called from creativeInstruct1.scala, calls creativeInstruct2.scala
 */

package main

import java.awt._
import java.awt.event.ActionEvent
import javax.swing._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object typingGUI extends App {
  def typing(difficulty: Int): Unit ={
    var delay = 500
    var bonus = 10
    if(difficulty == 0) {
      delay = 1000 //easy
    }
    if(difficulty == 1) {
      delay = 500 //medium
    }
    if(difficulty == 2) {
      delay = 300 //hard
    }

    val totalPanel = new JPanel()
    totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS))

    //Scoreboard and Frame
    val f= new JFrame("Typing Challenge")
    val userIden = new JLabel("User Score:")
    val userScore = new JLabel()
    val compIden = new JLabel("Comp Score:")
    val compScore = new JLabel()

    userScore.setText("0")
    compScore.setText("0")

    val topPanel = new JPanel()
    topPanel.setLayout(new FlowLayout())
    topPanel.setBackground(Color.GRAY)
    topPanel.add(userIden)
    topPanel.add(userScore)
    topPanel.add(compIden)
    topPanel.add(compScore)

    val HC = new Color(169, 82, 201)
    val crimson = new Color(172, 28, 0)

    val middlePanel = new JPanel()
    middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS))
    val userSide = new JPanel()
    userSide.setLayout(new BoxLayout(userSide, BoxLayout.Y_AXIS))
    userSide.setBackground(HC)
    userSide.setBorder(BorderFactory.createLineBorder(Color.BLACK))
    userSide.setMinimumSize(new Dimension(500,500))
    val compSide = new JPanel()
    compSide.setLayout(new BoxLayout(compSide, BoxLayout.Y_AXIS))
    compSide.setBackground(HC)
    compSide.setBorder(BorderFactory.createLineBorder(Color.BLACK))
    compSide.setMinimumSize(new Dimension(500,500))

    val bottomPanel = new JPanel()
    bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT))
    bottomPanel.setBackground(HC)

    // TextAreas for the user's and computer's game
    val title1 = new JLabel("User Target:")
    val target = new JTextArea()
    title1.setHorizontalAlignment(SwingConstants.LEFT)
    target.setBackground(Color.LIGHT_GRAY)
    target.setForeground(Color.BLACK)
    target.setEditable(false)
    target.setPreferredSize(new Dimension(50, 50))
    val scrollPane1 = new JScrollPane(target)
    val title2 = new JLabel("User Completed:")
    title2.setHorizontalAlignment(SwingConstants.LEFT)
    val progress = new JTextArea()
    progress.setBackground(Color.LIGHT_GRAY)
    progress.setEditable(false)
    progress.setForeground(crimson)
    progress.setPreferredSize(new Dimension(50, 50))
    val scrollPane2 = new JScrollPane(progress)
    val title5 = new JLabel("User Partial Sentence:", SwingConstants.CENTER)
    val partial = new JTextArea()
    title5.setLayout(new FlowLayout(FlowLayout.CENTER))
    partial.setBackground(Color.LIGHT_GRAY)
    partial.setEditable(false)
    partial.setForeground(crimson)
    partial.setPreferredSize(new Dimension(50, 50))
    val scrollPane3 = new JScrollPane(partial)
    val submit = new JTextField(15)
    val enter = new JButton("Enter")
    userSide.add(title1)
    userSide.add(target)
    userSide.add(title5)
    userSide.add(partial)
    userSide.add(title2)
    userSide.add(progress)
    target.add(scrollPane1)
    progress.add(scrollPane2)
    partial.add(scrollPane3)

    val title3 = new JLabel("Comp Target:") //You cannot reuse JLabels in two panels. I tried.
    val compTarget = new JTextArea()
    compTarget.setBackground(Color.LIGHT_GRAY)
    compTarget.setForeground(Color.BLACK)
    compTarget.setEditable(false)
    compTarget.setPreferredSize(new Dimension(50, 50))
    val scrollPane4 = new JScrollPane(compTarget)
    val title4 = new JLabel("Comp Completed:")
    val compProgress = new JTextArea()
    compProgress.setBackground(Color.LIGHT_GRAY)
    compProgress.setEditable(false)
    compProgress.setForeground(crimson)
    compProgress.setPreferredSize(new Dimension(50, 50))
    val scrollPane5 = new JScrollPane(compProgress)
    val title6 = new JLabel("Comp Partial Sentence:")
    val compPartial = new JTextArea()
    compPartial.setBackground(Color.LIGHT_GRAY)
    compPartial.setEditable(false)
    compPartial.setForeground(crimson)
    compPartial.setPreferredSize(new Dimension(50, 50))
    val scrollPane6 = new JScrollPane(compPartial)
    compSide.add(title3)
    compSide.add(compTarget)
    compSide.add(title6)
    compSide.add(compPartial)
    compSide.add(title4)
    compSide.add(compProgress)
    middlePanel.add(userSide)
    middlePanel.add(compSide)
    compTarget.add(scrollPane4)
    compProgress.add(scrollPane5)
    compPartial.add(scrollPane6)

    val subP = new JPanel()
    subP.setLayout(new BoxLayout(subP, BoxLayout.Y_AXIS))
    subP.setBackground(HC)
    subP.add(submit)
    subP.add(enter)
    bottomPanel.add(subP)

    totalPanel.add(topPanel)
    totalPanel.add(middlePanel)
    totalPanel.add(bottomPanel)
    f.add(totalPanel)
    f.setSize(700,700)
    f.setVisible(true)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setLocation(400, 150)

    //Automatically place cursor in the entry box. Hitting "ENTER" on keyboard submits text
    f.getRootPane().setDefaultButton(enter)
    submit.requestFocus()


    //START GAME HERE:
    var userSentences = 0 //how many sentences user has completed
    var compSentences = 0 //how many sentences user has completed
    val sentenceCap = 10 //sentence completion threshold
    var gameOver = false

    typingGame.newSentence()   // get new sentence at random for user
    target.setText(typingGame.wordBank)  // scrambles sentence and displays in word bank
    enter.addActionListener((e: ActionEvent) =>{
      if(e.getSource==enter) {
        partial.append(typingGame.checkGuess(submit.getText))   // checks if the guess is correct, adds word to partially completed if true
        userScore.setText(typingGame.userPoints.toString())
        submit.requestFocus()
        submit.setText("")

        target.setText(typingGame.wordBank)

        if(typingGame.sentence.size == 0) { //When user has completed a sentence
          userSentences += 1
          title2.setText("User Completed:" + userSentences.toString())
          progress.append(typingGame.sentenceString + "\n")
          partial.setText("")

          if(userSentences == sentenceCap) { //user wins
            typingGame.userPoints += bonus
            userScore.setText(typingGame.userPoints.toString())
            submit.setEditable(false)
            f.requestFocus()
            gameOver = true
            enter.setEnabled(false)
            instruct2.popup(typingGame.userPoints, typingGame.compPoints, difficulty) // Go to creativeInstruct2.scala
            f.setVisible(false)
          } else {
            typingGame.newSentence()
            target.setText(typingGame.wordBank) //print updated word bank
          }
        }
      }
    })

    // The computer's side of the game
    val computerGame = Future {
      typingGame.newSentenceComp() // get new sentence at random for computer
      compTarget.setText(typingGame.wordBankComp)
      while(compSentences < sentenceCap && !gameOver) { //Keep running computerGame until game is over
        val guess = typingGame.wordListComp(Random.nextInt(typingGame.wordListComp.length)) //Computer picks a random word from word bank as guess
        compPartial.append(typingGame.checkGuessComp(guess))
        compScore.setText(typingGame.compPoints.toString())

        compTarget.setText(typingGame.wordBankComp)
        if(typingGame.sentenceComp.size == 0) { //when computer completes a sentence
          compSentences += 1
          title4.setText("Comp Completed:" + compSentences.toString())
          compProgress.append(typingGame.sentenceStringComp + "\n")
          compPartial.setText("")
          if(compSentences == sentenceCap) {
            typingGame.compPoints += bonus
            compScore.setText(typingGame.compPoints.toString())
            submit.setText("")
            submit.setEditable(false)
            f.requestFocus()
            instruct2.popup(typingGame.userPoints, typingGame.compPoints, difficulty) // Go to creativeInstruct2.scala
            f.setVisible(false)
          } else {
            typingGame.newSentenceComp()
            compTarget.setText(typingGame.wordBankComp) //print updated word bank
          }
        }
        Thread.sleep(delay) // delay based on difficulty
      }
    }
  }
}
