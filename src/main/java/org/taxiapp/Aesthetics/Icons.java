package org.taxiapp.Aesthetics;

public class Icons {
    String person;
    String taxi;
    String destination;


    public Icons(){
        this.person = " \uD83E\uDDCD\uD83C\uDFFB\u200D♀\uFE0F ";
        this.taxi = " \uD83D\uDE97 ";
        this.destination = " \uD83D\uDCCD ";
    }
    public String getPerson() {
        return person;
    }

    public String getTaxi() {
        return taxi;
    }

    public String getDestination() {
        return destination;
    }
}
