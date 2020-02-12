//This is a helper class for handling csv input and output
//For initialization it needs a File object.
//csv files are handled as matrices, or lists of lists
//every line of the csv file is a list, and every value of said line is an entry in the list
//all of the list lines are stored within a container list

package csvIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class csvIO {
	ArrayList<ArrayList> matrix;
	File csv;
	public csvIO() {
		matrix = new ArrayList<ArrayList>(); 
	}
	//This is just a test function
//	public static void main(String[] args) throws IOException {
//		File file =  new File("C:\\Users\\pawel\\Desktop\\csvtest\\test.csv"); 
//		csvIO testIO = new csvIO(file); // Create an object of class MyClass (This will call the constructor)
//		testIO.getCsvMatrix();
//		ArrayList<ArrayList> test = testIO.getMatrix();
//		for(int i = 0; i<test.size();i++) {
//			ArrayList<String> temp = test.get(i);
//			for(int j = 0; j<temp.size(); j++) {
//				System.out.print(temp.get(j));
//			}
//		}
//		testIO.setFile("C:\\\\Users\\\\pawel\\\\Desktop\\\\csvtest\\\\test2.csv");
//		testIO.saveCsvMatrix();
//		
//	}
	//sets file of the csvIO object to file at provided path
	public void setFile(String path) {
		File file =  new File(path); 
		this.csv = file;
		
	}
	//returns matrix of the csvIO object
	public ArrayList<ArrayList> getMatrix(){
		return this.matrix;
	}
	//takes an initialized csvIO instance and returns the contents of it's file as a 2D matrix
	public void getCsvMatrix() throws FileNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader(this.csv)); //init a file reader
		String line;													   //temp variable for holding file lines
		ArrayList<ArrayList> result = new ArrayList<>();                   //housing list for csv value lists
		try {
			while((line = br.readLine())!= null) {							//loop through lines
				String temp = "";
				ArrayList<String> values = new ArrayList<>();
				char[] chars = line.toCharArray();
				for(int i = 0; i<line.length();i++) {						//loop through characters in line to separate values					
					
					
					if(chars[i]!=',') {						
						temp = temp + chars[i];
					}
					else{
						values.add(temp);
						temp = "";
					}
					
				}
				values.add(temp);
				result.add(values);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		this.matrix = result;
	
	}
	//dumps the matrix of the csvIO object into set file in the csv format
	public void saveCsvMatrix() throws IOException {
		ArrayList<ArrayList> matrix = this.matrix;
		BufferedWriter bw = new BufferedWriter(new FileWriter(this.csv));
		String line = "";
		for(int i = 0; i<matrix.size(); i++) {
			for(int j = 0; j<matrix.get(i).size();j++) {
				if(j == matrix.get(i).size()-1) {
					line += matrix.get(i).get(j);
					bw.write(line);
					bw.newLine();
					line = "";
				}
				else {
					line += matrix.get(i).get(j)+",";
				}
			}
		}
		bw.close();
	}
	public void matrixAdd(String input) {				//appends a value to end of last line in the matrix
		this.matrix.get(this.matrix.size()-1).add(input);	
	}
	public void matrixNewLine() {						//adds a new line to the matrix
		ArrayList<String> newLine = new ArrayList<String>();
		this.matrix.add(newLine);
	}
}