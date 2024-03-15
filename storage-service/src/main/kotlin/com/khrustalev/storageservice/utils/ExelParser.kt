package com.khrustalev.storageservice.utils

import com.khrustalev.storageservice.entity.schems.dictionary.EtalonPartsDictionary
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class ExelParser {
    companion object {
        fun getEtalonValues() : MutableList<EtalonPartsDictionary> {
            val fileInputStream = FileInputStream(File("C:/IdeaProjects/crm-parent/storage-service/Эталонные аналоги 2.xlsx"))
            val artList = ArrayList<String>()
            val nameList = ArrayList<String>()
            val analogNameList = ArrayList<String>()
            try {
                val workbook: Workbook = XSSFWorkbook(fileInputStream)
                val sheet: Sheet = workbook.getSheetAt(0)
                val numRows: Int = sheet.physicalNumberOfRows
                for (i in 0 until numRows) {
                    val row: Row = sheet.getRow(i)
                    val cell: Cell = row.getCell(1)
                    if (Objects.requireNonNull(cell.cellType) === CellType.STRING) {
                        artList.add(cell.stringCellValue)
                    }
                    workbook.close()
                    fileInputStream.close()
                }
                for (i in 0 until numRows) {
                    val row: Row = sheet.getRow(i)
                    val cell: Cell = row.getCell(2)
                    if (Objects.requireNonNull(cell.cellType) === CellType.STRING) {
                        nameList.add(cell.stringCellValue)
                    }
                    workbook.close()
                    fileInputStream.close()
                }
                for (i in 0 until numRows) {
                    val row: Row = sheet.getRow(i)
                    val cell: Cell = row.getCell(3)
                    if (Objects.requireNonNull(cell.cellType) === CellType.STRING) {
                        analogNameList.add(cell.stringCellValue)
                    }
                    workbook.close()
                    fileInputStream.close()
                }
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            val result: MutableList<EtalonPartsDictionary> = mutableListOf()
            for (i in artList.indices) {
                val etalonParts = EtalonPartsDictionary()
                etalonParts.art = artList[i]
                etalonParts.analogName = analogNameList[i]
                etalonParts.name = nameList[i]
                result.add(etalonParts)
            }
            return result
        }

    }
}