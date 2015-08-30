import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine
import scala.math.log10
import java.io.PrintWriter
import scala.io.Source

object Boilerplate {
  def main(args: Array[String]) {
    // Loops
    var i = 0
    while (i <= 10) {
      println(i)
      i += 1
    }
    for (i <- 1 to 10) {
      println(i)
    }
    val randomLetters = "WORD"
    for (i <- 0 until randomLetters.length) {
      println(randomLetters(i))
    }
    val aList = List(1, 2, 3, 4, 5)
    for (i <- aList) {
      println("Item: " + i)
    }
    // List filter
    val evenList = for {
      i <- 1 to 20
      if (i % 2) == 0
    } yield i
    for (i <- evenList) {
      println("Item: " + i)
    }
    for (i <- 1 to 5; j <- 6 to 10) {
      println(i + " " + j)
    }
    // There is no break and continue statement.
    def printPrimes() {
      val primeList = List(1, 2, 3, 5, 7, 11)
      for (i <- primeList) {
        if (i == 11) {
          // simulated break
          return
        }
        if (i != 1) {
          // simulated continue
          println(i)
        }
      }
    }
    printPrimes()
    var numberGuess = 0
    do {
      print("Guess a number ")
      numberGuess = readLine().toInt
      // readInt, readDouble, readByte, readShort, readLong
    } while (numberGuess != 15)
    printf("Guessed the secret number %d.\n", 15)

    // Strings
    val name = "Mary"
    val age = 1
    val weight = 1.5
    println(s"Hello $name")
    println(f"I am ${age + 1} and weigh $weight%.2f")
    // %c Character
    // %d Integer
    // %f Float
    // %s String
    printf("'%5d'\n", 5) // right justification
    printf("'%-5d'\n", 5) // left justification
    printf("'%05d'\n", 5) // zero fill
    printf("'%.5f'\n", 3.14) // five decimal places
    printf("'%-5s'\n", "hi") // string justification
    // Special characters
    // \b backspace
    // \\ escaped backslash
    // \a alert sound
    val randomSentence = "I saw a dragon fly by"
    println("index 3: " + randomSentence(3))
    println("length: " + randomSentence.length)
    println(randomSentence.concat(" and explode"))
    println("Are strings equal " + "I saw a dragon".equals(randomSentence))
    println(randomSentence.indexOf("dragon"))
    val randomSentenceArray = randomSentence.toArray
    for (v <- randomSentenceArray) {
      println(v)
    }

    // Functions
    /*
    def functionName(param1:dataType, param2:dataType) : returnType = {
        function body
        return result
    }
    */
    def getSum(num1: Int = 1, num2: Int = 1): Int = {
      num1 + num2 // return keywords is optional
    }
    println(getSum(5, 4))
    println(getSum(num2 = 5, num1 = 4)) // named arguments
    // functions without return values are called procedures
    def sayHi(): Unit = {
      println("Hello friend.")
    }
    sayHi()
    // Variable arguments
    def getSumVariable(args: Int*): Int = {
      var sum: Int = 0
      for (num <- args) {
        sum += num
      }
      sum
    }
    println(getSumVariable(1, 2, 3, 4, 5))
    // Recursion
    def factorial(num: BigInt): BigInt = {
      if (num <= 1) {
        1
      } else {
        num * factorial(num - 1)
      }
    }
    println(factorial(4))

    // Arrays
    val favouriteNumbers = new Array[Int](20)
    val friends = Array("Bob", "Tom")
    friends(0) = "Sue"
    println(friends(0))
    val friends2 = ArrayBuffer[String]() // changeable length
    friends2.insert(0, "Phil")
    friends2 += "Mark" // next slot
    friends2 ++= Array("Susy", "Paul") // add multiple at end
    friends2.insert(1, "Mike", "Marry", "Sally") // insert multiple in the middle
    friends2.remove(1, 2)
    var friend: String = " "
    for (friend <- friends2) {
      println(friend)
    }
    for (j <- favouriteNumbers.indices) {
      favouriteNumbers(j) = j
      println(favouriteNumbers(j))
    }
    val favouriteNumbersTimes2 = for (num <- favouriteNumbers) yield 2 * num
    // Print all elements
    favouriteNumbersTimes2.foreach(println)
    val favouriteNumbersDividedBy4 = for (num <- favouriteNumbers if num % 4 == 0) yield num
    favouriteNumbersDividedBy4.foreach(println)
    val multiplicationTable = Array.ofDim[Int](10, 10)
    for (i <- 0 to 9) {
      for (j <- 0 to 9) {
        multiplicationTable(i)(j) = i * j
      }
    }
    for (i <- 0 to 9) {
      for (j <- 0 to 9) {
        printf("%d : %d = %d\n", i, j, multiplicationTable(i)(j))
      }
    }
    println("Sum: " + favouriteNumbers.sum) // sum of all elements
    // More: min, max
    val sortedNumbers = favouriteNumbers.sortWith(_ > _) // sort descending
    println(sortedNumbers.deep.mkString(", "))

    // Maps
    val employees = Map("Manager" -> "Bob Smith", "Secretary" -> "Sue Brown")
    if (employees.contains("Manager")) {
      printf("Manager: %s\n", employees("Manager"))
    }
    val customers = collection.mutable.Map(100 -> "Paul Smith", 101 -> "Sally Smith")
    printf("Customer 1: %s\n", customers(100))
    customers(100) = "Tom Marks"
    customers(102) = "Megan Swift"
    for ((k, v) <- customers) {
      printf("%d : %s\n", k, v)
    }

    // Tuple
    val tupleMarge = (103, "Marge Simpson", 10.25)
    printf("%s owes us $%.2f\n", tupleMarge._2, tupleMarge._3)
    tupleMarge.productIterator.foreach { i => println(i) }
    println(tupleMarge.toString())

    // Classes
    // var = mutable
    class Animal(var name: String, var sound: String) {
      // Constructor parameters in ()
      this.setName(name)
      // constructor
      // scala has no static methods or variables
      val id = Animal.newIdNum

      // getter
      def getName: String = name

      def getSound: String = sound

      // setter
      def setName(name: String) {
        // if it contains any decimal number, not.
        if (!name.matches(".*\\d+.*")) {
          this.name = name
        } else {
          this.name = "No Name"
        }
      }

      def setSound(sound: String) {
        this.sound = sound
      }

      // constructor only with name
      def this(name: String) {
        this("No Name", "No Sound")
        this.setName(name)
      }

      // constructor with no argument
      def this() {
        this("No Name", "No Sound")
      }

      override def toString: String = {
        "%s with the id %d says %s".format(this.name, this.id, this.sound)
      }
    }
    // companion object
    object Animal {
      // simulate static field
      private var idNumber = 0

      // simulate static function
      private def newIdNum = {
        idNumber += 1
        idNumber
      }
    }
    val rover = new Animal
    rover.setName("Rover")
    rover.setSound("Woof")
    printf("%s says %s\n", rover.getName, rover.getSound)
    val whiskers = new Animal("Whiskers", "Meow")
    println(s"${whiskers.getName} with id ${whiskers.id} says ${whiskers.sound}")
    println(whiskers.toString)

    // Inheritance
    class Dog(name: String, sound: String, growl: String) extends Animal(name, sound) {
      def this(name: String, sound: String) {
        this("No Name", sound, "No Growl")
        this.setName(name)
      }

      def this(name: String) {
        this("No Name", "No Sound", "No Growl")
        this.setName(name)
      }

      def this() {
        this("No Name", "No Sound", "No Growl")
      }

      override def toString: String = {
        "%s with the id %d says %s or %s".format(this.name, this.id, this.sound, this.growl)
      }
    }
    val spike = new Dog("Spike", "Woof", "Grrrrr")
    spike.setName("Spike")
    println(spike.toString)

    // Abstract classes
    abstract class Mammal(val name: String) {
      var moveSpeed: Double

      def move: String
    }
    class Wolf(name: String) extends Mammal(name) {
      var moveSpeed = 35.0

      def move = "The wolf %s runs %.2f mph".format(this.name, this.moveSpeed)
    }
    val fang = new Wolf("Fang")
    fang.moveSpeed = 36.0
    println(fang.move)

    // Trait
    // You can extend multiple traits.
    trait Flyable {
      def fly: String
    }
    trait BulletProof {
      def hitByBullet: String

      def ricochet(startSpeed: Double): String = {
        "The bullet ricochets at a speed of %.1f ft/sec".format(startSpeed * 0.75)
      }
    }
    class Superhero(val name: String) extends Flyable with BulletProof {
      def fly = "%s flys through the air".format(this.name)

      def hitByBullet = "The bullet bounces off of %s".format(this.name)
    }
    val superman = new Superhero("Superman")
    println(superman.fly)
    println(superman.hitByBullet)
    println(superman.ricochet(2500))

    // Higher order functions
    val log10Func = log10 _
    println(log10Func(1000))
    List(1000.0, 10000.0).map(log10Func).foreach(println)
    List(1, 2, 3, 4, 5).map((x: Int) => x * 50).foreach(println)
    List(1, 2, 3, 4, 5).filter(_ % 2 == 0).foreach(println)
    def times3(num: Int) = num * 3
    def times4(num: Int) = num * 4
    def multiplyIt(func: (Int) => Double, num: Int) = {
      func(num)
    }
    printf("%.1f\n", multiplyIt(times3, 100))
    printf("%.1f\n", multiplyIt(times4, 100))
    // Closure
    val divisorValue = 5
    val divisor5 = (num: Double) => num / divisorValue
    print(divisor5(5.0))

    // File IO
    val writer = new PrintWriter("test.txt")
    writer.write("Just some random text.\nMore random text")
    writer.close()
    val textFromFile = Source.fromFile("test.txt", "UTF-8")
    val lineIterator = textFromFile.getLines()
    for (line <- lineIterator) {
      println(line)
    }
    textFromFile.close()

    // Handle exceptions
    def divideNumbers(number1: Int, number2: Int) = try {
      number1 / number2
    } catch {
      case ex: java.lang.ArithmeticException => "Can't divide by zero"
    } finally {
      // clean up after exception
    }
    println(divideNumbers(3, 0))
  }
}
