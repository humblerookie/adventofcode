import com.advcode.days.Day4
import com.advcode.days.Solution
import java.io.File

fun main() {

    val resPath = File("src/main/resources").absolutePath
    val solution: Solution = Day4(resPath)
    println("Part 1")
    println("Test File Solution:")
    solution.solvePart1(solution.testFile)
    println("Input File Solution:")
    solution.solvePart1(solution.inputFile)
    println("----------------------------")
    println("Part 2")
    println("Test File Solution:")
    solution.solvePart2(solution.testFile)
    println("Input File Solution:")
    solution.solvePart2(solution.inputFile)
}