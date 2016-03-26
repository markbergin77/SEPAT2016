package gui;

/**
 * Created by Pavel Nikolaev on 26/03/2016.
 */
public class ListNode {

    private String LOCATION;
    private int TEMP;
    private Boolean RAINING, SUNNY;

    public ListNode (String location, int temp, Boolean raining ,Boolean sunny){

        this.LOCATION = location;
        this.RAINING = raining;
        this.TEMP = temp;
        this.SUNNY = sunny;

    }
}
