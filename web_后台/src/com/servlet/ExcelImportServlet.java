package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//����excel
@WebServlet("/ExcelImportServlet")
public class ExcelImportServlet extends HttpServlet {
	static String cid=null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("charset=UTF-8");
        
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            // �ļ��ϴ����Ĺ�����
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 1024); // �����ļ���С����
            upload.setSizeMax(50 * 1024 * 1024); // ���ļ���С����
            upload.setHeaderEncoding("UTF-8"); // �������ļ����봦��

            if (ServletFileUpload.isMultipartContent(request)) {
                List<FileItem> list = upload.parseRequest(request);
                // ����
                for (FileItem item : list) {
                    if (!item.isFormField()) {
                        readExcel(item.getInputStream());      
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        response.sendRedirect("admin/newsList.jsp?cid="+cid);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    
    //��ȡExcel�Ĺ���
    public static void readExcel(InputStream input) throws Exception {
        String URL = "jdbc:mysql://112.124.21.19:3306/attendance?autoReconnect=true";
        final String USERNAME = "wuhao";
        final String PWD = "12345Wh/";
        Connection connection = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        //��JDBC����
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(URL, USERNAME, PWD);          

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Workbook wb = null;
        int i, j;
        try {
            wb = WorkbookFactory.create(input);
            Sheet sheet = wb.getSheetAt(0); // ��õ�һ����
            int totalRow = sheet.getLastRowNum();// �õ�excel���ܼ�¼����
            int columtotal = sheet.getRow(0).getPhysicalNumberOfCells();// ��ͷ�ܹ�������

            for (i = 1; i <= totalRow; i++) {
                //for(j = 0; j < columtotal; j++) {
                //ʹ��JDBC��������ݴ浽���ݿ�
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = (Connection) DriverManager.getConnection(URL, USERNAME, PWD);

                    //�����ݿ������ID��������DATA�������ݡ�
                    String sql = "insert into manage_student_course (sno,cid,tid,status) values(?,?,?,?)";
                    pstmt2 = (PreparedStatement) connection.prepareStatement(sql);
              
                    int n=1;
                    for (j = 0; j < columtotal; j++) {
                        sheet.getRow(i).getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        pstmt2.setString(n, sheet.getRow(i).getCell(j).getStringCellValue());
                        n++;
                    }
                    cid=sheet.getRow(1).getCell(1).getStringCellValue();
                    
                    pstmt2.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (pstmt2 != null) pstmt2.close();
                        if (connection != null) connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
            //����̨���
            System.out.println("������:" + totalRow + ",������:" + columtotal);
            for (i = 1; i <= totalRow; i++) {// ������
                for (j = 0; j < columtotal; j++) {
                    sheet.getRow(i).getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    System.out.print(sheet.getRow(i).getCell(j).getStringCellValue() + "      ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        }
    }
}