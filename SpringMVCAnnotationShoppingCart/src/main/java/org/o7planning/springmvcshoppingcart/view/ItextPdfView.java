package org.o7planning.springmvcshoppingcart.view;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.CartLineInfo;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ItextPdfView extends AbstractITextPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, 
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<CartInfo> courses = (List<CartInfo>) model.get("courses");
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");


        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{60, 10, 30});

        
        table.addCell("quantity");
        table.addCell("precio");
        table.addCell(cartInfo.getAmountTotal()+"numero de pedido");

        for (CartLineInfo produ : cartInfo.getCartLines()){
            table.addCell(produ.getQuantity()+"unidades");
            table.addCell(produ.getAmount()+"amount");
            table.addCell("");

        }
        
        table.addCell(cartInfo.getQuantityTotal()+" :total de items");
        table.addCell(cartInfo.getAmountTotal()+"Total precio");
        table.addCell(cartInfo.getOrderNum()+"ordernum");
        
       
        document.add(table);
    }
}
