package mann.alon.halamish.reem.winners;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Mr Bond on 27/05/2016.
 */
public class Games {
    String nameA;
    String nameB;
    ArrayList<Integer> winsA;
    ArrayList<Integer> winsB;
    double meanA;
    double meanB;
    double SEM_A;
    double SEM_B;

    public Games(String nameA, String nameB) {
        this.nameA = nameA;
        this.nameB = nameB;
        this.winsA = new ArrayList<>();
        this.winsB = new ArrayList<>();

    }

    public void polish(){
        // calculate SEM and mean
        double sum=0;
        for (Integer i:winsA){sum=sum+i;}
        meanA=sum/winsA.size();

        sum=0;
        for (Integer i:winsB){sum=sum+i;}
        meanB=sum/winsB.size();

        double sum_squers=0;
        for (Integer i:winsA){
            double distance = (i - meanA) * (i-meanA);
            sum_squers += distance;
        }
        double std = sum_squers / (winsA.size()-1);
        SEM_A=std/ Math.sqrt(winsA.size());
        if (winsA.size()==1) SEM_A=10;

        sum_squers=0;
        for (Integer i:winsA){
            double distance = (i - meanB) * (i-meanB);
            sum_squers += distance;
        }
        std = sum_squers / (winsB.size()-1);

        SEM_B=std/ Math.sqrt(winsB.size());
        if (winsB.size()==1) SEM_B=10;

    }


    public void flip() {
       String temp;
        temp=this.nameA;
        this.nameA=this.nameB;
        this.nameB=temp;
        ArrayList<Integer> temparr;
        temparr=this.winsA;
        this.winsA=this.winsB;
        this.winsB=temparr;

        double tempD;
        tempD=meanA;
        meanA=meanB;
        meanB=tempD;
        tempD=SEM_A;
        SEM_A=SEM_B;
        SEM_B=tempD;

    }
}
