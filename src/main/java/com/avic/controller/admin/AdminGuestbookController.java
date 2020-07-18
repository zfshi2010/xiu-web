package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.entity.*;
import com.avic.repository.*;
import com.avic.util.PdfUtil;
import com.avic.vo.GuestbookVo;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/admin/guestbook")
public class AdminGuestbookController extends BaseController {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    private MeasurementTaskRepository measurementTaskRepository;

    @Autowired
    private MeasurementTaskTypeRepository measurementTaskTypeRepository;

    @Autowired
    private TextureRepository textureRepository;

    @Autowired
    private GuestbookParameterValueRepository guestbookParameterValueRepository;

    @Value("${img.path}")
    private String path;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "admin/guestbook/list";
    }



    @RequestMapping("/detail")
    public String detail(ModelMap model, Long id) throws Exception{
        Guestbook guestbook = guestbookRepository.findOne(id);
        GuestbookVo guestbookVo = guestbook.toVo();

        if (guestbookVo.getMeasurementTaskId() != null) {
            MeasurementTask measurementTask = measurementTaskRepository.findOne(guestbookVo.getMeasurementTaskId());
            if (measurementTask != null) {
                guestbookVo.setMeasurementTaskName(measurementTask.getName());
            }
        }
        if (guestbookVo.getMeasurementTaskTypeId() != null) {
            MeasurementTaskType measurementTaskType = measurementTaskTypeRepository.findOne(guestbookVo.getMeasurementTaskTypeId());
            if (measurementTaskType != null) {
                guestbookVo.setMeasurementTaskTypeName(measurementTaskType.getName());
            }
        }
        if (guestbookVo.getTextureId() != null) {
            Texture texture = textureRepository.findOne(guestbookVo.getTextureId());
            if (texture != null) {
                guestbookVo.setTextureName(texture.getName());
            }
        }

        List<GuestbookParameterValue> guestbookParameterValues = guestbookParameterValueRepository.findByGuestbookId(id);
        model.addAttribute("guestbookParameterValues", guestbookParameterValues);
        model.addAttribute("guestbook", guestbookVo);
        return "admin/guestbook/detail";
    }

    @RequestMapping("/pdf")
    public void pdf(ModelMap model, Long id, HttpServletResponse response) throws Exception{
        Guestbook guestbook = guestbookRepository.findOne(id);
        GuestbookVo guestbookVo = guestbook.toVo();

        if (guestbookVo.getMeasurementTaskId() != null) {
            MeasurementTask measurementTask = measurementTaskRepository.findOne(guestbookVo.getMeasurementTaskId());
            if (measurementTask != null) {
                guestbookVo.setMeasurementTaskName(measurementTask.getName());
            }
        }
        if (guestbookVo.getMeasurementTaskTypeId() != null) {
            MeasurementTaskType measurementTaskType = measurementTaskTypeRepository.findOne(guestbookVo.getMeasurementTaskTypeId());
            if (measurementTaskType != null) {
                guestbookVo.setMeasurementTaskTypeName(measurementTaskType.getName());
            }
        }
        if (guestbookVo.getTextureId() != null) {
            Texture texture = textureRepository.findOne(guestbookVo.getTextureId());
            if (texture != null) {
                guestbookVo.setTextureName(texture.getName());
            }
        }
        List<GuestbookParameterValue> guestbookParameterValues = guestbookParameterValueRepository.findByGuestbookId(id);
        guestbookVo.setGuestbookParameterValues(GuestbookParameterValue.toList(guestbookParameterValues));


        String pdfPath = path + "/pdf/pdf_" + id + ".pdf";
        Document document = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        PdfUtil pdfUtil = new PdfUtil(document,pdfWriter,guestbookVo);
        pdfUtil.createPDF();
        writerResponse("pdf2" + id, response, pdfPath);
    }

    /**
     * description 弹出文件
     * param:
     * return
     */
    private void writerResponse(String fileName, HttpServletResponse response, String exportPath) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".pdf");
            response.setContentType("application/octet-stream");
            FileInputStream fis = new FileInputStream(exportPath);
            byte[] content = new byte[fis.available()];
            fis.read(content);
            fis.close();

            ServletOutputStream sos = response.getOutputStream();
            sos.write(content);
            sos.flush();
            sos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
