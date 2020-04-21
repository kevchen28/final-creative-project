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
    val compSide = new JPanel()
    compSide.setLayout(new BoxLayout(compSide, BoxLayout.Y_AXIS))
    compSide.setBackground(HC)
    compSide.setBorder(BorderFactory.createLineBorder(Color.BLACK))

    val bottomPanel = new JPanel()
    bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT))
    bottomPanel.setBackground(HC)

    val title1 = new JLabel("User Target:")
    val target = new JTextArea()
    target.setBackground(Color.LIGHT_GRAY)
    target.setForeground(Color.BLACK)
    target.setEditable(false)
    val scrollPane1 = new JScrollPane(target)
    val title2 = new JLabel("User Completed:")
    val progress = new JTextArea()
    progress.setBackground(Color.LIGHT_GRAY)
    progress.setEditable(false)
    progress.setForeground(crimson)
    val scrollPane2 = new JScrollPane(progress)
    val title5 = new JLabel("User Partial Sentence:")
    val partial = new JTextArea()
    partial.setBackground(Color.LIGHT_GRAY)
    partial.setEditable(false)
    partial.setForeground(crimson)
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
    val scrollPane4 = new JScrollPane(compTarget)
    val title4 = new JLabel("Comp Completed:")
    val compProgress = new JTextArea()
    compProgress.setBackground(Color.LIGHT_GRAY)
    compProgress.setEditable(false)
    compProgress.setForeground(crimson)
    val scrollPane5 = new JScrollPane(compProgress)
    val title6 = new JLabel("Comp Partial Sentence:")
    val compPartial = new JTextArea()
    compPartial.setBackground(Color.LIGHT_GRAY)
    compPartial.setEditable(false)
    compPartial.setForeground(crimson)
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

    f.getRootPane().setDefaultButton(enter)
    submit.requestFocus()
    //START GAME HERE:
    var userSentences = 0
    var compSentences = 0
    val sentenceCap = 2
    var gameOver = false
    typingGame.newSentence()
    target.setText(typingGame.wordBank)
    enter.addActionListener((e: ActionEvent) =>{
      if(e.getSource==enter) {
        partial.append(typingGame.checkGuess(submit.getText))
        userScore.setText(typingGame.userPoints.toString())
        submit.requestFocus()
        submit.setText("")

        target.setText(typingGame.wordBank)

        if(typingGame.sentence.size == 0) {
          userSentences += 1
          title2.setText("User Completed:" + userSentences.toString())
          progress.append(typingGame.sentenceString + "\n")
          partial.setText("")
          if(userSentences == sentenceCap) {
            typingGame.userPoints += bonus
            userScore.setText(typingGame.userPoints.toString())
            submit.setEditable(false)
            f.requestFocus()
            gameOver = true
            enter.setEnabled(false)
            instruct2.popup(typingGame.userPoints, typingGame.compPoints, difficulty)
            f.setVisible(false)
          } else {
            typingGame.newSentence()
            target.setText(typingGame.wordBank)
          }
        }
      }
    })

    val computerGame = Future {
      typingGame.newSentenceComp()
      compTarget.setText(typingGame.wordBankComp)
      while(compSentences < sentenceCap && !gameOver) {
        val guess = typingGame.wordListComp(Random.nextInt(typingGame.wordListComp.length))
        compPartial.append(typingGame.checkGuessComp(guess))
        compScore.setText(typingGame.compPoints.toString())

        compTarget.setText(typingGame.wordBankComp)
        if(typingGame.sentenceComp.size == 0) {
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
            instruct2.popup(typingGame.userPoints, typingGame.compPoints, difficulty)
            f.setVisible(false)
          } else {
            typingGame.newSentenceComp()
            compTarget.setText(typingGame.wordBankComp)
          }
        }
        Thread.sleep(delay)
      }
    }
  }
}
