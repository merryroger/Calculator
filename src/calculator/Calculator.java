package calculator;

/**
 * Класс Caculator отвечает за выполнение математических операций и выдачу результатов.
 * @author Ehwaz Raido
 *
 */

public class Calculator {

	private double op1, op2, res;
	private int dotpos, dcount, sign, step;
	private String operation, error;
	
	protected Calculator() {
		creset();
	}
	
	/**
	 * Метод creset выполняет "сброс" основных параметров калькулятора в исходное состояние.
	 */
	protected void creset() {
		op1 = 0.0;
		op2 = 0.0;
		res = 0.0;
		sign = 1;
		step = 0;
		dotpos = -1;
		operation = "";
		error = "";
		dcount = 0;
	}
	
	/**
	 * Мтод appendSign принимает символы, вводимые с цифровых клавиш калькулятора, добавляет к набираемому числу и преобразует данные в число для операнда1.
	 * @param s String - вводимый символ;
	 * @param dc int - счетчик символов вводимого числа.
	 * @return
	 */
	protected boolean appendSign(String s, int dc) {
		if(dotpos == -1 && s.equals(new String("0")) && dc == 0)
				return false;
		
		if(dotpos == -1 && s.equals(new String("."))) {
			dotpos = 0;
			if(dc == 0)
				dcount = 1;
			
			return false;
		}
		
		if(dotpos > -1 && s.equals(new String(".")))
			return false;
		
		if(s.equals(new String("-"))) {
			sign *= -1; return false;
		}
		
		try {		
			op1 = Double.parseDouble(getOp1() + s);
			if(dotpos > -1)
				dotpos++;
			
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Метод возвращает либо пустой символ, либо "-" для отображения на дисплее в зависимости от знака операнда.	
	 * @return String
	 */
	protected String getSign() {
		return (sign > 0) ? " " : "-";
	}
	
	/**
	 * Метод возвращает отформатированное строковое значение операнда1.
	 * @return String;
	 */
	protected String getOp1() {
		return this.toString(op1);
	}
	
	/**
	 * Метод возвращает строковое значение операнда2.
	 * @return String;
	 */
	protected String getOp2() {
		return String.valueOf((float) op2);
	}
	
	/**
	 * Метод возвращает отформатированное строковое значение результата операций либо сообщение об ошибке.
	 * @return String
	 */
	protected String getResult() {
		op1 = (float) res * sign;
		if(error.length() > 0)
			operation = "=";
		return (error.length() == 0) ? formatOutput(Math.abs(res)) : error;
	}
	
	/**
	 * Метод форматирует и преобразует к строковому типу данных значение типа double; 
	 * @param op double;
	 * @return String;
	 */
	private String toString(double op) {
		String out;
		if(dotpos < 0) {
			out = String.valueOf(op);
			if(out.indexOf("E") < 0)
				out = out.substring(0, out.indexOf("."));
			else {
				out = out.split("E")[0];
				out = out.replace(".", "");
				while(out.length() < dcount)
					out += "0";
			}
			
		} else if(dotpos == 0) {
			out = String.valueOf(op);
			if(out.indexOf("E") < 0)
				out = out.substring(0, out.indexOf(".") + 1);
			else {
				out = out.split("E")[0];
				out = out.replace(".", "") + ".";
			}
			
		} else {
			out = String.valueOf(op);
			
			if(out.indexOf("E") > 0) {
				String[] o = out.split("E");
				o[0] = o[0].replace(".", "");
				int e = Integer.parseInt(o[1]);
				if(e > 0)
					out = o[0].substring(0, e + 1) + "." + o[0].substring(e + 1);
				else {
					out = "0.";
					while(e < -1) {
						out += "0"; e++;
					}
					out += o[0];
					while(out.lastIndexOf("0") == out.length() - 1)
						out = out.substring(0, out.length() - 1);
				}
						
				o = null;
			}
			
			while(out.length() <= dcount)
				out += "0";
		}
		
		return out;
	}
	
	/**
	 * Метод пытается форматировать и преобразовать к строковому типу данных результат операций; 
	 * @param r double;
	 * @return String.
	 */
	private String formatOutput(double r) {
		String out = String.valueOf((float)r);
		if(out.indexOf("E") < 0)
			return out;
		
		String[] o = out.split("E");
		int e = Integer.parseInt(o[1]);
		if(e > 15 || e < -14)
			return out;
		
		o[0] = o[0].replace(".", "");
		if(e > 0) {
			out = o[0].substring(0, e + 1) + "." + o[0].substring(e + 1);
		}
		else {
			out = "0.";
			while(e < -1) {
				out += "0"; e++;
			}
			out += o[0];
			while(out.lastIndexOf("0") == out.length() - 1)
				out = out.substring(0, out.length() - 1);
		}
		return out;
	}
	
	/**
	 * Обработчик команд с кнопок управления.
	 * @param cmd String.
	 */
	protected void setCommand(String cmd) {
		if(step == 0) {
			op2 = op1 * sign;
			operation = cmd;
		}
		else {
			switch(operation) {
				case "+":
				case "-": op2 += (op1 * sign); operation = cmd; break;
				case "*": op2 *= (op1 * sign); operation = cmd; break;
				case "/":
					if(op1 == 0) {
						error = "Infinity"; cmd = "=";
						break;
					}
					op2 /= (op1 * sign); operation = cmd;
				break;
			}
		}
		if(cmd.equals(new String("="))) {
			res = (float) op2;
			op2 = 0.0; op1 = 0.0;
			operation = "="; step = 0;
			sign = (res < 0) ? -1 : 1;
			return;
		}
		op1 = 0.0;
		dotpos = -1;
		sign = (cmd.equals(new String("-"))) ? -1 : 1;
		step++;
	}
	
	/**
	 * Отдельный метод для получения квадратного корня из значения операнда1.
	 */
	protected void getRoot() {
		if(sign < 0 || op1 < 0)
			error = "Error: negative argument";
		else
			res = Math.sqrt(op1);
		op2 = 0.0; op1 = 0.0;
		operation = "="; step = 0;
		sign = (res < 0) ? -1 : 1;
		dotpos = -1; 
	}
	
	protected boolean backSpace() {
		switch(operation) {
			case "SQR": case "=": return false; 
		}
		if(dcount > 0) {
			op1 = (dcount > 1) ? Double.parseDouble(getOp1().substring(0, getOp1().length() - 1)) : 0;
			dcount--;
			
			if(dotpos > 0)
				dotpos--;
			else if(dotpos == 0) {
				op1 = (dcount >= 1) ? Double.parseDouble(getOp1().substring(0, getOp1().length() - 2)) : 0;
				dotpos--;
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * Метод возвращает значение счетчика операций.
	 * @return int.
	 */
	protected int getStep() {
		return step;
	}
	
	/**
	 * Метод возвращает тип выполняемой операции.
	 * @return String.
	 */
	protected String getOperation() {
		return operation;
	}
	
	protected String getOperationSign(String op) {
		switch(op) {
			case "-": case "+": case "*": case "/": return op;
			default: return " ";
		}
	}
	
	/**
	 * Метод возвращает значение счетчика количества символов операнда1.
	 * @return int.
	 */
	protected int getDCount() {
		return dcount;
	}
	
	/**
	 * Метод устанавливает значение счетчика количества символов операнда1.
	 * @param dc int.
	 */
	protected void setDCount(int dc) {
		dcount = dc;
	}
	
	/**
	 * Метод для проверки возникновения ошибки при выполнении операции.
	 * @return boolean.
	 */
	protected boolean isError() {
		return (error.length() > 0) ? true : false;
	}
	
}
