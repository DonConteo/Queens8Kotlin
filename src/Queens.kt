
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.stream.Collectors
import kotlin.math.ceil
import kotlin.math.floor

fun main(args: Array<String>) {

    var reader = BufferedReader(InputStreamReader(System.`in`))
    var queens = ArrayList<Int>()

/*Внесение координат ферзей в список через консоль. Первая цифра - координата x, вторая - координата y.
Обе цифры вводить без пробелов и знаков препинания, координаты для нового ферзя вводить с новой строки*/
    for (i in 1..8) {
        var s: String = reader.readLine()
        var x: String = s.substring(0, 1)
        var y: String = s.substring(1)
        var xx: Int = Integer.parseInt(x)
        var yy: Int = Integer.parseInt(y)
        var queen: Int = xx + (yy - 1) * 8      //формула для вычисления положения ферзя на доске исходя из введенных координат
        queens.add(queen)
    }
}

    fun dangerFields(queens: ArrayList<Int>) {      //Метод для вычисления битых полей для каждого ферзя
        var fields = HashSet<Int>()
        queens.forEach {
            var f: Int
            var min = 1
            var max = 8
            while (max <= 64) {     //вычисление битых полей по горизонтали влево и вправо
                if (it in min..max) {
                    f = it - 1
                    while (it > min) {
                        fields.add(f)
                        min++
                        f--
                    }
                    f = it + 1
                    while (it < max) {
                        fields.add(f)
                        max--
                        f++
                    }
                }
                min += 8
                max += 8
            }

            f = it - 8
            while (f > 0) {     //вычисление битых полей по вертикали вниз
                if (f > 8) {
                    fields.add(f)
                }
                f -= 8
            }
            f = it + 8
            while (f < 65) {        //вычисление битых полей по вертикали вверх
                if (f < 57) {
                    fields.add(f)
                }
                f += 8
            }

            var count = floor((it / 8).toDouble()).toInt()      //подсчет клеток до края доски по диагонали влево
            var count2 = 8 - ceil((it / 8).toDouble()).toInt()  //подсчет клеток до края доски по диагонали вправо

            f = it
            while (count > 0) {     //вычисление битых полей по диагонали влево-вниз
                if (f == 17 || f == 25 || f == 33 || f == 41 || f == 49 || f == 57) {
                    break
                } else if (f > 9) {
                    fields.add(f - 9)
                    f -= 9
                }
                count--
            }

            f = it
            while (count > 0) {     //вычисление битых полей по диагонали влево-вверх
                if (f == 9 || f == 17 || f == 25 || f == 33 || f == 41 || f == 49) {
                    break
                } else if (f < 57) {
                    fields.add(f + 7)
                    f += 7
                }
                count--
            }

            f = it
            while (count2 > 0) {        //вычисление битых полей по диагонали вправо-вниз
                if (f == 16 || f == 24 || f == 32 || f == 40 || f == 48 || f == 56 || f == 64) {
                    break
                } else if (f > 8) {
                    fields.add(f - 7)
                    f -= 7
                }
                count2--
            }

            f = it
            while (count2 > 0) {        //вычисление битых полей по диагонали вправо-вверх
                if (f == 8 || f == 16 || f == 24 || f == 32 || f == 40 || f == 48) {
                    break
                } else if (f < 56) {
                    fields.add(f + 9)
                    f += 9
                }
                count2--
            }
        }

        //Выведение результата в консоль
        var positions = HashSet(queens)
        var intersect = fields.stream().filter(positions::contains).collect(Collectors.toSet())
        if(intersect.isEmpty()){
            println("NO")       //если ни один ферзь не бьет другого
        } else println("YES")   //если какие-либо ферзи бьют друг друга
    }





