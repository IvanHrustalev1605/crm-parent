package com.khrustalev.storageservice.utils

import org.springframework.stereotype.Service
import kotlin.random.Random
@Service
class GeneratorUtils {

    companion object {
        private val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()
        private val maleNames = arrayOf("Александр", "Максим", "Иван", "Артем", "Дмитрий", "Никита", "Даниил", "Егор", "Сергей", "Кирилл", "Илья", "Роман", "Матвей", "Владимир", "Андрей", "Денис", "Алексей", "Евгений", "Глеб", "Тимофей", "Арсений", "Леонид", "Федор", "Игорь", "Павел", "Антон", "Вячеслав", "Михаил", "Константин", "Станислав")
        private val maleLastNames = listOf(
            "Иванов", "Смирнов", "Кузнецов", "Соколов", "Попов",
            "Васильев", "Петров", "Михайлов", "Новиков", "Федоров"
        )
        private val malePatronymics = listOf(
            "Иванович", "Сергеевич", "Алексеевич", "Дмитриевич", "Максимович",
            "Николаевич", "Андреевич", "Валерьевич", "Владимирович", "Артёмович"
        )
        fun generateRandomString(length: Int): String = (1..length)
                .map { Random.nextInt(0, chars.size) }
                .map(chars::get)
                .joinToString("")
        fun generateRandomFirstName() : String = maleNames[Random.nextInt(0, maleNames.size)]
        fun generateRandomSecondName() : String = maleLastNames[Random.nextInt(0, maleLastNames.size)]
        fun generateRandomMiddleName() : String = malePatronymics[Random.nextInt(0, malePatronymics.size)]

    }
}