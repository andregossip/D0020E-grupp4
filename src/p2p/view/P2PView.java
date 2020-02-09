package p2p.view;

import simulator.SimView;

import java.io.File;
import java.io.PrintWriter;
import java.util.Observable;

import p2p.state.P2PState;

public class P2PView extends SimView{
    private String Filepath = "";
    private String result = "";
    
    /**
     * 
     * @param filepath
     */
    public P2PView(String filepath) {
    	this.Filepath = filepath;
    }
    @Override
    public void update(Observable o, Object arg){
        P2PState state = (P2PState) o;
        if(state.simulatorIsRunning()){
            result += generateProgress(state);
        }
        else{
           // printFile(Filepath, true);
            printConsole();
        }
    }
    public String generateProgress(P2PState state) {
    	String result ="";
    	result += state.getElapsedTime();
    	result += ",";
    	result += state.getEventDescription();
    	if(state.getNodeWhoPerformedEvent() != "-") {
    		result += ", Node: ";
    		result += state.getNodeWhoPerformedEvent();
    	}
    	result += "\n";
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