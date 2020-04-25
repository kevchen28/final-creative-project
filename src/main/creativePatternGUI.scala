/* creativePatternGUI.scala
 * GUI for the Pattern Matching Challenge
 * Future delay based off difficulty
 * called from creativeInstruct2.scala, calls finalScreen.scala
 */

package main

import java.awt.event.{ActionEvent, ActionListener}
import java.awt._

import javax.swing._
import javax.swing.text.{SimpleAttributeSet, StyleConstants}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object patternGUI extends App {
  def pattern(uScore: Int, cScore: Int, difficulty: Int): Unit ={
    var delay = 500
    var bonus = 10
    if(difficulty == 0) {
      delay = 3000 //easy
    }
    if(difficulty == 1) {
      delay = 2000 //medium
    }
    if(difficulty == 2) {
      delay = 1500 //hard
    }

    val totalPanel = new JPanel()
    totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS))

    val beefy = new Font(Font.SERIF, Font.BOLD, 14)
    val lean = new Font(Font.SANS_SERIF, Font.PLAIN, 18)

    val f= new JFrame("Pattern Challenge")
    val userIden = new JLabel("User Score:")
    userIden.setFont(beefy)
    val userScore = new JLabel()
    userScore.setFont(beefy)
    val compIden = new JLabel("Comp Score:")
    compIden.setFont(beefy)
    val compScore = new JLabel()
    compScore.setFont(beefy)

    var userPoints = uScore
    var compPoints = cScore
    userScore.setText(userPoints.toString()) //this should carry over from the old game
    compScore.setText(compPoints.toString()) //as parameters for the function call

    val topPanel = new JPanel()
    topPanel.setLayout(new FlowLayout())
    topPanel.setBackground(Color.GRAY)
    topPanel.add(userIden)
    topPanel.add(userScore)
    topPanel.add(compIden)
    topPanel.add(compScore)

    val HC = new Color(169, 82, 201)

    val middlePanel = new JPanel()
    middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS))
    val userSide = new JPanel()
    userSide.setLayout(new BoxLayout(userSide, BoxLayout.Y_AXIS))
    userSide.setBackground(HC)
    userSide.setAlignmentX(Component.CENTER_ALIGNMENT)
    userSide.setAlignmentY(Component.CENTER_ALIGNMENT)
    userSide.setBorder(BorderFactory.createLineBorder(Color.BLACK))
    val compSide = new JPanel()
    compSide.setLayout(new BoxLayout(compSide, BoxLayout.Y_AXIS))
    compSide.setBackground(HC)
    compSide.setBorder(BorderFactory.createLineBorder(Color.BLACK))

    val bottomPanel = new JPanel()
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS))
    bottomPanel.setBackground(HC)
    //FlowLayout.LEADING
    //An array to store the panels needed for the user's questions
    var userQ : Array[JTextPane] = new Array[JTextPane] (4)
    val title1 = new JLabel("User Question:")
    title1.setFont(beefy)
    userSide.add(title1)
    for (x <- 0 to 3){
      userQ(x) = new JTextPane()
      val doc = userQ(x).getStyledDocument //All of this
      val center = new SimpleAttributeSet()  //is needed
      StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER) //to center
      doc.setParagraphAttributes(0, doc.getLength, center, false) //the text
      userQ(x).setFont(lean)
      userQ(x).setBackground(Color.LIGHT_GRAY)
      userQ(x).setForeground(Color.BLACK)
      userQ(x).setEditable(false)
      userQ(x).setText("This is a test")
      userQ(x).setPreferredSize(new Dimension(50, 50)) //Needed to stop from resizing
      userSide.add(userQ(x))
    }

    val compQ : Array[JTextPane] = new Array[JTextPane] (4)
    val title2 = new JLabel("Comp Question:")
    title2.setFont(beefy)
    compSide.add(title2)
    for (x <- 0 to 3){
      compQ(x) = new JTextPane()
      val doc = compQ(x).getStyledDocument //All of this
      val center = new SimpleAttributeSet()  //is needed
      StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER) //to center
      doc.setParagraphAttributes(0, doc.getLength, center, false) //the text
      compQ(x).setFont(lean)
      compQ(x).setBackground(Color.LIGHT_GRAY)
      compQ(x).setForeground(Color.BLACK)
      compQ(x).setEditable(false)
      compQ(x).setText("This is comp test")
      compQ(x).setPreferredSize(new Dimension(50, 50)) //Needed to stop from resizing
      compSide.add(compQ(x))
    }

    middlePanel.add(userSide)
    middlePanel.add(compSide)

    var wrongIndex = scala.util.Random.nextInt(4) //Pick a random choice to be the "wrong" choice
    patternGame.pickCaseClass()
    for(x <- 0 to 3) {
      if(x == wrongIndex) { //Output string of item that doesn't belong
        val item = patternGame.pickItem(patternGame.secondaryClass)
        userQ(x).setText(patternGame.writeCaseClass(item))
      } else { //Output string of items that belong
        val item = patternGame.pickItem(patternGame.primaryClass)
        userQ(x).setText(patternGame.writeCaseClass(item))
      }
    }

    val comboBox = new JComboBox(patternGame.classList.toArray) //This is the dropdown
    comboBox.setEditable(false)

    var userCorrect = 0
    var compCorrect = 0
    val cap = 15 //how many correct answers needed to win

    //User side of the game
    val submit = new JButton("Submit")
    val actions = new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = {
        if(e.getSource() == submit) {
          val guess = comboBox.getSelectedItem()
          if(guess == patternGame.secondaryClass) { //If user guess is correct
            userPoints += 5
            userScore.setText(userPoints.toString())
            userCorrect += 1
          } else {     //user guess is wrong
            userPoints -= 5
            userScore.setText(userPoints.toString())
          }
        }
        if(userCorrect == cap) { //win condition
          finalScreen.popup(userPoints, compPoints, difficulty) //show final screen with score
          f.setVisible(false)
        }
        patternGame.pickCaseClass()    //pick new primary and secondary class
        wrongIndex = scala.util.Random.nextInt(4) //pick a new random answer to be the wrong one
        for(x <- 0 to 3) {
          if(x == wrongIndex) {
            val item = patternGame.pickItem(patternGame.secondaryClass)
            userQ(x).setText(patternGame.writeCaseClass(item))
          } else {
            val item = patternGame.pickItem(patternGame.primaryClass)
            userQ(x).setText(patternGame.writeCaseClass(item))
          }
        }
      }
    }

    //computer side of the game
    val computerGame = Future {
      while(userCorrect < cap) { //while game isn't over keep looping
        patternGame.pickCompCaseClass() //pick primary and secondary case class for computer
        val compWrongIndex = scala.util.Random.nextInt(4)
        for (x <- 0 to 3) { //print options
          if (x == compWrongIndex) {
            val item = patternGame.pickItem(patternGame.compSecondaryClass)
            compQ(x).setText(patternGame.writeCaseClass(item))
          } else {
            val item = patternGame.pickItem(patternGame.compPrimaryClass)
            compQ(x).setText(patternGame.writeCaseClass(item))
          }
        }
        var guess = patternGame.classList(scala.util.Random.nextInt(patternGame.classList.length - 1))
        if (guess == patternGame.compSecondaryClass) { //user guess is correct
          compPoints += 5
          compScore.setText(userPoints.toString())
          compCorrect += 1
        }
        compScore.setText(compPoints.toString())
        if(compCorrect == cap) { //win condition
          finalScreen.popup(userPoints, compPoints, difficulty)
          f.setVisible(false)
        }
        Thread.sleep(delay)
      }

    }
    submit.addActionListener(actions)
    val realPanel = new JPanel()
    realPanel.setBounds(0, 0, 250, 100)
    realPanel.setBackground(HC)
    realPanel.add(comboBox)
    realPanel.add(submit)
    val dummyPanel = new JPanel() //Used to push Combo/Submit further left but not totally left
    dummyPanel.setOpaque(false)
    bottomPanel.add(realPanel)
    bottomPanel.add(dummyPanel)

    totalPanel.add(topPanel)
    totalPanel.add(middlePanel)
    totalPanel.add(bottomPanel)
    f.add(totalPanel)
    f.setSize(700,700)
    f.setVisible(true)
    f.setResizable(false)
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    f.setLocation(400, 150)


  }
}