package intmatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Intmatrix {

	private final String path = "C://Temp//matrix.txt";
	private Random r = new Random();
	
	protected Intmatrix(int rows, int cols) throws IOException {
		String matrix = generateMatrix(rows, cols);
		storeMatrix(matrix);
		matrix = "";
		matrix = readMatrix();
		System.out.println("Исходная матрица: \n" + matrix + "\n");
		matrix = transformMatrix(matrix);
		System.out.println("Перобразованная матрица: \n" + matrix);
	}
	
	private String transformMatrix(String contents) {
		String[] cm = contents.split("\n");
		String[] vals;
		int val;
		for(int row = 0; row < cm.length; row++) {
			vals = cm[row].split("\t");
			for(int col = 0; col < vals.length; col++) {
				val = (int) Math.pow(Double.parseDouble(vals[col]), (double)(row + 1));
				vals[col] = String.valueOf(val);
			}
			
			cm[row] = String.join("\t", vals);
		}
		
		return String.join("\n", cm);
	}
	
	private void storeMatrix(String contents) throws IOException {
		FileWriter f = new FileWriter(path, false);
		f.write(contents);
		f.flush();
		f.close();
	}
	
	private String readMatrix() throws FileNotFoundException {
		String[] out = new String[0];
		String p = String.join("/", path.split("//"));
		File f = new File(p);
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine()) {
			out = Arrays.copyOf(out, out.length + 1);
			out[out.length - 1] = sc.nextLine();
		}
		
		sc.close();
		
		return String.join("\n", out);
	}
	
	private String generateMatrix(int rows, int cols) {
		String[] mrw = new String[cols];
		String[] mat = new String[rows];
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				mrw[col] = String.valueOf(r.nextInt(9) + 1);
			}
			
			mat[row] = String.join("\t", mrw);
		}
		
		return String.join("\r\n", mat);
	}
	
	public static void main(String[] args) throws IOException {
		new Intmatrix(4, 4);

	}

}
