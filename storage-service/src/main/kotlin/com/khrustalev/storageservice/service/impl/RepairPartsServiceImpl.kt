package com.khrustalev.storageservice.service.impl

import com.khrustalev.storageservice.dto.RepairPartsDto
import com.khrustalev.storageservice.entity.schems.storage.RepairParts
import com.khrustalev.storageservice.mappers.RepairPartsMapper
import com.khrustalev.storageservice.repository.RepairPartsRepository
import com.khrustalev.storageservice.service.abstracts.RepairPartsService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import java.util.*
@Service
class RepairPartsServiceImpl(private val repairPartsRepository: RepairPartsRepository,
                             @Lazy private val repairPartsMapper: RepairPartsMapper) : RepairPartsService {

    override fun getById(id: Long): RepairParts? {
        return repairPartsRepository.findById(id).orElse(null)
    }

    override fun save(repairPartsDto: RepairPartsDto): RepairPartsDto {
        return repairPartsMapper.toDto(repairPartsRepository.save(repairPartsMapper.toEntity(repairPartsDto)))
    }

    override fun countByEtalonArt(art: String): Long {
        return repairPartsRepository.countAllByEtalonPartsDictionary_Art(art)
    }

    override fun getByIds(ids: MutableList<Long>): MutableList<RepairPartsDto> {
        return repairPartsRepository.findAllById(ids).stream()
            .map { repairPartsMapper.toDto(it) }
            .toList().toMutableList()
    }

    override fun getByNumberList(numberList: MutableList<UUID>): MutableList<RepairPartsDto> {
        return numberList.stream()
            .map { repairPartsRepository.findByNumber(it).orElse(null) }
            ?.map { x -> repairPartsMapper.toDto(x) }
            ?.toList()?.toMutableList() ?: mutableListOf()

    }

    override fun getByCarNumber(carNumber: String): MutableList<RepairPartsDto> {
        return repairPartsRepository.findAllByCar_Number(carNumber)?.stream()
            ?.map { repairPartsMapper.toDto(it) }
            ?.toList()?.toMutableList() ?: mutableListOf()
    }

    override fun getByCarId(carId: Long): MutableList<RepairPartsDto> {
        return if (!CollectionUtils.isEmpty(repairPartsRepository.findAllByCar_Id(carId))) repairPartsRepository.findAllByCar_Id(carId).stream()
            .map { repairPartsMapper.toDto(it) }
            .toList().toMutableList() else mutableListOf()
    }

    override fun countByGroupName(groupName: String): Long {
       return repairPartsRepository.countAllByRepairPartsLargeGroup_Name(groupName)
    }

    override fun countByGroupId(groupId: Long): Long {
        return repairPartsRepository.countAllByRepairPartsLargeGroup_Id(groupId)
    }

    override fun getByGroupName(groupName: String): MutableList<RepairPartsDto> {
        return if (!CollectionUtils.isEmpty(repairPartsRepository.findAllByRepairPartsLargeGroup_Name(groupName)))
            repairPartsRepository.findAllByRepairPartsLargeGroup_Name(groupName).stream().map { repairPartsMapper.toDto(it) }
                .toList().toMutableList()
        else mutableListOf()
    }

    override fun getAllNotInstalled(): MutableList<RepairPartsDto> {
        return if (!CollectionUtils.isEmpty(repairPartsRepository.findAllByInstalledIsFalse()))
            repairPartsRepository.findAllByInstalledIsFalse().stream().map { repairPartsMapper.toDto(it) }
                .toList().toMutableList()
        else mutableListOf()
    }

    override fun getInstalledInCarByCarNumber(carNumber: String): MutableList<RepairPartsDto> {
        return if (!CollectionUtils.isEmpty(repairPartsRepository.findAllByInstalledIsFalseAndCar_Number(carNumber)))
            repairPartsRepository.findAllByInstalledIsFalseAndCar_Number(carNumber).stream()
                .map { repairPartsMapper.toDto(it) }
                .toList().toMutableList()
        else mutableListOf()
    }

    override fun convertToDto(repairParts: RepairParts): RepairPartsDto {
        return repairPartsMapper.toDto(repairParts)
    }

    override fun getByEtalonArt(etalonArt: String): MutableList<RepairPartsDto> {
        return if (!CollectionUtils.isEmpty(repairPartsRepository.findAllByEtalonPartsDictionary_Art(etalonArt)))
            repairPartsRepository.findAllByEtalonPartsDictionary_Art(etalonArt).stream()
                .map { repairPartsMapper.toDto(it) }
                .toList().toMutableList()
        else mutableListOf()
    }

    override fun getInstalledInRepair(repairId: Long): MutableList<RepairPartsDto> {
        return repairPartsRepository.getAllInstalledRepairPArtsInRepair(repairId).map { repairPartsMapper.toDto(it) }.toMutableList()
    }
}