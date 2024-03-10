package org.khrustalev.documentservice.service.impl

import org.apache.poi.wp.usermodel.HeaderFooterType
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.khrustalev.documentservice.feign.StorageFeignClient
import org.khrustalev.documentservice.service.WordService
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

@Service
class WordServiceImpl(private val storageFeignClient: StorageFeignClient) : WordService {
    override fun generateWordDocument(): Boolean {
        try  {
            var document = XWPFDocument()
            var p = document.createParagraph()
            var header = document.createHeader(HeaderFooterType.DEFAULT)
            val pRun = p.createRun()
            pRun.setText("Тестовый текст")
            pRun.setFontSize(36.0)
            pRun.addBreak()
            pRun.addBreak()

            val hp = header.createParagraph()
            val hpRun = hp.createRun()
            hpRun.setText("Тестовый текст заголовка")
            hpRun.setFontSize(26.0)
            hpRun.addBreak()

            val byteArrayOutputStream = ByteArrayOutputStream()
            document.write(byteArrayOutputStream)

            val file = File("test.doc")
            file.writeBytes(byteArrayOutputStream.toByteArray())
            byteArrayOutputStream.close()
            sendFile(file)
            return true

        } catch (e: Exception) {
            println(e)
        }
        return false
    }

    override fun generateRepairRequest(repairId: Long): Boolean {
        val repairReportDto = storageFeignClient.generateRepairReport(repairId).body
        val document = XWPFDocument()
        val file = File("repair-report.doc")
        val p1 = document.createParagraph()
        val p2 = document.createParagraph()
        val p3 = document.createParagraph()
        val r1 = p1.createRun()
        val r2 = p2.createRun()
        val r3 = p3.createRun()
        return false
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