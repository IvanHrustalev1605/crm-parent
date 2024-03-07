package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.entity.*
import com.khrustalev.storageservice.entity.enums.RepairPartsCategory
import com.khrustalev.storageservice.repository.*
import com.khrustalev.storageservice.service.abstracts.GenerateValueService
import com.khrustalev.storageservice.utils.GeneratorUtils
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class GenerateValueServiceImpl(private val carRepository: CarRepository,
                               private val trackRepository: TrackRepository,
                               private val driverRepository: DriverRepository,
                               private val mechanicRepository: MechanicRepository,
                               private val securityRepository: SecurityRepository,
                               private val engineerRepository: EngineerRepository,
                               private val repairPartsRepository: RepairPartsRepository,
                               private val repairPartsGroupRepository: RepairPartsGroupRepository
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
}