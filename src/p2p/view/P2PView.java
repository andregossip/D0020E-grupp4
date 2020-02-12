package p2p.view;

import csvIO.csvIO;
import simulator.SimView;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;

import p2p.state.P2PState;

public class P2PView extends SimView{
    private String Filepath = "";
    private String result = "";
    private ArrayList<csvIO> csv = new ArrayList<>();
    /**
     * 
     * @param filepath
     */
    public P2PView(String filepath, int nodeAmount) {
    	//this.Filepath = filepath;
    	for(int i = 0; i<nodeAmount; i++) {
    		csvIO temp = new csvIO();
    		temp.setFile(filepath + i + ".csv");
    		csv.add(temp);
    	}
    }
    @Override
    public void update(Observable o, Object arg){
        P2PState state = (P2PState) o;
        if(state.simulatorIsRunning()){
            result += generateProgress(state);
        }
        else{
           // printFile(Filepath, true);
        	try {
				for(int i = 0; i<csv.size(); i++) {
					csv.get(i).saveCsvMatrix();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            printConsole();
        }
    }
    public String generateProgress(P2PState state) {
    	String result = null;
    	if(state.getNodeWhoPerformedEvent() != "-") {
    		int node = Integer.parseInt(state.getNodeWhoPerformedEvent());
    		csvIO temp = csv.get(node);

    	temp.matrixNewLine();
    	result ="";
    	result += state.getElapsedTime();
    	temp.matrixAdd(Double.toString(state.getElapsedTime()));
    	result += state.getEventDescription();
    	temp.matrixAdd(state.getEventDescription());
    	if(state.getNodeWhoPerformedEvent() != "-") {
    		result += state.getNodeWhoPerformedEvent();
    		temp.matrixAdd(state.getNodeWhoPerformedEvent());
    	}
    	}
    	return result;
    }
    @Override
    public void printFile(String filePath, boolean overwrite){
        File file = new File(filePath);
        if (file.isDirectory()){
            return;
        }
        try {
			PrintWriter writer = new PrintWriter(file);
			writer.println(result);
			writer.close();
			System.out.println("File saved to: \n" + filePath);
		} catch (Exception e) {
			System.out.println("An error occured while writing result to file at: \n" + filePath);
		}
    }
    
	@Override
	public void printConsole() {
		System.out.println(result);
	}
}