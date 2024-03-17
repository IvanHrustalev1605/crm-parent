package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.enums.RepairPartsCategory
import com.khrustalev.storageservice.entity.schems.dictionary.EtalonPartsDictionary
import com.khrustalev.storageservice.entity.schems.dictionary.RepairPartsLargeGroup
import com.khrustalev.storageservice.entity.schems.storage.*
import com.khrustalev.storageservice.repository.*
import com.khrustalev.storageservice.service.abstracts.GenerateValueService
import com.khrustalev.storageservice.utils.ExelParser
import com.khrustalev.storageservice.utils.GeneratorUtils
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class GenerateValueServiceImpl(private val carRepository: CarRepository,
                               private val trackRepository: TrackRepository,
                               private val driverRepository: DriverRepository,
                               private val mechanicRepository: MechanicRepository,
                               private val securityRepository: SecurityRepository,
                               private val engineerRepository: EngineerRepository,
                               private val repairPartsRepository: RepairPartsRepository,
                               private val etalonPartsDictionaryRepository: EtalonPartsDictionaryRepository,
                               private val etalonPartsStocksRepository: EtalonPartsStocksRepository,
                               private val repairPartsLargeGroupRepository: RepairPartsLargeGroupRepository) : GenerateValueService {
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
        val list1 = repairPartsLargeGroupRepository.findAll().size.toLong()
        val list2 = etalonPartsDictionaryRepository.findAll().size.toLong()
        for (i in 1..1500) {
            val also = RepairParts().also {
                it.name = GeneratorUtils.generateRandomString(Random.nextInt(5, 8))
                it.mileageResource = Random.nextInt(10000, 100000).toLong()
                it.repairPartsLargeGroup = repairPartsLargeGroupRepository.findById(Random.nextLong(1, list1)).get()
                it.vendorArt = GeneratorUtils.generateRandomString(10)
                it.isOrigin = Random.nextBoolean()
                it.installed = false
                it.etalonPartsDictionary = etalonPartsDictionaryRepository.findById(Random.nextLong(2, list2)).get()
                it.car = null
            }
            repairPartsRepository.save(also)
        }
        return true
    }


    override fun generateDictionary(): Boolean {
        val etalonDictionary = ExelParser.getEtalonValues()
        etalonPartsDictionaryRepository.saveAll(etalonDictionary)
        return true
    }

    override fun generateRandomStocks(): Boolean {
        etalonPartsDictionaryRepository.findAll().forEach {x ->
            val also = EtalonPartsStocks().also {
                it.stocks = Random.nextInt(32, 100);
                it.etalonPartsDictionary = x
            }
            etalonPartsStocksRepository.save(also)
        }
        return true
    }
}