package com.avic.util;

import com.avic.entity.Guestbook;
import com.avic.vo.GuestbookVo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPdf {
    /*public static void main(String[] args) throws Exception {

        *//*TestPdf pdf = new TestPdf();
        String filename = "/Users/zfshi/Desktop/去飞行新需求/pdf/a.pdf";
        pdf.createPDF(filename);
        System.out.println("打印完成");

        Document document = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filename));

        GuestbookVo guestbook = new GuestbookVo();
        guestbook.setCompanyName("好");
        guestbook.setName("女");
        guestbook.setPhone("13110011001");
        PdfUtil pdfUtil = new PdfUtil(document,pdfWriter,guestbook);
        pdfUtil.createPDF();*//*

    }*/
    public void createPDF(String filename) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();
            document.add(createTable(pdfWriter));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static PdfPTable createTable(PdfWriter writer) throws DocumentException, IOException{
        PdfPTable table = new PdfPTable(2);//生成一个两列的表格
        PdfPCell cell;
        int size = 20;
        cell = new PdfPCell(new Phrase("one"));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("two"));
        cell.setFixedHeight(size);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("three"));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("four"));
        cell.setFixedHeight(size);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("five"));
        cell.setColspan(1);//设置所占列数
        //cell.setRowspan(2);//合并行
        cell.setFixedHeight(size*2);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        PdfPTable table2 = new PdfPTable(1);//新建一个table
        cell = new PdfPCell(new Phrase("six"));
        cell.setFixedHeight(size);
        table2.addCell(cell);
        cell = new PdfPCell(new Phrase("seven"));
        cell.setFixedHeight(size);
        table2.addCell(cell);
        cell = new PdfPCell(table2);//将table放到cell中
        table.addCell(cell);//将cell放到外面的table中去
        Phrase phrase1 = new Phrase();
        Chunk chunk1 = new Chunk("         YES");
        Chunk chunk2 = new Chunk("          NO");
        phrase1.add(chunk1);
        phrase1.add(chunk2);
        cell = new PdfPCell(phrase1);
        cell.setColspan(2);
        table.addCell(cell);
        //增加两个单选框
        PdfFormField radiogroup=PdfFormField.createRadioButton(writer, true);
        radiogroup.setFieldName("salesModel");
        Rectangle rect1 = new Rectangle(110, 722, 120, 712);
        Rectangle rect2 = new Rectangle(165, 722, 175, 712);
        RadioCheckField radio1 = new RadioCheckField(writer, rect1, null, "self-support" ) ;
        RadioCheckField radio2 = new RadioCheckField(writer, rect2, null, "cooprate") ;
        radio2.setChecked(true);
        PdfFormField radiofield1 = radio1.getRadioField();
        PdfFormField radiofield2 = radio2.getRadioField();
        radiogroup.addKid(radiofield1);
        radiogroup.addKid(radiofield2);
        writer.addAnnotation(radiogroup);
        return table;
    }
}
