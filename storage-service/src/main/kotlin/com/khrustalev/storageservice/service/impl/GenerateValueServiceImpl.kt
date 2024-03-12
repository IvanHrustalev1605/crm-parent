package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.enums.RepairPartsCategory
import com.khrustalev.storageservice.entity.schems.dictionary.ChineseCarDetails
import com.khrustalev.storageservice.entity.schems.dictionary.EuropeanCarDetails
import com.khrustalev.storageservice.entity.schems.dictionary.RepairPartsLargeGroup
import com.khrustalev.storageservice.entity.schems.dictionary.SpecialTechniqueDetails
import com.khrustalev.storageservice.entity.schems.storage.*
import com.khrustalev.storageservice.repository.*
import com.khrustalev.storageservice.service.abstracts.GenerateValueService
import com.khrustalev.storageservice.utils.GeneratorUtils
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class GenerateValueServiceImpl(private val carRepository: CarRepository,
                               private val trackRepository: TrackRepository,
                               private val driverRepository: DriverRepository,
                               private val mechanicRepository: MechanicRepository,
                               private val securityRepository: SecurityRepository,
                               private val engineerRepository: EngineerRepository,
                               private val repairPartsRepository: RepairPartsRepository,
                               private val repairPartsGroupRepository: RepairPartsGroupRepository,
                               private val repairPartsLargeGroupRepository: RepairPartsLargeGroupRepository,
                               private val europeanCarDetailsRepository: EuropeanCarDetailsRepository,
                               private val chineseCarDetailsRepository: ChineseCarDetailsRepository,
                               private val specialTechniqueDetailsRepository: SpecialTechniqueDetailsRepository
) : GenerateValueService {
    override fun generateDbValues(): Boolean {
        for (i in 1..50) {
            val driver = Driver().also {
                it.license = GeneratorUtils.generateRandomString(8)
                it.personInfo = generatePersonInfo()
            }
            val car = Car().also {
                it.mileage = Random.nextInt(250000, 999999)
                it.model = GeneratorUtils.generateRandomString(6)
                it.number = GeneratorUtils.generateRandomString(9)
                it.vinNumber = GeneratorUtils.generateRandomString(18)
            }
            val track = Track().also {
                it.number = GeneratorUtils.generateRandomString(4)
                it.vinNumber = GeneratorUtils.generateRandomString(15)
            }
            carRepository.save(car)
            driverRepository.save(driver)
            trackRepository.save(track)
        }
        for (i in 1 .. 10) {
            val mechanic: Mechanic = Mechanic().also {
                it.personInfo = generatePersonInfo()
            }
            mechanicRepository.save(mechanic)
        }
        for (i in 1.. 5) {
            val engineer = Engineer().also {
                it.personInfo = generatePersonInfo()
            }
            engineerRepository.save(engineer)
        }
        for (i in 1 .. 8) {
            val security = Security().also {
                it.personInfo = generatePersonInfo()
            }
            securityRepository.save(security)
        }
        generateRepairParts()
        return true
    }
    private fun generatePersonInfo() : PersonInfo {
        return PersonInfo().also {
            it.age = Random.nextInt(18, 65)
            it.firstName = GeneratorUtils.generateRandomFirstName()
            it.middleName = GeneratorUtils.generateRandomMiddleName()
            it.lastName = GeneratorUtils.generateRandomSecondName()
            it.email = GeneratorUtils.generateRandomString(6) + "@mail.ru"
        }
    }
    override fun generateRepairParts() : Boolean {
        for (i in 1..1500) {
            val also = RepairParts().also {
                it.name = GeneratorUtils.generateRandomString(Random.nextInt(5, 8))
                it.mileageResource = Random.nextInt(10000, 100000).toLong()
                it.category = RepairPartsCategory.entries[Random.nextInt(0, RepairPartsCategory.entries.size)]
            }
            repairPartsRepository.save(also)
        }
        return true
    }

    override fun setRepairPartGroup() {
        repairPartsRepository.findAll().forEach {
            it.repairPartsGroup = repairPartsGroupRepository.findById(Random.nextLong(1, 6)).get()
            repairPartsRepository.save(it)
        }
    }

    override fun generateDictionary(): Boolean {
        val list1: MutableList<String> = mutableListOf("Кузовные детали", "Запчасти агрегатов и узлов автомобиля", "мелкие запасные части"
            , "Среднегабаритные автодетали", "Крупногабаритные, а также нестандартные элементы", "Шины, другие резинотехнические изделия для авто"
            , "Автостекла", "Масла, рабочие жидкости, другие расходные материалы", "Аккумуляторы")
        list1.forEach {
            val largeGroup = RepairPartsLargeGroup(null, it)
            repairPartsLargeGroupRepository.save(largeGroup)
        }
        val chineseTruckDetails = mutableListOf<String>(
            "Аккумуляторы для китайских грузовиков",
            "Амортизаторы кабины для китайских грузовиков",
            "Амортизаторы подвески для китайских грузовиков",
            "Баки топливные для китайских грузовиков",
            "Барабаны тормозные для китайских грузовиков",
            "Болты для китайских грузовиков",
            "Валы тормозные для китайских грузовиков",
            "Вискомуфты для китайских грузовиков",
            "Гайки для китайских грузовиков",
            "Генераторы для китайских грузовиков",
            "Детали катализатора для китайских грузовиков",
            "Детали мостов и редукторов для китайских грузовиков",
            "Детали подвески для китайских грузовиков",
            "Детали системы выхлопа для китайских грузовиков",
            "Диски колесные для китайских грузовиков",
            "Диски тормозные для китайских грузовиков",
            "Заклепки для китайских грузовиков",
            "Запчасти двигателей китайских грузовиков",
            "Запчасти КПП для китайских грузовиков",
            "Запчасти подвески кабины для Китайских грузовиков",
            "Запчасти рулевого управления для китайских грузовиков",
            "Запчасти системы охлаждения для китайских грузовиков",
            "Запчасти системы подъема кабины для китайских грузовиков",
            "Запчасти стеклоочистителя и омывателя для китайских грузовиков",
            "Запчасти топливной системы для китайских грузовиков",
            "Запчасти тормозной системы для китайских грузовиков",
            "Зеркала для китайских грузовиков",
            "Интеркулеры для китайских грузовиков",
            "Кабели электрические для китайских грузовиков",
            "Карданные валы и крестовины для китайских грузовиков",
            "Коленвалы для китайских грузовиков",
            "Колодки барабанные и накладки для китайских грузовиков",
            "Колодки тормозные дисковые для китайских грузовиков",
            "Компрессоры воздушные для китайских грузовиков",
            "Компрессоры кондиционера для китайских грузовиков",
            "Краны и запчасти пневмосистемы для китайских грузовиков",
            "Кузовные запчасти для китайских грузовиков",
            "Модуляторы для китайских грузовиков",
            "Насосы водяные для китайских грузовиков",
            "Отопители автономные для китайских грузовиков",
            "Отопление и вентиляция для китайских грузовиков",
            "Патрубки системы охлаждения для китайских грузовиков",
            "Переключатели для китайских грузовиков",
            "Пневмоподушки для китайских грузовиков",
            "Подвесные подшипники для китайских грузовиков",
            "Подшипники для китайских грузовиков")
        val specialTechniqueDetails = mutableListOf<String>(
            "Болты для спецтехники",
            "Датчики для спецтехники",
            "Запчасти двигателей для спецтехники",
            "Масла, антифриз, смазка для спецтехники",
            "Прокладки двигателя для спецтехники",
            "Ролики и ремни двигателя для спецтехники",
            "Сальники, прокладки и уплотнения для спецтехники",
            "Топливная система для спецтехники",
            "Турбокомпрессоры для спецтехники",
            "Фильтры воздушные для спецтехники",
            "Фильтры масляные для спецтехники",
            "Фильтры топливные для спецтехники",
            "Форсунки топливные для спецтехники"
        )
        val europeanTruckDetails = mutableListOf<String>(
            "Популярные товары",
            "Специальное предложение",
            "Уцененные товары",
            "Аккумуляторы для грузовиков и другой техники",
            "Аксессуары для грузовиков",
            "Амортизаторы кабины для европейских грузовиков",
            "Амортизаторы подвески для европейских грузовиков",
            "Баки топливные для европейских грузовиков",
            "Барабаны тормозные для европейских грузовиков",
            "Болты для европейских грузовиков",
            "Валы тормозные для европейских грузовиков",
            "Вискомуфты для европейских грузовиков",
            "Гайки колесные",
            "Генераторы для европейских грузовиков",
            "Датчики для европейских грузовиков",
            "Детали катализатора для европейских грузовиков",
            "Детали мостов для европейских грузовиков",
            "Детали подвески для европейских грузовиков",
            "Детали системы выхлопа для европейских грузовиков",
            "Диски колесные для европейских грузовиков",
            "Диски тормозные для европейских грузовиков и полуприцепов",
            "Заклепки для европейских грузовиков",
            "Запчасти двигателей для европейских грузовиков",
            "Запчасти для гидробортов",
            "Запчасти для спецтехники",
            "Запчасти КПП для европейских грузовиков",
            "Запчасти подвески кабины для европейских грузовиков",
            "Зеркала для европейских грузовиков",
            "Инструмент",
            "Интеркулеры для европейских грузовиков",
            "Кабели электрические для европейских грузовиков",
            "Карданные валы для европейских грузовиков",
            "Коленвалы для грузовиков для европейских грузовиков",
            "Колодки барабанные и накладки для европейских грузовиков и прицепов",
            "Колодки тормозные для европейских грузовиков и прицепов",
            "Компрессоры воздушные для европейских грузовиков",
            "Компрессоры кондиционера для европейских грузовиков",
            "Краны и запчасти пневмосистемы для европейских грузовиков",
            "Кузовные запчасти для европейских грузовиков",
            "Лампы для европейских грузовиков",
            "Лапы опорные полуприцепа",
            "Масла, антифриз, смазка для европейских грузовиков",
            "Модуляторы ABS и EBS для грузовиков",
            "Насосы водяные для европейских грузовиков",
            "Отопители автономные для европейских грузовиков",
            "Отопление и вентиляция салона для европейских грузовиков",
            "Патрубки системы охлаждения для европейских грузовиков",
            "Переключатели для европейских грузовиков",
            "Пневмоподушки для европейских грузовиков и полуприцепов",
            "Подвесные подшипники для европейских грузовиков",
            "Подшипники для европейских грузовиков и прицепов",
            "Прокладки двигателя для европейских грузовиков",
            "Радиаторы для европейских грузовиков",
            "Разъемы электрические для европейских грузовиков",
            "Распредвалы для европейских грузовиков",
            "Реле для европейских грузовиков",
            "Ремкомплекты для европейских грузовиков",
            "Ремкомплекты суппортов для европейских грузовиков и полуприцепов",
            "Ресиверы воздушные",
            "Рессоры для европейских грузовиков и прицепов",
            "Рефрижераторы",
            "Ролики и ремни двигателя для европейских грузовиков",
            "Рулевое управление для европейских грузовиков",
            "Сальники, прокладки и уплотнения для европейских грузовиков",
            "Седельные устройства",
            "Сепараторы топлива для европейских грузовиков",
            "Система охлаждения для европейских грузовиков",
            "Система подъема кабины для европейских грузовиков",
            "Система стеклоочистителя и омывателя для европейских грузовиков",
            "Соединители и переходники для европейских грузовиков",
            "Стартеры для европейских грузовиков",
            "Стекла для европейских грузовиков",
            "Стремянки рессор для европейских грузовиков",
            "Ступицы для европейских грузовиков и полуприцепов",
            "Суппорта для европейских грузовиков и полуприцепов",
            "Сцепление для европейских грузовиков",
            "Термостаты для европейских грузовиков",
            "Топливная система для европейских грузовиков",
            "Тормозная система для европейских грузовиков",
            "Трещетки тормозные для европейских грузовиков",
            "Трубки топливные для европейских грузовиков",
            "Турбокомпрессоры для европейских грузовиков",
            "Тяги рулевые и наконечники для европейских грузовиков",
            "Фары и фонари для европейских грузовиков",
            "Фильтр влагоотделителя для европейских грузовиков",
            "Фильтры воздушные для европейских грузовиков",
            "Фильтры ГУР для европейских грузовиков",
            "Фильтры КПП и мостов для европейских грузовиков",
            "Фильтры масляные для европейских грузовиков",
            "Фильтры мочевины для европейских грузовиков",
            "Фильтры салона для европейских грузовиков",
            "Фильтры сапуна для европейских грузовиков",
            "Фильтры топливные для европейских грузовиков",
            "Фильтры тосола для европейских грузовиков",
            "Форсунки топливные для европейских грузовиков",
            "Фурнитура прицепа",
            "Цепи на колеса для грузовых автомобилей",
            "Шкворни и ремкомплекты для европейских грузовиков",
            "Электрическая система для европейских грузовиков",
            "Энергоаккумуляторы и тормозные камеры для европейских грузовиков"
            )
        chineseTruckDetails.forEach {
            val var1 = ChineseCarDetails(null, it)
            chineseCarDetailsRepository.save(var1)
        }
        europeanTruckDetails.forEach {
            val var2 = EuropeanCarDetails(null, it)
            europeanCarDetailsRepository.save(var2)
        }
        specialTechniqueDetails.forEach {
            val var3 = SpecialTechniqueDetails(null, it)
            specialTechniqueDetailsRepository.save(var3)
        }

        return true
    }
}