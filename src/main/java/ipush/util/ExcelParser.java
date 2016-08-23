package ipush.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ipush.model.Member;
import jxl.*;

/**
 * 用于解析excel文件，并从中获取所有客户的消息
 * @author arlabsurface
 *
 */
public class ExcelParser {

	
	public static List<Member> parseFile(File file, int createUserId) {
		String fileName = file.getName();
		if (fileName.endsWith("xls")) {
			return getContentOfXls(file, createUserId);
		} else if (fileName.endsWith("xlsx")) {
			return getContentOfXlsx(file, createUserId);
		} else {
			return null;
		}
		
	}
	
	private static List<Member> getContentOfXlsx(File file, int createUserId) {
		List<Member> result = new ArrayList<Member>();
		XSSFWorkbook wb = null;  
		XSSFSheet sheet; 
        int i;
        XSSFCell cell1, cell2, cell3, cell4, cell5;
        try {
			wb = new XSSFWorkbook(file);
			sheet = wb.getSheetAt(0);
			int all = sheet.getLastRowNum();
			i = 1;
			while(i <= all) {
				XSSFRow row = sheet.getRow(i);
				cell1 = row.getCell(0);
				cell2 = row.getCell(1);
				cell3 = row.getCell(2);
				cell4 = row.getCell(3);
				cell5 = row.getCell(4);
				String memberName = "", mobileNum = "", email = "", openId = "", weiboId = "";
				
				if(cell1 != null)
					memberName = cell1.getStringCellValue();
				if(cell2 != null)
					mobileNum = cell2.getRawValue();
				//cell3.toString()

				if(cell3 != null)
					email = cell3.getStringCellValue();
				if(cell4 != null)
					openId = cell4.getRawValue();
				if(cell5 != null)
					weiboId = cell5.getRawValue();
				
				StringBuilder s = new StringBuilder();
				if (openId == "")
					s.append('0');
				else
					s.append('1');
				if (weiboId == "")
					s.append('0');
				else
					s.append('1');
				if (mobileNum == "")
					s.append('0');
				else
					s.append('1');
				if (email == "")
					s.append('0');
				else
					s.append('1');

				
				byte channelProp = Byte.parseByte(s.toString(), 2);
				
				result.add(new Member(null, createUserId, memberName, mobileNum, email, openId, weiboId, channelProp));
				i++;
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return result;
	}
	
	
	private static List<Member> getContentOfXls(File file, int createUserId) {
		List<Member> result = new ArrayList<Member>();
		int i;
		Sheet sheet;
		Workbook book;
		Cell cell1, cell2, cell3, cell4, cell5;
		try {
			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(file);

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(0);
			// 获取左上角的单元格
			cell1 = sheet.getCell(0, 0);
			int all = sheet.getRows();
			i = 1;
			while (i < all) {
				// 获取每一行的单元格
				
				cell1 = sheet.getCell(0, i);// （列，行）
				cell2 = sheet.getCell(1, i);
				cell3 = sheet.getCell(2, i);
				cell4 = sheet.getCell(3, i);
				cell5 = sheet.getCell(4, i);
				if ("".equals(cell1.getContents()) == true) // 如果读取的数据为空
					break;
				
				String memberName = cell1.getContents();
				String mobileNum = cell2.getContents();
				String email = cell3.getContents();
				String openId = cell4.getContents();
				String weiboId = cell5.getContents();
				
				StringBuilder s = new StringBuilder();
				if (openId == "")
					s.append('0');
				else
					s.append('1');
				if (weiboId == "")
					s.append('0');
				else
					s.append('1');
				if (mobileNum == "")
					s.append('0');
				else
					s.append('1');
				if (email == "")
					s.append('0');
				else
					s.append('1');

				
				byte channelProp = Byte.parseByte(s.toString(), 2);
				
				result.add(new Member(null, createUserId, memberName, mobileNum, email, openId, weiboId, channelProp));
				i++;
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		List<Member> list = parseFile(new File("C:/Users/arlabsurface/Desktop/通讯录模板 (1).xls"), 1);
		System.out.println(list);
		List<Member> list2 = parseFile(new File("C:/Users/arlabsurface/Desktop/通讯录模板 (1).xlsx"), 1);
		System.out.println(list2);
	}
}
