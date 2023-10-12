import polymorphism.Animal
import polymorphism.Cat
import polymorphism.Monkey

fun main(args: Array<String>) {
    println("Hello World!")

    val kkobuki: Poketmon = AquaPoketmon("꼬부기")
    println("이름: ${kkobuki.name}, 속성: ${kkobuki.type}")

    val cat: Animal = Cat()
    val monkey: Animal = Monkey()

    cat.울다()
    monkey.울다()
}
