package com.seeyon.apps.shbb.test;
import java.text.NumberFormat;
public class test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		 //这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
		   double percent = 50.5D / 150D;
		   //输出一下，确认你的小数无误
		   System.out.println("小数：" + percent);
		   //获取格式化对象
		   NumberFormat nt = NumberFormat.getPercentInstance();
		   //设置百分数精确度2即保留两位小数
		   nt.setMinimumFractionDigits(2);
		   //最后格式化并输出
		   System.out.println("百分数：" +percent);
	}
	
	public static String getMatterCodeIndex(int index){
		String result = "";
		Integer index1 = index;//转换为包装类Integer
		int intLen = index1.toString().length();
		switch (intLen) {
		case 1:
			result = "000" + index;
			break;
		case 2:
			result = "00" + index;
			break;
		case 3:
			result = "0" + index;
			break;
		case 4:
			result = "" + index;
			break;
		default:
			result = "" + index;
			break;
		}
		return result;
	}

}
