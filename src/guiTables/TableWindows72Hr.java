package guiTables;

import java.util.Vector;

public class TableWindows72Hr extends Vector<Table72HrDataTEST> {
	
	public Boolean search(String stationName){
		
		Boolean exists = false;
		
		for (Table72HrDataTEST dataTable : this){
			
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
		
		for (Table72HrDataTEST dataTable : this){		
			if(dataTable.getName().equals(stationName)){
				dataTable.toFront();					
			}
		}			
	}
}