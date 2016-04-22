package guiTables;

import java.util.Vector;

public class TableWindowsYearlyData extends Vector<TableYearlyDataTEST> {
	
	public Boolean search(String stationName){
		
		Boolean exists = false;
		
		for (TableYearlyDataTEST dataTable : this){
			
			if(dataTable.getName().equals(stationName)){
				exists = true;				
			}
			else{
				exists = false;
			}			
		}		
		return exists;
	}
	
	public void toFront(String stationName){
		
		for (TableYearlyDataTEST dataTable : this){
			if(dataTable.getName().equals(stationName)){
				dataTable.toFront();					
			}
		}		
	}
	
	public void runALL(){
		
	
	}
}