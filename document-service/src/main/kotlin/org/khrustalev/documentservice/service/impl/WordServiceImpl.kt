package org.khrustalev.documentservice.service.impl

import org.apache.poi.ss.usermodel.FontFamily
import org.apache.poi.wp.usermodel.HeaderFooterType
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.khrustalev.documentservice.dto.CarRepairStateDto
import org.khrustalev.documentservice.dto.RepairRequestDto
import org.khrustalev.documentservice.feign.StorageFeignClient
import org.khrustalev.documentservice.service.WordService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.UUID

@Service
class WordServiceImpl(private val storageFeignClient: StorageFeignClient) : WordService {
    private val LOGGER: Logger = LoggerFactory.getLogger(WordServiceImpl::class.java)

    override fun generateRepairReport(repairId: Long): Boolean {
        val repairReportDto = storageFeignClient.generateRepairReport(repairId).body
        val repairRequestList = mutableListOf<RepairRequestDto>()
        val repairStatesList = mutableListOf<CarRepairStateDto>()
        repairReportDto!!.repairRequestIds.forEach { repairRequestList.add(storageFeignClient.getByRepairRequestId(it).body!!)  }
        repairReportDto.carRepairStatesIds.forEach { _ -> repairStatesList.addAll(storageFeignClient.getRepairStatesForRepairByRepairId(repairId).body!!) }

        val document = XWPFDocument()
        val file = File("Repair-Report # " + UUID.randomUUID().toString().substring(0, 8) + ".doc")
        val r1 = document.createParagraph().createRun()
        val r2 = document.createParagraph().createRun()
        val r3 = document.createParagraph().createRun()

        r1.setFontSize(15.0)
        repairRequestList.forEach { r1.setText("Основанием для ремонта являлась Заявка на ремонт № ${it.requestNumber} - описание: ${it.requestDescription}") }
        r1.addBreak()
        r1.addBreak()

        r2.setShadow(true)
        r2.fontFamily = FontFamily.MODERN.name
        r2.fontSize = 12
        r2.setText("В ходе ремонта было задействовано ${repairStatesList.size} стадий")
        r2.addBreak()
        repairStatesList.forEach { r2.setText("На стадии ${it.id} , было задействованы механики ${it.mechanicIds} и установлены запчасти \n " +
                "${it.repairParts}") }
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            document.write(byteArrayOutputStream)
            document.close()
            file.writeBytes(byteArrayOutputStream.toByteArray())
            byteArrayOutputStream.close()
            sendFile(file)
        } catch (e: Exception) {
            LOGGER.info(e.stackTrace.toString())
        } catch (e1: IOException) {
            LOGGER.info(e1.stackTrace.toString())
        }
        return true
    }

    private fun sendFile(file: File) {
        val body: LinkedMultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("document", FileSystemResource(file))
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA
        val requestEntity: HttpEntity<LinkedMultiValueMap<String, Any>> = HttpEntity(body, headers)
        try {
            restTemplate.exchange(
                "https://api.telegram.org/bot6453066702:AAG7cD-xrOKYOAAOyPm2_PKzV0hCbKkDLqI/sendDocument?chat_id=1094273082",
                HttpMethod.POST,
                requestEntity,
                Any::class.java
            )
        } catch (e: Exception) {
            println(e)
        }
    }

}