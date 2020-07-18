package com.avic.util;

import com.avic.vo.GuestbookParameterValueVo;
import com.avic.vo.GuestbookVo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PdfUtil {

    private Document document;
    private PdfWriter writer;
    private GuestbookVo guestbookVo;

    public PdfUtil(Document document, PdfWriter writer, GuestbookVo guestbookVo) {
        this.document = document;
        this.writer = writer;
        this.guestbookVo = guestbookVo;
    }

    public  void createPDF() throws IOException {
        try {
            document.addTitle("sheet of product");
            document.addAuthor("scurry");
            document.addSubject("product sheet.");
            document.addKeywords("product.");
            document.open();
            PdfPTable table = createTable(writer, guestbookVo);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private PdfPTable createTable(PdfWriter writer, GuestbookVo guestbookVo) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(2);//生成一个两列的表格
        table.setLockedWidth(true);
        table.setTotalWidth(new float[]{80, 280});

        PdfPCell cell;
        int size = 20;
        BaseFont baseFont = BaseFont.createFont("/Users/zfshi/Desktop/pdf/simsun.ttc" + ",1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont,18,Font.BOLD,BaseColor.BLACK);
        cell = new PdfPCell(new Phrase("在线选型详情", font));
        cell.setFixedHeight(size);
        cell.setColspan(2);//设置所占列数
        cell.setFixedHeight(size*2);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);

        font = new Font(baseFont);
        Font fontLabel = new Font(baseFont,12,Font.BOLD,BaseColor.BLACK);

        cell = new PdfPCell(new Phrase("测量任务：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getMeasurementTaskName(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("产品类型：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getMeasurementTaskTypeName(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("材质：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getTextureName(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("测量范围：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getMeasurementRange(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        for (GuestbookParameterValueVo guestbookParameterValueVo : guestbookVo.getGuestbookParameterValues()) {
            cell = new PdfPCell(new Phrase(guestbookParameterValueVo.getParameterName() + "：", fontLabel));
            cell.setFixedHeight(size);
            cell.setHorizontalAlignment(2);
            table.addCell(cell);
            String parameterValue = guestbookParameterValueVo.getParameterValueName();
            if (guestbookParameterValueVo.getIfInput()) {
                parameterValue = guestbookParameterValueVo.getParameterValueName() + "，用户输入："
                        + guestbookParameterValueVo.getContent();
            }
            cell = new PdfPCell(new Phrase(parameterValue, font));
            cell.setFixedHeight(size);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("公司：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getCompanyName(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("姓名：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getName(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("联系电话：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getPhone(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("内容：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getContent(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("提交时间：", fontLabel));
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(guestbookVo.getCreateTime(), font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        return table;
    }
}
